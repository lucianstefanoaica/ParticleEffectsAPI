package ro.uvt.pel;

import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_BLEND_DST;
import static javax.media.opengl.GL.GL_BLEND_EQUATION;
import static javax.media.opengl.GL.GL_BLEND_SRC;
import static javax.media.opengl.GL.GL_FUNC_ADD;
import static javax.media.opengl.GL.GL_ONE;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import ro.uvt.api.systems.CircleSystem;
import ro.uvt.api.systems.CylindricalSystem;
import ro.uvt.api.systems.FireworksSystem;
import ro.uvt.api.systems.FountainSystem;
import ro.uvt.api.systems.LineSystem;
import ro.uvt.api.systems.ParticleSystem;
import ro.uvt.api.systems.PulseSystem;
import ro.uvt.api.systems.ReversedSystem;
import ro.uvt.api.systems.SprayedSystem;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

public class PEL {

    private ParticleSystem system;
    private GL2 gl;

    private IntBuffer previousEquation;
    private IntBuffer previousSourceFactor;
    private IntBuffer previousDestinationFactor;
    private boolean blendWasEnabled;

    private List<ParticleSystem> systems = new ArrayList<>();

    public PEL(GL2 gl) {
	this.gl = gl;
    }

    public void pelDrawSprayedSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {
	setBlending();
	if (system instanceof SprayedSystem == false) {
	    system = new SprayedSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);
	unsetBlending();
    }

    public void pelDrawCylindricalSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {
	setBlending();
	if (system instanceof CylindricalSystem == false) {
	    system = new CylindricalSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);
	unsetBlending();
    }

    public void pelDrawReversedSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle) {
	setBlending();
	if (system instanceof ReversedSystem == false) {
	    system = new ReversedSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	}
	system.draw(angle);
	unsetBlending();
    }

    public void pelDrawFountainSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	setBlending();
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
	unsetBlending();
    }

    public void pelDrawLineSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	setBlending();
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
	unsetBlending();
    }

    public void pelDrawCircleSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	setBlending();
	if (system instanceof CircleSystem == false) {
	    system = new CircleSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	    system.setGravityVector(gravity);
	}
	system.draw(angle);
	unsetBlending();
    }

    public void pelDrawPulseSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	setBlending();
	if (system instanceof PulseSystem == false) {
	    system = new PulseSystem(gl, positions, texture, material,
		    systemRadius);
	    system.setParticlesPerSpawn(particlesPerSpawn);
	    system.setParticleRadius(particleRadius);
	    system.setFadeUnit(fadeUnit);
	    system.setScalar(scalar);
	    system.setGravityVector(gravity);
	    system.setPulsate(true);
	}
	system.draw(angle);
	unsetBlending();
    }

    public void pelDrawFireworksSystem(Vertex[] positions, Texture texture,
	    Material material, float systemRadius, int particlesPerSpawn,
	    float particleRadius, float fadeUnit, float scalar, float angle,
	    Vertex gravity) {
	setBlending();

	if (system instanceof FireworksSystem == false) {
	    systems.clear();

	    ParticleSystem aSystem = new FireworksSystem(gl, positions,
		    texture, material, systemRadius);
	    aSystem.setParticlesPerSpawn(particlesPerSpawn);
	    aSystem.setParticleRadius(particleRadius);
	    aSystem.setFadeUnit(fadeUnit);
	    aSystem.setScalar(scalar);
	    aSystem.setGravityVector(gravity);
	    aSystem.setPulsate(true);
	    systems.add(aSystem);

	    Vertex[] newPos = new Vertex[2];
	    newPos[0] = new Vertex(2.0f, 3.0f, 0.0f);
	    newPos[1] = new Vertex(2.0f, 3.0f, 0.0f);
	    aSystem = new FireworksSystem(gl, newPos, texture, material,
		    systemRadius);
	    aSystem.setParticlesPerSpawn(particlesPerSpawn);
	    aSystem.setParticleRadius(particleRadius);
	    aSystem.setFadeUnit(fadeUnit);
	    aSystem.setScalar(scalar);
	    aSystem.setGravityVector(gravity);
	    aSystem.setPulsate(true);
	    systems.add(aSystem);

	    Vertex[] anotherPos = new Vertex[2];
	    anotherPos[0] = new Vertex(-2.0f, 3.0f, 0.0f);
	    anotherPos[1] = new Vertex(-2.0f, 3.0f, 0.0f);
	    aSystem = new FireworksSystem(gl, anotherPos, texture, material,
		    systemRadius);
	    aSystem.setParticlesPerSpawn(particlesPerSpawn);
	    aSystem.setParticleRadius(particleRadius);
	    aSystem.setFadeUnit(fadeUnit);
	    aSystem.setScalar(scalar);
	    aSystem.setGravityVector(gravity);
	    aSystem.setPulsate(true);
	    systems.add(aSystem);
	}

	for (int i = 0; i < systems.size(); ++i) {
	    system = systems.get(i);
	    system.draw(angle);
	}
	unsetBlending();
    }

    private void setBlending() {
	previousEquation = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

	previousSourceFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

	previousDestinationFactor = Buffers.newDirectIntBuffer(1);
	gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

	blendWasEnabled = gl.glIsEnabled(GL_BLEND);

	gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	gl.glBlendEquation(GL_FUNC_ADD);
	gl.glEnable(GL_BLEND);
    }

    private void unsetBlending() {
	gl.glBlendFunc(previousSourceFactor.get(0),
		previousDestinationFactor.get(0));
	gl.glBlendEquation(previousEquation.get(0));
	if (blendWasEnabled == false) {
	    gl.glDisable(GL_BLEND);
	}
    }
}
