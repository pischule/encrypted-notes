package bsu.pischule.company;

import java.math.BigInteger;
import java.util.Random;


public class RSA {
   public BigInteger publicKey;
   public BigInteger privateKey;
   BigInteger n;
   BigInteger phi;
   Random rand = new Random(2);

   public RSA(int bitLength)
   {
      //*****************************************************************должны совпадать
//      System.out.println(rand.nextInt());

      // 1. Find large primes p and q
      BigInteger p = largePrime(bitLength);
      BigInteger q = largePrime(bitLength);

      // 2. Compute n from p and q
      // n is mod for private & public keys, n bit length is key length
      n = n(p, q);

      // 3. Compute Phi(n) (Euler's totient function)
      // Phi(n) = (p-1)(q-1)
      // BigIntegers are objects and must use methods for algebraic operations
      phi = getPhi(p, q);
   }

   public  void generateKeys(){
      // 4. Find an int e such that 1 < e < Phi(n) 	and gcd(e,Phi) = 1
      publicKey = genE(phi);

      // 5. Calculate d where  d ≡ e^(-1) (mod Phi(n))
      privateKey = extEuclid(publicKey, phi)[1];
   }

   public BigInteger encrypt(String message, BigInteger pubk)
   {
      BigInteger cipherMessage = stringCipher(message);
      BigInteger encrypted = encrypt(cipherMessage, pubk, n);
      return (encrypted);
   }

   public String decrypt(BigInteger encrypted, BigInteger prvk)
   {
      // Decrypt the encrypted message
      BigInteger decrypted = decrypt((encrypted), prvk, n);
      // Uncipher the decrypted message to text
      return cipherToString(decrypted);
   }

   /**
    * Takes a string and converts each character to an ASCII decimal value
    * Returns BigInteger
    */
   public  BigInteger stringCipher(String message) {
      message = message.toUpperCase();
      String cipherString = "";
      int i = 0;
      while (i < message.length()) {
         int ch = (int) message.charAt(i);
         cipherString = cipherString + ch;
         i++;
      }
      BigInteger cipherBig = new BigInteger(String.valueOf(cipherString));
      return cipherBig;
   }


   /**
    * Takes a BigInteger that is ciphered and converts it back to plain text
    *	returns a String
    */
   public  String cipherToString(BigInteger message) {
      String cipherString = message.toString();
      String output = "";
      int i = 0;
      while (i < cipherString.length()) {
         int temp = Integer.parseInt(cipherString.substring(i, i + 2));
         char ch = (char) temp;
         output = output + ch;
         i = i + 2;
      }
      return output;
   }


   /** 3. Compute Phi(n) (Euler's totient function)
    *  Phi(n) = (p-1)(q-1)
    *	BigIntegers are objects and must use methods for algebraic operations
    */
   public  BigInteger getPhi(BigInteger p, BigInteger q) {
      BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
      return phi;
   }

   /**
    * Generates a random large prime number of specified bitlength
    *
    */
   public  BigInteger largePrime(int bits) {
//      Random randomInteger = new Random();
      BigInteger largePrime = BigInteger.probablePrime(bits, rand);
      return largePrime;
   }


   /**
    * Recursive implementation of Euclidian algorithm to find greatest common denominator
    * Note: Uses BigInteger operations
    */
   public  BigInteger gcd(BigInteger a, BigInteger b) {
      if (b.equals(BigInteger.ZERO)) {
         return a;
      } else {
         return gcd(b, a.mod(b));
      }
   }


   /** Recursive EXTENDED Euclidean algorithm, solves Bezout's identity (ax + by = gcd(a,b))
    * and finds the multiplicative inverse which is the solution to ax ≡ 1 (mod m)
    * returns [d, p, q] where d = gcd(a,b) and ap + bq = d
    * Note: Uses BigInteger operations
    */
   public  BigInteger[] extEuclid(BigInteger a, BigInteger b) {
      if (b.equals(BigInteger.ZERO)) return new BigInteger[] {
              a, BigInteger.ONE, BigInteger.ZERO
      }; // { a, 1, 0 }
      BigInteger[] vals = extEuclid(b, a.mod(b));
      BigInteger d = vals[0];
      BigInteger p = vals[2];
      BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
      return new BigInteger[] {
              d, p, q
      };
   }


   /**
    * generate e by finding a Phi such that they are coprimes (gcd = 1)
    *
    */
   public  BigInteger genE(BigInteger phi) {

      BigInteger e = new BigInteger(1024, rand);
      do {
         e = new BigInteger(1024, rand);
         while (e.min(phi).equals(phi)) { // while phi is smaller than e, look for a new e
            e = new BigInteger(1024, rand);
         }
      } while (!gcd(e, phi).equals(BigInteger.ONE)); // if gcd(e,phi) isnt 1 then stay in loop
      return e;
   }

   public  BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
      return message.modPow(e, n);
   }

   public  BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
      return message.modPow(d, n);
   }

   public  BigInteger n(BigInteger p, BigInteger q) {
      return p.multiply(q);

   }
}