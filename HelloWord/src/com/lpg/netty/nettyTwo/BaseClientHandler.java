package com.lpg.netty.nettyTwo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class BaseClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
