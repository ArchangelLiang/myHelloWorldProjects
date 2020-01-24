package com.unknown.base.nio.other.buffer;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {

    public static void main(String[] args) {
        //初始化一个容量为5的intBuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            //添加数据
            intBuffer.put(i);
        }
        //读写切换
        intBuffer.flip();
        intBuffer.position(1);
        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println(i);
        }
    }

    public void readOnlyBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //只读buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
    }

    @Test
    public void mappedBuffer() throws Exception{
        RandomAccessFile accessFile = new RandomAccessFile("test_io2/testChannel.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, accessFile.length());
        mappedByteBuffer.put(9, (byte) 'z');
        mappedByteBuffer.put(10, (byte) 'i');
        mappedByteBuffer.put(11, (byte) 'o');
        channel.write(mappedByteBuffer);
        accessFile.close();
    }

}
