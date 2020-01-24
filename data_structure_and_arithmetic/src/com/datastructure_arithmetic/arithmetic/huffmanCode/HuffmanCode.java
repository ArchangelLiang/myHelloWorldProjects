package com.datastructure_arithmetic.arithmetic.huffmanCode;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class HuffmanCode {

    public static class Node implements Comparable<Node> {
        private Byte data;
        private int count;
        private Node left;
        private Node right;

        public Node(int count) {
            this.count = count;
        }

        public Node(byte data, int count) {
            this.data = data;
            this.count = count;
        }

        public void preOrderList() {
            System.out.println(this.data + "====>" + this.count);
            if (this.left != null) {
                this.left.preOrderList();
            }
            if (this.right != null) {
                this.right.preOrderList();
            }
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    public byte[] decodeToHuffmanCode(Map<Byte, String> codeMap, byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToBinaryStr(i != (bytes.length - 1), bytes[i]));
        }

        HashMap<String, Byte> inversionMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            inversionMap.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        int index = 0;
        int count = 1;
        while (index + count <= sb.length()) {
            String key = sb.substring(index, index + count);
            if (inversionMap.containsKey(key)) {
                list.add(inversionMap.get(key));
                index += count;
                count = 1;
            } else {
                count++;
            }
        }
        byte[] bs = new byte[list.size()];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = list.get(i);
        }
        return bs;
    }

    private String byteToBinaryStr(boolean flag, byte b) {
        if (flag) {
            String binaryString = Integer.toBinaryString((int) b | 256);
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return Integer.toBinaryString(b);
        }
    }

    public byte[] getHuffmanCodeBytes(byte[] bytes) {
        return getHuffmanCodeBytes(bytes, generateCode(bytes));
    }

    private byte[] getHuffmanCodeBytes(byte[] bytes, Map<Byte, String> codeMap) {
        byte[] huffmanCodeBytes = null;

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(codeMap.get(b));
        }
        huffmanCodeBytes = new byte[(sb.length() + 7) / 8];
        System.out.println(sb.toString());
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            if (i + 8 < sb.length()) {
                huffmanCodeBytes[index] = (byte) Integer.parseInt(sb.substring(i, i + 8), 2);
            } else {
                huffmanCodeBytes[index] = (byte) Integer.parseInt(sb.substring(i), 2);
            }
            index++;
        }

        return huffmanCodeBytes;
    }

    public Map<Byte, String> generateCode(byte[] bytes) {
        HashMap<Byte, String> map = new HashMap<>();
        generateCode(createHuffmanTree(getList(bytes)), map, "", new StringBuilder());
        return map;
    }

    private void generateCode(Node node, Map<Byte, String> codeMap, String pathCode, StringBuilder builder) {
        StringBuilder sb = new StringBuilder(builder);
        sb.append(pathCode);
        if (node.data != null) {
            codeMap.put(node.data, sb.toString());
        } else {
            if (node.left != null) {
                generateCode(node.left, codeMap, "0", sb);
            }
            if (node.right != null) {
                generateCode(node.right, codeMap, "1", sb);
            }
        }
    }

    public Node createHuffmanTree(List<Node> list) {

        Collections.sort(list);
        while (list.size() > 1) {
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.count + right.count);
            parent.left = left;
            parent.right = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
            Collections.sort(list);
        }

        return list.get(0);
    }

    public List<Node> getList(byte[] bytes) {
        ArrayList<Node> list = new ArrayList<>();

        HashMap<Byte, Integer> map = new HashMap<>();
        Integer count;
        for (byte b : bytes) {
            count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, ++count);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public void preOrderList(Node root) {
        if (root != null) {
            root.preOrderList();
        }
    }

    public static void main(String[] args) {

        String info = "i like like like java do you like a java";
        byte[] bytes = info.getBytes();
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] huffmanCodeBytes = huffmanCode.getHuffmanCodeBytes(bytes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        byte[] sourceBytes = huffmanCode.decodeToHuffmanCode(huffmanCode.generateCode(bytes), huffmanCodeBytes);
        System.out.println(new String(sourceBytes));
    }

    @Test
    public void test() throws Exception{
        File file = new File("D:\\Work\\IDEA\\finalStudy\\data_structure_and_arithmetic\\src\\com\\datastructure_arithmetic\\datastructure\\linkedList\\JosephuCircleLinkedList.java");
        FileInputStream inputStream = new FileInputStream(file);
        System.out.println(inputStream.available());
        System.out.println(file.length());
    }

}