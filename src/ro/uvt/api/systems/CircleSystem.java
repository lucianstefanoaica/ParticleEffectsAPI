package ro.uvt.api.systems;

import java.util.Random;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class CircleSystem extends ParticleSystem {

    private Random rand;

    public CircleSystem(GL2 gl, Vertex[] positions, Texture texture,
	    Material material, float systemRadius) {
	super(gl, positions, texture, material, systemRadius);
	rand = new Random();
    }

    @Override
    protected void generateParticleDirectionVector() {
	startPosition = generatePointInCircle(source, systemRadius);
	speed = new Vertex(0.0f, 0.0f, 0.0f);
    }

    private Vertex generatePointInCircle(Vertex center, float radius) {
	float angle = rand.nextFloat() * 2 * (float) Math.PI;

	Vertex pointOnCircle = new Vertex((float) Math.cos(angle), 0.0f,
		(float) Math.sin(angle));

	Vertex scaledPoint = Calculator.scaleUp(pointOnCircle, radius);

	return Calculator.scaleUp(scaledPoint, rand.nextFloat()).add(center);
    }
}
