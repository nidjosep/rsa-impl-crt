package org.unb.rsa;

import org.unb.rsa.models.KeyPair;

import java.math.BigInteger;
import java.util.Random;

public class RSAImplCRT {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("19211916981990472618936322908621863986876987146317321175477459636156953561475008733870517275438245830106443145241548501528064000686696553079813968930084003413592173929258239545538559059522893001415540383237712787805857248668921475503029012210091798624401493551321836739170290569343885146402734119714622761918874473987849224658821203492683692059569546468953937059529709368583742816455260753650612502430591087268113652659115398868234585603351162620007030560547611");
        BigInteger q = new BigInteger("49400957163547757452528775346560420645353827504469813702447095057241998403355821905395551250978714023163401985077729384422721713135644084394023796644398582673187943364713315617271802772949577464712104737208148338528834981720321532125957782517699692081175107563795482281654333294693930543491780359799856300841301804870312412567636723373557700882499622073341225199446003974972311496703259471182056856143760293363135470539860065760306974196552067736902898897585691");

        //key generation
        KeyGen keyGen = new KeyGen(p, q);
        KeyPair keyPair = keyGen.init();

        //encryption
        BigInteger m = new BigInteger(keyGen.getPhiOfn().bitLength() - 1, new Random());
        BigInteger c = Encrypt.encryptMessage(m, keyPair.getRsaPublicKey());

        System.out.printf("\nChosen message is m = %s\n", m);
        System.out.printf("Ciphertext is c = %s\n", c);

        //decryption - tracking execution time in milliseconds
        Decrypt.decryptCipher(c, keyPair.getRsaPrivateKey());
        Decrypt.decryptCipherUsingCRT(c, keyPair.getRsaPrivateKey());

        System.out.println("----------------------------");
    }
}