package com.rockit.qcloud.im.signature;

import com.rockit.core.exception.UserSignatureException;
import com.rockit.qcloud.im.common.QCloudProperties;
import com.tls.sigcheck.tls_sigcheck;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TLSSignatureGenerator {
    private static final Logger logger = LoggerFactory.getLogger(TLSSignatureGenerator.class);
    public static final String WIN_JNI_LIB_NAME = "jnisigcheck.dll";
    public static final String UNIX_JNI_LIB_NAME = "jnisigcheck.so";
    public static final String DEFAULT_PRIVATE_KEY_FILE_NAME = "ec_key.pem";

    public static final String SYSTEM_ENV_KEY = "USER_SIGNATURE_PRIVATE_FILE_PATH";
    public static final String SYSTEM_PROPERTY_KEY = "user.signature.privateKeyFile";
    private String privateKey;

    private QCloudProperties userSignatureProperties;

    public TLSSignatureGenerator(QCloudProperties userSignatureProperties) throws UserSignatureException {
        this.userSignatureProperties = userSignatureProperties;

        if (userSignatureProperties == null) {
            throw new UserSignatureException("userSignatureProperties is null");
        }
        if (userSignatureProperties.getJniLibFile() == null) {
            String jniFileName = isWindows() ? WIN_JNI_LIB_NAME : UNIX_JNI_LIB_NAME;
            File tmpdir = getJavaIOTmpDirectory();

            if (logger.isDebugEnabled()) {
                logger.debug("jniLibFile is not specified, use file 'classpath:{}' as default, and copy it to the directory:{} specified by 'java.io.tmpdir'", jniFileName, tmpdir);
            }
            try {
                File jniFile = getClasspathFile(jniFileName);
                FileUtils.copyFileToDirectory(jniFile, tmpdir);
                userSignatureProperties.setJniLibFile(new File(tmpdir, jniFileName).getAbsolutePath());
            } catch (IOException e) {
                throw new UserSignatureException(e);
            }
        }

        if (userSignatureProperties.getPrivateKeyFile() == null) {
            String privateKeyFile = null;
            while (true) {
                privateKeyFile = System.getenv(SYSTEM_ENV_KEY);
                if (privateKeyFile != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("use private key file: 'file:\\{}' specified by system env key:{}", privateKeyFile, SYSTEM_ENV_KEY);
                    }
                    break;
                }
                privateKeyFile = System.getProperty("user.signature.privateKeyFile");
                if (privateKeyFile != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("use private key file: 'file:\\{}' specified by system property key:{}", privateKeyFile, SYSTEM_PROPERTY_KEY);
                    }
                    break;
                }
                privateKeyFile = getClasspathFileAsString(DEFAULT_PRIVATE_KEY_FILE_NAME);

                if (privateKeyFile == null) {
                    throw new UserSignatureException("private key file is not specified");
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("use private key file: 'classpath:{}'", DEFAULT_PRIVATE_KEY_FILE_NAME);
                }
                this.userSignatureProperties.setPrivateKeyFile(privateKeyFile);
                break;
            }
        }

        try {
            tls_sigcheck.loadJniLib(userSignatureProperties.getJniLibFile());
            privateKey = FileUtils.readFileToString(new File(userSignatureProperties.getPrivateKeyFile()));
        } catch (IOException e) {
            throw new UserSignatureException(e);
        }
    }

    public String getUserSignature(String identifier) throws UserSignatureException {
        if (StringUtils.isBlank(identifier)) {
            throw new UserSignatureException("identifier is blank");
        }
        tls_sigcheck sigcheck = new tls_sigcheck();
        int code = sigcheck.tls_gen_signature_ex2_with_expire(userSignatureProperties.getSdkAppId(), identifier, privateKey, userSignatureProperties.getExpire());
        if (0 != code) {
            throw new UserSignatureException(sigcheck.getErrMsg());
        }
        return sigcheck.getSig();
    }

    private static boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.indexOf("win") >= 0;
    }

    private static File getJavaIOTmpDirectory() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    private static String getClasspathFileAsString(String classpathFile) {
        return TLSSignatureGenerator.class.getClassLoader().getResource(classpathFile).getFile();
    }

    private static File getClasspathFile(String classpathFile) {
        String file = getClasspathFileAsString(classpathFile);
        return new File(file);
    }

    public QCloudProperties getUserSignatureProperties() {
        return userSignatureProperties;
    }

    public void setUserSignatureProperties(QCloudProperties userSignatureProperties) {
        this.userSignatureProperties = userSignatureProperties;
    }

}
