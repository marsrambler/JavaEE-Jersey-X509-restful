#! /bin/bash

NAME=localhost
# server files
S_SUBJ="/C=CN/ST=ST_l/L=L_l/O=O_l/OU=OU_l/CN=${NAME}/"
S_PRIV_KEY=${NAME}_privkey.pem
S_CSR=${NAME}.csr
S_PUB_KEY=${NAME}_pubkey.pem
S_P12=${NAME}.p12
S_KEYSTORE=keystore.${NAME}
S_TRUST_KEYSTORE=${NAME}-trust.jks

#client files
CNAME=${NAME}_client
C_SUBJ="/C=CN/ST=ST_l/L=L_l/O=O_l/OU=OU_l/CN=${CNAME}/"
C_PRIV_KEY=${CNAME}_privkey.pem
C_CSR=${CNAME}.csr
C_CERT=${CNAME}.crt
C_PFX=${CNAME}.pfx

openssl genrsa -out ${S_PRIV_KEY} 2048
openssl req -new -key ${S_PRIV_KEY} -subj ${S_SUBJ} -out ${S_CSR}
openssl x509 -req -days 3650 \
	-in ${S_CSR} -signkey  ${S_PRIV_KEY} -out ${S_PUB_KEY}
openssl pkcs12 -export -in ${S_PUB_KEY} -inkey ${S_PRIV_KEY} \
	-passin pass:password -passout pass:password -out ${S_P12}
keytool -importkeystore -srckeystore ${S_P12} -srcstorepass password -srcstoretype pkcs12 \
	-destkeystore ${S_KEYSTORE} -deststorepass password -deststoretype jks

# client stuff
openssl genrsa -out ${C_PRIV_KEY} 2048
openssl req -new -key ${C_PRIV_KEY}  -subj ${C_SUBJ} -out ${C_CSR}
openssl x509 -req -days 3650 -in  ${C_CSR} -out ${C_CERT} -CA ${S_PUB_KEY} \
	-CAcreateserial -CAkey ${S_PRIV_KEY}
openssl pkcs12 -export -inkey ${C_PRIV_KEY} -in ${C_CERT} -certfile ${S_PUB_KEY} \
	-passin pass:password -passout pass:password -out ${C_PFX}


# Server Trust-Store
keytool -importkeystore -srcstoretype pkcs12 -srcstorepass password -srckeystore ${C_PFX}  \
	-destkeystore ${S_TRUST_KEYSTORE} -deststorepass password -deststoretype jks

openssl pkcs12 -in ${C_PFX} -out ${C_PFX}.pem -nodes
