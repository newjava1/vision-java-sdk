/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.vision.visionjsdk.abi.FunctionEncoder;
import org.vision.visionjsdk.abi.FunctionReturnDecoder;
import org.vision.visionjsdk.abi.TypeReference;
import org.vision.visionjsdk.abi.datatypes.generated.Bytes10;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.abi.datatypes.generated.Uint32;
import org.vision.visionjsdk.abi.datatypes.*;
import org.vision.visionjsdk.client.contract.Contract;
import org.vision.visionjsdk.client.contract.ContractFunction;
import org.vision.visionjsdk.client.transaction.TransactionBuilder;
import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.proto.Chain.Transaction;
import org.vision.visionjsdk.proto.Contract.TriggerSmartContract;
import org.vision.visionjsdk.proto.Response.BlockExtention;
import org.vision.visionjsdk.proto.Response.BlockListExtention;
import org.vision.visionjsdk.proto.Response.TransactionExtention;
import org.vision.visionjsdk.proto.Response.TransactionReturn;
import org.vision.visionjsdk.abi.datatypes.Function;
import org.vision.visionjsdk.abi.datatypes.StaticArray;
import org.vision.visionjsdk.abi.datatypes.Type;
import org.vision.visionjsdk.abi.datatypes.generated.Uint256;
import org.vision.visionjsdk.api.GrpcAPI.BytesMessage;

import java.math.BigInteger;
import java.util.*;

import org.vision.visionjsdk.proto.Chain.Block;
import org.vision.visionjsdk.proto.Chain.BlockHeader;

import org.vision.visionjsdk.proto.Response.DelegatedResourceAccountIndex;
import org.vision.visionjsdk.utils.Base58Check;
import com.google.protobuf.ByteString;
import demo.vrc20.Vrc20Demo;

public class App {
    public String encodeFunctionCalling() {
        System.out.println("! function sam(bytes _, bool _, address _, uint[])");
        Function function = new Function("sam",
                Arrays.asList(new DynamicBytes("dave".getBytes()), new Bool(true),
                        new Address("VNTxQP1qojBwiMkVfjfwcZ9vj7LF3DRQPn"),
                        new DynamicArray<>(
                                new Uint(BigInteger.ONE), new Uint(BigInteger.valueOf(2)), new Uint(BigInteger.valueOf(3)))),
                Collections.emptyList());
        String encodedHex = FunctionEncoder.encode(function);
        return encodedHex;
    }

    public void decodeFunctionReturn() {
        Function function = new Function("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", Collections.<Type>emptyList(),
                Arrays.asList(new TypeReference<Uint>() {
                }, new TypeReference<Address>() {
                }));

        List<Type> outputs =
                FunctionReturnDecoder.decode("0000000000000000000000000000000000000000000000000000000000000037"
                                + "00000000000000000000000028263f17875e4f277a72f6c6910bb7a692108b3e",
                        function.getOutputParameters());
        for (Type obj : outputs) {
            System.out.println(obj.getTypeAsString() + "  " + obj.toString());
            if (Uint.class.isInstance(obj)) {
                System.out.println("  parsed value => " + ((Uint) obj).getValue());
            }
        }
        // assertEquals(outputs,
        //    (Arrays.asList(new Uint(BigInteger.valueOf(55)), new Uint(BigInteger.valueOf(7)))));
    }

