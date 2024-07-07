package org.timetorunserver.entities;

import org.timetorunserver.space.Graph;

public class Entity {
    int now, to;
    long beginMoving;
    double speed, pos;
    Graph graph;
    public Entity(int vert, Graph graph, double speed){
        this.now = vert;
        this.graph = graph;
        this.speed = speed;
        this.to = vert;
        this.beginMoving = -1;
    }

    public void act(double dt){
        if(beginMoving == -1) return;
        pos += speed * dt;
        if(pos >= 1.0){
            beginMoving = -1;
            now = to;
        }
    }
    public void move(int to, long time){
        if(beginMoving != -1 || graph.isEdge(now, to)){
            return;
        }
        beginMoving = time;
        this.to = to;
        pos = 0.0;
    }

    public boolean touched(Entity other){
        if(other.pos == this.pos && other.now == this.now){
            return true;
        }else if(this.beginMoving == -1){
            return false;
        }else if(other.now == this.now && other.to == this.to &&
                (
                        (this.pos < other.pos && this.beginMoving > other.beginMoving)
                        || (this.pos > other.pos && this.beginMoving < other.beginMoving)
                )
        )
        {
            return true;
        }else if(other.now == this.to && other.to == this.now && this.pos + other.pos >= 1.0){
            return true;
        }else{
            return false;
        }
    }

}
