package com.ivaneye.zoro.rpc.client;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		/*
		 * ����ط��� ����ͷ���˶�Ӧ�ϡ������޷���������ͱ���
		 * 
		 * ����ͱ��� �ҽ�������һ��Ϊ�����ϸ�Ľ��⡣�ٴ���ʱ������ϸ������
		 */
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
		// �ͻ��˵��߼�
		pipeline.addLast("handler", new ClientHandler());
	}
}