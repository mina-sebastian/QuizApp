
package me.minutz.trv.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(byte[].class);
		kryo.register(DPacket.class);
	}

	static public class DPacket {
		public byte[] data;
	}
}
