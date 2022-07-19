Spring - BOOT - SSL
-------------------

Generate the key using the command: `keytool -genkey -keyalg RSA -alias simplessl -keystore simplessl.jks -storepass simplepass -validity 4000 -keysize 2048`

Run the app: `gradle bootRun`

Access the app: `https://localhost:8000/` (dev profile) or `https://localhost:9000/` (test profile)