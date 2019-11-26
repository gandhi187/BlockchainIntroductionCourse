package de.sharma.ntublock;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.apache.commons.codec.binary.Hex;
/**
 * @author david.sharma@gmx.de Soulution to Task3_SecondTerm_Project Soulution
 *         in Java Created a JAVA wrapper class out of the PJ2.sol file within
 *         web3j-mvn-plugin
 *
 */
public class Task3_SendTransaction {
	private static final Logger LOGGER = LoggerFactory.getLogger(Task3_SendTransaction.class);

	public static void main(String[] args) {
		HexConverter hexConvert = new HexConverter();
		LOGGER.info("Connect to Ether...");
		// connect to a hosted ether node (ropsten testnet)
		Web3j web3 = Web3j.build(new HttpService("your endpoint here"));

		// credents
		Credentials credentials = Credentials
				.create("your private key here");

		// load the function from the wrapper class (PJ2)
		PJ2 registryContract = PJ2.load("0xC820cBdc60c879cB73Cdd895e7e89E796f6C6C16", web3, credentials,
				new DefaultGasProvider());
		
		PJ2 pj2;
		try {
			
			LOGGER.info("Deploying the contract....");
			pj2 = PJ2.deploy(web3, credentials,new DefaultGasProvider()).send();
			String contractAddress = pj2.getContractAddress();
			LOGGER.info("Works !, contract address is " + contractAddress);
			
			LOGGER.info("mhhh what the HEX ? " + hexConvert.calculateHex());
			
			Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
			LOGGER.info("Client Version " + clientVersion);
			String hex = hexConvert.calculateHex();
		
			// call the contract function and send the studentIT, hex and contractAddress
		TransactionReceipt	txReceipt = registryContract.Problem3("a08922101", hex,contractAddress).send();

			LOGGER.info("Reciep " + txReceipt);

		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