    /*public void signTransaction() {
        System.out.println("============= signTransaction =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            client.signTransaction(, 1_000_000L, 3L,1);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void vrc20Encode() {
        Function vrc20Transfer = new Function("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i",
                Arrays.asList(new Address("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"),
                        new Uint256(BigInteger.valueOf(1000).multiply(BigInteger.valueOf(10).pow(18)))),
                Arrays.asList(new TypeReference<Bool>() {
                }));

        String encodedHex = FunctionEncoder.encode(vrc20Transfer);
        System.out.println("! encoding a VRC20 transfer");
        System.out.println(encodedHex);
    }


    /*public void sendVs() {
        System.out.println("============= VRC transfer =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transactionExtention = client.transfer("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP", 1_000_000);
            Transaction signedTxn = client.signTransaction(transactionExtention);
            System.out.println(signedTxn.toString());
            //broadcast transactionx
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void sendVs() {
        System.out.println("============= VRC transfer =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.transfer("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP", 1_000_000);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
    /*public void transferVrc10(){
        System.out.println("============= transferVrc10 =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transactionExtention = client.transferVrc10("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP",
                    1000002, 1_000_000);
            Transaction signedTxn = client.signTransaction(transactionExtention);
            System.out.println(signedTxn.toString());
            //broadcast transactionx
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void transferVrc10() {
        System.out.println("============= transferVrc10 =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transactionExtention = client.transferVrc10("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i",
                    1000002, 1_000_000);
            Transaction signedTxn = client.signTransaction(transactionExtention);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    /*public void freezeBalance() {
        System.out.println("============= freeze balance =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.freezeBalance("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", 1_000_000L, 3L,1);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            //broadcast transactionx
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void freezeBalance() {
        System.out.println("============= freeze balance =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.freezeBalance("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", 1_000_000L, 3L, 1, "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP");
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void unFreezeBalance() {
        System.out.println("============= unFreeze balance =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.unfreezeBalance("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", 1, "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP");
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getBlockByNum() {
        System.out.println("============= getBlockByNum =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            Block block = client.getBlockByNum(10);
            System.out.println(block);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    /*public void getBlockByLatestNumSolidity() {
        System.out.println("============= getBlockByNum =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            BlockListExtention block = client.getBlockByLatestNumSolidity(1);
            System.out.println(block);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void getNowBlock() {
        System.out.println("============= getNowBlock =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getNowBlock());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getNowBlockSolidity() {
        System.out.println("============= getNowBlockSolidity =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getNowBlockSolidity());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getNodeInfo() {
        System.out.println("============= getNodeInfo=============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getNodeInfo());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void listNodes() {
        System.out.println("============= listNodes=============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.listNodes());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getTransactionInfoByBlockNum() {
        System.out.println("============= getTransactionInfoByBlockNum =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getTransactionInfoByBlockNum(-100));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    // public void sendVrc20Transaction() {
    //     System.out.println("============ VRC20 transfer =============");
    //     // Any of `ofVtest`, `ofMainnet`.
    //     VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");

    //     // transfer(address _to,uint256 _amount) returns (bool)
    //     // _to = VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP
    //     // _amount = 10 * 10^18
    //     Function vrc20Transfer = new Function("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i",
    //         Arrays.asList(new Address("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"),
    //             new Uint256(BigInteger.valueOf(10).multiply(BigInteger.valueOf(10).pow(18)))),
    //         Arrays.asList(new TypeReference<Bool>() {}));

    //     String encodedHex = FunctionEncoder.encode(vrc20Transfer);
    //     TriggerSmartContract trigger =
    //         TriggerSmartContract.newBuilder()
    //             .setOwnerAddress(VisionClient.parseAddress("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"))
    //             .setContractAddress(VisionClient.parseAddress("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i")) // JST
    //             .setData(VisionClient.parseHex(encodedHex))
    //             .build();

    //     System.out.println("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i" + trigger);

    //     TransactionExtention txnExt = client.blockingStub.triggerContract(trigger);
    //     System.out.println("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i" + VisionClient.toHex(txnExt.getTxid().toByteArray()));

    //     Transaction unsignedTxn = txnExt.getTransaction.toBuilder()
    //         .setRawData(txnExt.getTransaction().getRawData().toBuilder().setFeeLimit(10000000L))
    //         .build();

    //     Transaction signedTxn = client.signTransaction(unsignedTxn);

    //     System.out.println(signedTxn.toString());
    //     TransactionReturn ret = client.blockingStub.broadcastTransaction(signedTxn);
    //     System.out.println("======== Result ========\n" + ret.toString());
    // }

    /*public void transferVrc20() {
        // Any of `ofVtest`, `ofMainnet`.
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            //JST transfer
            client.transferVrc20("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", 1000000000L, 10L, 18);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void getSmartContract() {
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {

            Contract cntr = client.getContract("4648dd91ff64c11eba559e9f49c5094c97e43c6ab9");
            System.out.println("Contract name: " + cntr.getName());
            // System.out.println("Contract ABI: " + cntr.getAbi());
            System.out.println("Contract functions: " + cntr.getFunctions().size());
            for (ContractFunction cf : cntr.getFunctions()) {
                System.out.println(cf.toString());
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getTransactionInfoById() {
        System.out.println("============= getTransactionInfoById =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getTransactionInfoById("683eb52596556af9ebd3ddb93d0d7bf60aa2e71a2314a825a8f71abafcee9345"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getTransactionById() {
        System.out.println("============= getTransactionById =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getTransactionById("683eb52596556af9ebd3ddb93d0d7bf60aa2e71a2314a825a8f71abafcee9345"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getTransactionByIdSolidity() {
        System.out.println("============= getTransactionByIdSolidity =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getTransactionByIdSolidity("683eb52596556af9ebd3ddb93d0d7bf60aa2e71a2314a825a8f71abafcee9345"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    /**
     * This is a constant call demo
     */
    public void viewContractName() {
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            Function viewName = new Function("name", Collections.emptyList(), Collections.emptyList());
            TransactionExtention txnExt = client.constantCall("VNTxQP1qojBwiMkVfjfwcZ9vj7LF3DRQPn", "VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", viewName);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }


