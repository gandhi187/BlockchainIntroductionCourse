pragma solidity >=0.4.22 <0.6.0;

contract PJ2 {
	mapping(string=>int) public score;

	mapping(string=>bool) public isP1Submit;
	function Problem1(string memory ID) public payable{ // pay at least 3 gwei to submit problem1 
		assert(msg.value >= 3000000000);// 3 gwei
		if(isP1Submit[ID] == false){
			score[ID] += 10;
		}
		isP1Submit[ID] = true;
	}

	mapping(string=>bool) public isP2Submit;
	mapping(string=>string) public ID2P2Hex;
	function Problem2(string memory ID, string memory HashedHex) public payable{// pay at least 3 gwei by program with its hexdigest
		assert(msg.value >= 3000000000);//3 gwei
		assert(bytes(HashedHex).length == 64);// check if it's really a hexdigest
		if(isP2Submit[ID] == false){
			score[ID] += 10;
		}
		isP2Submit[ID] = true;
		ID2P2Hex[ID] = HashedHex;
	}

	mapping(string=>bool) public isP3Submit;
	mapping(string=>address) public ID2address;
	mapping(string=>string) public ID2P3Hex;
	function Problem3(string memory ID, string memory HashedHex, address yourContract) public { // deploy your contract and send it here by program with its hexdigest
		if(isP3Submit[ID] == false){
			score[ID] += 10;
		}
		isP3Submit[ID] = true;
		ID2P3Hex[ID] = HashedHex;
		ID2address[ID] = yourContract;
	}

	function random() private view returns (uint8) {
		return uint8(uint256(keccak256(abi.encodePacked(block.timestamp, block.difficulty)))%251);
	}
	mapping(string=>bool) public isBonusSubmit;
	function Bonus(string memory ID, uint8 key) public {
		assert(key == random());
		if(isBonusSubmit[ID] == false){
			isBonusSubmit[ID] = true;
			score[ID] += 20;
		}
	} 
}