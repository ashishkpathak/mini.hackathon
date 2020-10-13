package au.com.apathak.hackathon.encryption;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import au.com.apathak.hackathon.utils.Utils;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JWEExample {


  // key for signing the JWT
  private static RSAPrivateKey jwtPrivateKey = null;

  // key for verifying the JWT.
  private static RSAPublicKey jwtPublicKey = null;

  // key for encrypting the JWE.
  private static RSAPublicKey jwePublicKey = null;

  //key for decrypting the JWE.
  private static RSAPrivateKey jwePrivateKey = null;


  private static void loadKeys() throws Exception {
    FileInputStream fis = new FileInputStream("src/main/resources/public_key_jwe.pem");

    String publicKey = Utils.formatKeyResourceFile(fis);
    byte[] decodedKeyBytes = Base64.getDecoder().decode(publicKey);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKeyBytes);
    jwePublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);


    fis = new FileInputStream("src/main/resources/public_key_jwt.pem");
    publicKey = Utils.formatKeyResourceFile(fis);
    decodedKeyBytes = Base64.getDecoder().decode(publicKey);
    keySpec = new X509EncodedKeySpec(decodedKeyBytes);
    jwtPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);

    fis = new FileInputStream("src/main/resources/private_key_jwe.pem");
    publicKey = Utils.formatKeyResourceFile(fis);
    decodedKeyBytes = Base64.getDecoder().decode(publicKey);
    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedKeyBytes);
    jwePrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);


    fis = new FileInputStream("src/main/resources/private_key_jwt.pem");
    publicKey = Utils.formatKeyResourceFile(fis);
    decodedKeyBytes = Base64.getDecoder().decode(publicKey);
    privateKeySpec = new PKCS8EncodedKeySpec(decodedKeyBytes);
    jwtPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);

    System.out.printf("Loaded keys: %n");

  }

  private static final void generateJWT() throws Exception {
    Date now = new Date();
    JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
    claimsSetBuilder.claim("userName", "Viva.La.Vida")
        .subject("LL")
        .audience(Arrays.asList("Harley-Davidson","Triumph","Indian"))
        .expirationTime(new Date(now.getTime() + 1000 * 60 * 60))
        .notBeforeTime(now)
        .issueTime(now).issuer("Department of Transport");

    //create a signed JWT
    SignedJWT signedJWT = new SignedJWT((new JWSHeader.Builder(JWSAlgorithm.RS256)).build(), claimsSetBuilder.build());

    //sign is using JWT private key.
    signedJWT.sign(new RSASSASigner(jwtPrivateKey));

    System.out.printf("JWT: %s%n",signedJWT.serialize());

    JWEObject jweObject = new JWEObject((new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, com.nimbusds.jose.EncryptionMethod.A256GCM)).contentType("JWT").build(), new Payload(signedJWT));

    //encrypt the JWE using public RSA key.
    jweObject.encrypt(new RSAEncrypter(jwePublicKey));
    String jweString = jweObject.serialize();

    System.out.printf("Encrypted JWE: %s%n",jweString);
    // now lets reverse it. Decrypt the JWE, and verify the contents.

//    JWEObject jweObj = JWEObject.parse(jweString);
    JWEDecrypter decrypter = new RSADecrypter(jwePrivateKey);
    jweObject.decrypt(decrypter);

    System.out.printf("Decrypted JWE. %n");
    Payload payload = jweObject.getPayload();

    SignedJWT jwt = payload.toSignedJWT();

    //verify the signature.
    jwt.verify(new RSASSAVerifier(jwtPublicKey));
    System.out.printf("Verified JWT %n");
    //get the claims
    JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();

    String userName = (String) jwtClaimsSet.getClaim("userName");
    //Viva.La.Vida !!

    System.out.printf("%s%n",userName);



  }
  public static void main(String... args) throws Exception {
    loadKeys();
    generateJWT();

  }
}
