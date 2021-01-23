package org.vision.visionjsdk.client.contract;

/**
 * The {@code Contract} class is a wrapper for the {@code SmartContract}
 * class defined in the .proto file. An object of {@code Contract} contains
 * same attributes with the {@code SmartContract} class.
 *
 * <p>This class provides mutator and accessor methods for all
 * attributes. Besides, functions of smart contracts are wrapped
 * and can be easily viewed through {@link
 * org.vision.visionjsdk.client.contract.ContractFunction#toString()}</>
 *
 * @since jdk 13.0.2+8
 * @see org.vision.visionjsdk.client.contract.ContractFunction
 * @see org.vision.visionjsdk.proto.Common.SmartContract;
 */

import com.google.protobuf.ByteString;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Common.SmartContract;
import org.vision.visionjsdk.proto.Common.SmartContract.ABI;
import org.vision.visionjsdk.proto.Common.SmartContract.ABI.Entry;
import org.vision.visionjsdk.proto.Common.SmartContract.ABI.Entry.Param;
import org.vision.visionjsdk.proto.Contract.CreateSmartContract;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    protected VisionClient client;
    protected ByteString originAddr = ByteString.EMPTY;
    protected ByteString cntrAddr = ByteString.EMPTY;
    protected ABI abi;
    protected ByteString bytecode;
    protected long callValue = 0;
    protected long consumeUserResourcePercent = 100;
    protected String name;
    protected long originEnergyLimit = 1;
    protected ByteString codeHash = ByteString.EMPTY;
    protected ByteString trxHash = ByteString.EMPTY;
    //Current transaction owner's address, to call or trigger contract"
    protected ByteString ownerAddr = ByteString.EMPTY;
    protected List<ContractFunction> functions = new ArrayList();

    public Contract(Contract cntr, String ownerAddr, VisionClient client) {
        this.originAddr = cntr.getOriginAddr();
        this.cntrAddr = cntr.getCntrAddr();
        this.abi = cntr.getAbi();
        this.bytecode = cntr.getBytecode();
        this.callValue = cntr.getCallValue();
        this.consumeUserResourcePercent = cntr.getConsumeUserResourcePercent();
        this.name = cntr.getName();
        this.originEnergyLimit = cntr.getOriginEnergyLimit();
        this.codeHash = cntr.getCodeHash();
        this.trxHash = cntr.getVsHash();
        this.ownerAddr = VisionClient.parseAddress(ownerAddr);
        this.client = client;
    }

    public Contract(ByteString cntrAddr, ABI abi, ByteString bytecode, long consumeUserResourcePercent, String name, long originEnergyLimit) {
        this.cntrAddr = cntrAddr;
        this.abi = abi;
        this.bytecode = bytecode;
        this.consumeUserResourcePercent = consumeUserResourcePercent;
        this.name = name;
        this.originEnergyLimit = originEnergyLimit;
    }

    public Contract(Builder builder) {
        this.originAddr = builder.originAddr;
        this.cntrAddr = builder.cntrAddr;
        this.abi = builder.abi;
        this.bytecode = builder.bytecode;
        this.callValue = builder.callValue;
        this.consumeUserResourcePercent = builder.consumeUserResourcePercent;
        this.name = builder.name;
        this.originEnergyLimit = builder.originEnergyLimit;
        this.ownerAddr = builder.ownerAddr;
        abiToFunctions();
    }

    public ByteString getOriginAddr() {
        return originAddr;
    }

    public void setOriginAddr(ByteString originAddr) {
        this.originAddr = originAddr;
    }

    public ByteString getCntrAddr() {
        return cntrAddr;
    }

    public void setCntrAddr(ByteString cntrAddr) {
        this.cntrAddr = cntrAddr;
    }

    public ABI getAbi() {
        return abi;
    }

    public void setAbi(ABI abi) {
        this.abi = abi;
    }

    public ByteString getBytecode() {
        return bytecode;
    }

    public void setBytecode(ByteString bytecode) {
        this.bytecode = bytecode;
    }

    public long getCallValue() {
        return callValue;
    }

    public void setCallValue(long callValue) {
        this.callValue = callValue;
    }

    public long getConsumeUserResourcePercent() {
        return consumeUserResourcePercent;
    }

    public void setConsumeUserResourcePercent(long consumeUserResourcePercent) {
        this.consumeUserResourcePercent = consumeUserResourcePercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOriginEnergyLimit() {
        return originEnergyLimit;
    }

    public void setOriginEnergyLimit(long originEnergyLimit) {
        this.originEnergyLimit = originEnergyLimit;
    }

    public ByteString getCodeHash() {
        return codeHash;
    }

    public void setCodeHash(ByteString codeHash) {
        this.codeHash = codeHash;
    }

    public ByteString getVsHash() {
        return trxHash;
    }

    public void setVsHash(ByteString trxHash) {
        this.trxHash = trxHash;
    }

    public ByteString getOwnerAddr() {
        return ownerAddr;
    }

    public void setOwnerAddr(ByteString ownerAddr) {
        this.ownerAddr = ownerAddr;
    }

    public List<ContractFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<ContractFunction> functions) {
        this.functions = functions;
    }

    public static class Builder {
        protected ByteString originAddr = ByteString.EMPTY;
        protected ByteString cntrAddr = ByteString.EMPTY;
        protected ABI abi;
        protected ByteString bytecode;
        protected long callValue = 0;
        protected long consumeUserResourcePercent = 100;
        protected String name;
        protected long originEnergyLimit = 1;
        protected ByteString codeHash = ByteString.EMPTY;
        protected ByteString trxHash = ByteString.EMPTY;
        protected ByteString ownerAddr = ByteString.EMPTY;

        public Builder setOriginAddr(ByteString originAddr) {
            this.originAddr = originAddr;
            return this;
        }

        public Builder setCntrAddr(ByteString cntrAddr) {
            this.cntrAddr = cntrAddr;
            return this;
        }

        public Builder setAbi(ABI abi) {
            this.abi = abi;
            return this;
        }

        public Builder setBytecode(ByteString bytecode) {
            this.bytecode = bytecode;
            return this;
        }

        public Builder setCallValue(long callValue) {
            this.callValue = callValue;
            return this;
        }

        public Builder setConsumeUserResourcePercent(long consumeUserResourcePercent) {
            this.consumeUserResourcePercent = consumeUserResourcePercent;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setOriginEnergyLimit(long originEnergyLimit) {
            this.originEnergyLimit = originEnergyLimit;
            return this;
        }

        public Builder setOwnerAddr(ByteString ownerAddr) {
            this.ownerAddr = ownerAddr;
            return this;
        }

        public Contract build() {
            return new Contract(this);
        }
    }

  /**
   * Convert abi entries to ContractFunction objects
   * @see org.vision.visionjsdk.proto.Common.SmartContract.ABI.Entry;
   * @see ContractFunction ;
   */
  protected void abiToFunctions() {
        int funcNum = abi.getEntrysCount();
        for (int i = 0; i < funcNum; i++) {
            Entry funcAbi = abi.getEntrysList().get(i);
            if (funcAbi.getTypeValue() == 2) {
                ContractFunction.Builder builder = new ContractFunction.Builder();
                builder.setName(funcAbi.getName());
                builder.setAbi(funcAbi);
                builder.setCntr(this);
                builder.setOwnerAddr(this.ownerAddr);
                //if has input
                if (0 != funcAbi.getInputsCount()) {
                    List params = funcAbi.getInputsList();
                    builder.setInputParams(collectParams(params, 'p'));
                    builder.setInputTypes(collectParams(params, 't'));
                }
                //if has output
                if (0 != funcAbi.getOutputsCount()) {
                    List<Param> params = funcAbi.getOutputsList();
                    if (null != params.get(0).getName()) {
                        builder.setOutput((String)collectParams(params, 'p').get(0));
                    }
                    builder.setOutputType((String)collectParams(params, 't').get(0));
                }
                functions.add(builder.build());
            }
        }
    }

    protected List<String> collectParams(List<Param> params, char flag) {
        List<String> ret = new ArrayList();
        switch (flag) {
            //p = param, t = type
            case 'p':
              for (Param p : params) {
                  ret.add(p.getName());
              }
              break;
            case 't':
              for (Param p : params) {
                  ret.add(p.getType());
              }
              break;
        }
        return ret;
    }

  /**
   * build a SmartContract object
   * @return SmartContract object
   */
  public SmartContract toProto() {
        return SmartContract.newBuilder()
                   .setOriginAddress(originAddr)
                   .setContractAddress(cntrAddr)
                   .setAbi(abi)
                   .setBytecode(bytecode)
                   .setCallValue(callValue)
                   .setConsumeUserResourcePercent(consumeUserResourcePercent)
                   .setName(name)
                   .setOriginEnergyLimit(originEnergyLimit)
                   .setVsHash(trxHash)
                   .build();
    }

    public CreateSmartContract deploy() {
        //No deposit when creating contract
        return deploy(0, 0);
    }

  /**
   * create a CreateSmartContract object to get ready for deployment
   * @param callTokenValue deposit amount while deploying
   * @param tokenId token id
   * @return CreateSmartContract object for signing and broadcasting
   */
    public CreateSmartContract deploy(long callTokenValue, long tokenId) {
        //throws if deployed
        if (this.cntrAddr.isEmpty()) {
            throw new RuntimeException("This contract has already been deployed.");
        }
        //throws if origin address does not match owner address
        if (!this.originAddr.equals(this.ownerAddr)) {
            throw new RuntimeException("Origin address and owner address mismatch.");
        }
        //create
        CreateSmartContract.Builder builder = CreateSmartContract.newBuilder();
        builder.setOwnerAddress(ownerAddr);
        builder.setNewContract(toProto());
        //if any deposit
        if (tokenId != 0) {
            builder.setTokenId(tokenId);
            builder.setCallTokenValue(callTokenValue);
        }

        return builder.build();
    }

    
}