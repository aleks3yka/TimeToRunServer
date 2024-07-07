package org.timetorunserver.entities;

import org.timetorunserver.space.Graph;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Player extends Entity {
    InetAddress address;
    int port;
    public Player(int vert, Graph graph, double speed) {
        super(vert, graph, speed);
    }
    public void init(DatagramPacket packet){
        this.address = packet.getAddress();
        this.port = packet.getPort();
    }
}
