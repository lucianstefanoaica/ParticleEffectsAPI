package ro.uvt.pel;

import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_BLEND_DST;
import static javax.media.opengl.GL.GL_BLEND_EQUATION;
import static javax.media.opengl.GL.GL_BLEND_SRC;
import static javax.media.opengl.GL.GL_FUNC_ADD;
import static javax.media.opengl.GL.GL_ONE;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

import java.nio.IntBuffer;

import javax.media.opengl.GL2;

import ro.uvt.api.systems.CylindricalSystem;
import ro.uvt.api.systems.FountainSystem;
import ro.uvt.api.systems.LineSystem;
import ro.uvt.api.systems.ParticleSystem;
import ro.uvt.api.systems.ReversedSystem;
import ro.uvt.api.systems.SprayedSystem;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

public class PEL {

    private ParticleSystem system;
    private GL2 gl;

    public PEL(GL2 gl) {
	this.gl = gl;
    }

    public void pelDrawSprayedSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {

	IntBuffer previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	IntBuffer previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	IntBuffer previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	boolean blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);

	if (system instanceof SprayedSystem == false) {
	    system = new SprayedSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);

	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }

    public void pelDrawCylindricalSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {

	IntBuffer previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	IntBuffer previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	IntBuffer previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	boolean blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);

	if (system instanceof CylindricalSystem == false) {
	    system = new CylindricalSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);

	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }

    public void pelDrawReversedSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {

	IntBuffer previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	IntBuffer previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	IntBuffer previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	boolean blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);

	if (system instanceof ReversedSystem == false) {
	    system = new ReversedSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);

	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }

    public void pelDrawFountainSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {

	IntBuffer previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	IntBuffer previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	IntBuffer previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	boolean blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);

	if (system instanceof FountainSystem == false) {
	    system = new FountainSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	    system.setGravityVector(gravity);
	}
	system.draw(angle);

	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }

    public void pelDrawLineSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	IntBuffer previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	IntBuffer previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	IntBuffer previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	boolean blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);

	if (system instanceof LineSystem == false) {
	    system = new LineSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	    system.setGravityVector(gravity);
	}
	system.draw(angle);

	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }
}
