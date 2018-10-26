#!/bin/bash

# default password for keys
PASSWORD=password
 
OUT_DIR=certs
  
# Subject items
C="US"
ST="My State"
L="My City"
O="My Corp"
   
CN_CA="My CA Root"
CN_SERVER="localhost"
CN_CLIENT="John Smith jsmith"
    
###############################
 
# Create output directory
mkdir -p ${OUT_DIR}
 
###############################
 
# create CA key
openssl genrsa -des3 -out ${OUT_DIR}/ca.key -passout pass:$PASSWORD 4096

# create CA cert
openssl req -new -x509 -days 365 -key ${OUT_DIR}/ca.key -out ${OUT_DIR}/ca.crt \
-passin pass:$PASSWORD -subj "/C=${C}/ST=${ST}/L=${L}/O=${O}/CN=${CN_CA}/"

# create truststore
keytool -import -trustcacerts -alias caroot -file ${OUT_DIR}/ca.crt \
-keystore ${OUT_DIR}/truststore.jks -storepass ${PASSWORD} -noprompt
  
###############################
   
# create server key
openssl genrsa -des3 -out ${OUT_DIR}/server.key -passout pass:$PASSWORD 4096
  
# create server cert request
openssl req -new -key ${OUT_DIR}/server.key -out ${OUT_DIR}/server.csr \
-passin pass:$PASSWORD -subj "/C=${C}/ST=${ST}/L=${L}/O=${O}/CN=${CN_SERVER}/"

# create server cert
openssl x509 -req -days 365 -in ${OUT_DIR}/server.csr -CA ${OUT_DIR}/ca.crt \
-CAkey ${OUT_DIR}/ca.key -set_serial 01 -out ${OUT_DIR}/server.crt \
-passin pass:${PASSWORD}
   
# convert server cert to PKCS12 format, including key
openssl pkcs12 -export -out ${OUT_DIR}/server.p12 -inkey ${OUT_DIR}/server.key \
-in ${OUT_DIR}/server.crt -passin pass:${PASSWORD} -passout pass:${PASSWORD}

# convert server PKCS12 format to jks
keytool -importkeystore -srckeystore ${OUT_DIR}/server.p12 -srcstoretype PKCS12 \
-destkeystore ${OUT_DIR}/server.jks -deststoretype JKS
      
###############################
    
# create client key
openssl genrsa -des3 -out ${OUT_DIR}/client.key -passout pass:${PASSWORD} 4096
       
# create client cert request
openssl req -new -key ${OUT_DIR}/client.key -out ${OUT_DIR}/client.csr \
-passin pass:$PASSWORD -subj "/C=${C}/ST=${ST}/L=${L}/O=${O}/CN=${CN_CLIENT}/"


# create client cert
openssl x509 -req -days 365 -in ${OUT_DIR}/client.csr -CA ${OUT_DIR}/ca.crt \
-CAkey ${OUT_DIR}/ca.key -set_serial 02 -out ${OUT_DIR}/client.crt \
-passin pass:${PASSWORD}
   
# convert client cert to PKCS12, including key
openssl pkcs12 -export -out ${OUT_DIR}/client.p12 -inkey ${OUT_DIR}/client.key \
-in ${OUT_DIR}/client.crt -passin pass:${PASSWORD} -passout pass:${PASSWORD}

# convert client cert from PKCS12 to pem
openssl pkcs12 -in ${OUT_DIR}/client.p12 -out ${OUT_DIR}/client.pem -nodes

#############################################
#
# below commands are just for reference.
#
#############################################

# import cert to trust 
#keytool -import -trustcacerts -alias server -file server.crt -keystore server.trust

###################################

# print cert
#keytool -printcert -v -file server.crt

# print trust keystore
#keytool -list -v -storetype jks -keystore server.trust

# print keystore
#keytool -list -v -storetype pkcs12 -keystore server.p12

# export cert from keystore (.jks)
#keytool -export -alias caroot -file caExport.crt -keystore truststore.jks

# convert pkcs12 to jks
#keytool -importkeystore -srckeystore abc.p12 -srcstoretype PKCS12 \
#-destkeystore abc.jks -deststoretype JKS

# convert jks to pkcs12
#keytool -importkeystore -srckeystore truststore.jks -srcstoretype JKS \
#-deststoretype PKCS12 -destkeystore truststore.p12


