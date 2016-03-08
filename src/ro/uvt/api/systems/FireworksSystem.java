package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class FireworksSystem extends ParticleSystem {

    public FireworksSystem(GL2 gl, Vertex[] positions, Texture texture,
	    Material material, float systemRadius) {
	super(gl, positions, texture, material, systemRadius);
	aBackupPosition = new Vertex(source.getPositionX(),
		source.getPositionY(), source.getPositionZ());
    }

    @Override
    protected void generateParticleDirectionVector() {
	Vertex pointInSphere = generatePointInSphere(destination,
		aBackupPosition, systemRadius);
	Vertex movementVector = Calculator.subtract(pointInSphere, source);

	speed = Calculator.scaleDown(movementVector, scalar);
	startPosition = source.clone();
    }
}
