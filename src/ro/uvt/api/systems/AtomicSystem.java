package ro.uvt.api.systems;

import java.util.Random;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class AtomicSystem extends ParticleSystem {

    private Random rand;

    private int type;

    public AtomicSystem(GL2 gl, Vertex[] positions, Texture texture,
	    Material material, float systemRadius) {
	super(gl, positions, texture, material, systemRadius);
	rand = new Random();
    }

    @Override
    protected void generateParticleDirectionVector() {
	startPosition = source.clone();
	speed = generatePointOnCircle();
    }

    private Vertex generatePointOnCircle() {
	float angle = rand.nextFloat() * 2 * (float) Math.PI;
	Vertex pointOnCircle = null;

	switch (type) {
	case 0:
	    pointOnCircle = new Vertex((float) Math.cos(angle), 0.0f,
		    (float) Math.sin(angle));
	    break;

	case 1:
	    pointOnCircle = new Vertex((float) Math.cos(angle),
		    (float) Math.cos(angle), (float) Math.sin(angle));
	    break;

	case 2:
	    pointOnCircle = new Vertex((float) Math.cos(angle),
		    -(float) Math.cos(angle), (float) Math.sin(angle));
	    break;

	case 3:
	    pointOnCircle = new Vertex(0.0f, (float) Math.cos(angle),
		    (float) Math.sin(angle));
	    break;

	}

	return Calculator.scaleDown(pointOnCircle, systemRadius);
    }

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }
}