    public void getAccount() {
        System.out.println("============= getAccount =============");
//        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        VisionClient client = VisionClient.ofSelfFullNode("3333333333333333333333333333333333333333333333333333333333333333", "3.22.117.236:50051", "3.22.117.236:50061");

        try {
            System.out.println(client.getAccount("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i").getAccountName());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void updateAccount() {
        System.out.println("============= updateAccount =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.updateAccount("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "xxx");
            System.out.println(transaction);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void createAccount() {
        System.out.println("============= createAccount =============");
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            TransactionExtention transaction = client.createAccount("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP");
            System.out.println(transaction);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAccountResource() {
        System.out.println("============= getAccountResource =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAccountResource("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAccountPhoton() {
        System.out.println("============= getAccountPhoton =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAccountPhoton("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getDelegatedResource() {
        System.out.println("============= getDelegatedResource =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {

            System.out.println(client.getDelegatedResource("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VXPGrussMYszd1cxRYne9vcGcnQSo1sBjP"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getDelegatedResourceAccountIndex() {
        System.out.println("============= getDelegatedResourceAccountIndex =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            DelegatedResourceAccountIndex accountIndex = client.getDelegatedResourceAccountIndex("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i");
            ByteString account = accountIndex.getAccount();

            System.out.println("Accounts: " + Base58Check.bytesToBase58(client.parseAddress(client.toHex(account)).toByteArray()));

            int fromAccountsCount = accountIndex.getFromAccountsCount();
            for (int i = 0; i < fromAccountsCount; i++) {
                ByteString fromAccounts = accountIndex.getFromAccounts(i);
                System.out.println("fromAccounts: " + Base58Check.bytesToBase58(client.parseAddress(client.toHex(fromAccounts)).toByteArray()));
            }

            int toAccountsCount = accountIndex.getToAccountsCount();
            for (int i = 0; i < toAccountsCount; i++) {
                ByteString toAccounts = accountIndex.getToAccounts(i);
                System.out.println("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i" + Base58Check.bytesToBase58(client.parseAddress(client.toHex(toAccounts)).toByteArray()));
            }

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAccountSolidity() {
        System.out.println("============= getAccountSolidity =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAccountSolidity("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getRewardSolidity() {
        System.out.println("============= getRewardSolidity =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getRewardSolidity("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }


    public void listWitnesses() {
        System.out.println("============= listWitnesses =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.listWitnesses());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void listExchanges() {
        System.out.println("============= listExchanges =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.listExchanges());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    /*public void voteWitness(){
        System.out.println("============= voteWitness =============");
        HashMap<String, String> witness = new HashMap<>();
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            witness.put("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i","1");
            TransactionExtention transaction = client.voteWitness("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i",witness);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }*/

    public void voteWitness() {
        System.out.println("============= voteWitness =============");
        HashMap<String, String> witness = new HashMap<>();
        VisionClient client = VisionClient.ofVtest("bf9a7e2287ca47757c43230ab1844f4f26b27cf53933c09362158a37dace6d05");
        try {
            witness.put("VFnr6qYKsEvJY3h4iY8FwNESgYNeyzvxNc", "1");
            TransactionExtention transaction = client.voteWitness("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", witness);
            Transaction signedTxn = client.signTransaction(transaction);
            System.out.println(signedTxn.toString());
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    /**
     * This is a trigger call - transfer vrc-20 demo
     */
    public void triggerCallDemo() {
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            //function 'transfer'
            //params: function name, function params
            Function vrc20Transfer = new Function("transfer",
                    Arrays.asList(new Address("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"),
                            new Uint256(BigInteger.valueOf(10L).multiply(BigInteger.valueOf(10).pow(18)))),
                    Arrays.asList(new TypeReference<Bool>() {
                    }));

            //the params are: owner address, contract address, function
            TransactionBuilder builder = client.triggerCall("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i", "VNTxQP1qojBwiMkVfjfwcZ9vj7LF3DRQPn", vrc20Transfer); //JST
            //set extra params
            builder.setFeeLimit(100000000L);
            builder.setMemo("Let's go!");
            //sign transaction
            Transaction signedTxn = client.signTransaction(builder.build());
            System.out.println(signedTxn.toString());
            //broadcast transaction
            TransactionReturn ret = client.broadcastTransaction(signedTxn);
            System.out.println("======== Result ========\n" + ret.toString());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void generateAddress() {
        System.out.println("============= generateAddress =============");
        try {
            System.out.println(VisionClient.generateAddress());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getBlockByLatestNum() {
        System.out.println("============= getBlockByLatestNum =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            BlockListExtention blockListExtention = client.getBlockByLatestNum(100);
            System.out.println(blockListExtention);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getBlockByLimitNext() {
        System.out.println("============= getBlockByLimitNext =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            BlockListExtention blockListExtention = client.getBlockByLimitNext(-11, 0);
            System.out.println(blockListExtention);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getChainParameters() {
        System.out.println("============= getChainParameters =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getChainParameters());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAssetIssueList() {
        System.out.println("============= getAssetIssueList =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAssetIssueList());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getPaginatedAssetIssueList() {
        System.out.println("============= getPaginatedAssetIssueList =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getPaginatedAssetIssueList(0, 20));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAssetIssueByAccount() {
        System.out.println("============= getAssetIssueByAccount =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAssetIssueByAccount("VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getAssetIssueById() {
        System.out.println("============= getAssetIssueById =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getAssetIssueById("1000134"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void listProposals() {
        System.out.println("============= listProposals =============");
        VisionClient client = VisionClient.ofVtest("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.listProposals());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    //1-17
    public void getProposalById() {
        System.out.println("============= getProposalById =============");
        VisionClient client = VisionClient.ofMainnet("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getProposalById("15"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void getExchangeById() {
        System.out.println("============= getExchangeById =============");
        VisionClient client = VisionClient.ofMainnet("3333333333333333333333333333333333333333333333333333333333333333");
        try {
            System.out.println(client.getExchangeById("500"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void createAssetIssue() {
        System.out.println("============= createAssetIssue =============");
//        VisionClient client = VisionClient.ofNile("5bffa78c05e58d25e6d9fca69ece106678b5acdb558fd329972f0e860f27974f");
        try {
            VisionClient client = VisionClient.ofVtest("5176b5624639dc479272605eb0e8feb54e3ff7104cfec4dd2b0faca33750fd99");
            long start = 1711483775000l;
            long end = 1812483780000l;

            TransactionExtention transaction = client.createAssetIssue("VXdkWTQcngyYnUxpMNXX7k8SskgwLmPcRa", "lsp1", "saf1",
                    100000000L, 1, 1000, start, end, "www.baidu.com",
                    100000L, 1L, 6, 1L, 1L, "stest-assetissue");
            String json = JSON.toJSONString(transaction);
            System.out.println("======== Result ========\n" + json);
            //           Transaction signedTxn = client.signTransaction(transaction);
//            TransactionReturn ret = client.broadcastTransaction(signedTxn);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }


    public static void main(String[] args) {
        App app = new App();
        app.createAssetIssue();
        // System.out.println(app.encodeFunctionCalling());

//         app.getAccountResource();
        // app.getAccountPhoton();
//         app.getDelegatedResource();
//         app.getDelegatedResourceAccountIndex();
        // app.getChainParameters();
        // app.getAssetIssueList();
        // app.getPaginatedAssetIssueList();
        // app.getAssetIssueByAccount();
        // app.getAssetIssueById();
        // app.listProposals();
        // app.listWitnesses();
        // app.listExchanges();
        // app.getExchangeById();
        // app.getProposalById();
        // app.getAccount();
        // app.voteWitness();
        // app.updateAccount();
        // app.createAccount();
        // app.decodeFunctionReturn();
        // app.signTransaction();
        // app.vrc20Encode();
//         app.sendVs();
        // app.transferVrc10();
        // app.sendVrc20();
//         app.freezeBalance();
//         app.unFreezeBalance();
        // app.getBlockByNum();
        // app.getNowBlock();
        // app.getNodeInfo();
        // app.getBlockByLatestNum();
        // app.listNodes();
        // app.getTransactionInfoByBlockNum();
//         app.getTransactionInfoById();
//         app.getAccount();
//        // app.voteWitness();
//        // app.transferVrc20();
//         app.getSmartContract();
//        // app.viewContractName();
//        // app.triggerCallDemo();
//        // app.getAccountSolidity();
//        // app.getTransactionByIdSolidity();
//         app.generateAddress();
//        // app.getNowBlockSolidity();
//        // app.getRewardSolidity();
//
//         Vrc20Demo vrc20Demo = new Vrc20Demo();
//
//         vrc20Demo.getName();
//         vrc20Demo.getSymbol();
//         vrc20Demo.getDecimals();
//         vrc20Demo.getTotalSupply();
//         vrc20Demo.getBalanceOf();
//         vrc20Demo.transfer();
//         vrc20Demo.transferFrom();
//         vrc20Demo.approve();
//         vrc20Demo.getAllowance();
    }
}