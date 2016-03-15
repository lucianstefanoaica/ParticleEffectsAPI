package ro.uvt.pel.util;

import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;

import javax.media.opengl.GL2;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class Particle {

    private GL2 gl;
    private Vertex leftBottom;
    private Vertex rightBottom;
    private Vertex rightTop;
    private Vertex leftTop;
    private Texture texture;
    private float fadeUnit;
    private Material material;
    private Vertex gravityVector = new Vertex(0.0f, 0.0f, 0.0f);

    private float particleRadius;
    private Vertex particlePosition;
    private Vertex speed;
    private float lifespan;

    public Particle(GL2 gl, Vertex position, Vertex speed, float cameraAngle,
	    Texture texture, float radius, float fadeUnit, Material material) {
	this.gl = gl;
	this.particlePosition = position;
	this.speed = speed;
	this.particleRadius = radius;
	this.material = material;
	this.texture = texture;
	this.fadeUnit = fadeUnit;
	lifespan = 1.0f;
    }

    public void move() {
	speed.add(gravityVector);
	particlePosition.add(speed);
	lifespan -= fadeUnit;
	material.decreaseAlpha(fadeUnit);

	// I don't want particles to grow right now
	// particleRadius += 0.001f;
    }

    public void draw(float cameraAngle) {
	computeCornerCoordinates(particlePosition, particleRadius, cameraAngle);
	material.bind(gl);
	drawCorners();
    }

    public boolean died() {
	if (lifespan <= 0.0f) {
	    return true;
	} else {
	    return false;
	}
    }

    private void computeCornerCoordinates(Vertex center, float particleSize,
	    float cameraAngle) {
	leftBottom = new Vertex(center.getPositionX(), center.getPositionY(),
		center.getPositionZ());
	rightBottom = new Vertex(center.getPositionX(), center.getPositionY(),
		center.getPositionZ());
	rightTop = new Vertex(center.getPositionX(), center.getPositionY(),
		center.getPositionZ());
	leftTop = new Vertex(center.getPositionX(), center.getPositionY(),
		center.getPositionZ());

	float rightSideZ = -particleSize * (float) Math.sin(cameraAngle);
	float rightSideX = particleSize * (float) Math.cos(cameraAngle);
	rightBottom.add(new Vertex(rightSideX, -particleSize, rightSideZ));
	rightTop.add(new Vertex(rightSideX, particleSize, rightSideZ));

	float leftSideZ = -particleSize
		* (float) Math.sin(cameraAngle + Math.PI);
	float leftSideX = particleSize
		* (float) Math.cos(cameraAngle + Math.PI);
	leftTop.add(new Vertex(leftSideX, particleSize, leftSideZ));
	leftBottom.add(new Vertex(leftSideX, -particleSize, leftSideZ));
    }

    private void drawCorners() {
	TextureCoords textureCoords = texture.getImageTexCoords();

	float leftTex = textureCoords.left();
	float rightTex = textureCoords.right();
	float topTex = textureCoords.top();
	float bottomTex = textureCoords.bottom();

	gl.glPushMatrix();
	gl.glBegin(GL_TRIANGLE_STRIP);
	gl.glTexCoord2d(rightTex, bottomTex);
	gl.glVertex3f(rightBottom.getPositionX(), rightBottom.getPositionY(),
		rightBottom.getPositionZ());

	gl.glTexCoord2d(rightTex, topTex);
	gl.glVertex3f(rightTop.getPositionX(), rightTop.getPositionY(),
		rightTop.getPositionZ());

	gl.glTexCoord2d(leftTex, bottomTex);
	gl.glVertex3f(leftBottom.getPositionX(), leftBottom.getPositionY(),
		leftBottom.getPositionZ());

	gl.glTexCoord2d(leftTex, topTex);
	gl.glVertex3f(leftTop.getPositionX(), leftTop.getPositionY(),
		leftTop.getPositionZ());
	gl.glEnd();
	gl.glPopMatrix();
    }

    // getters & setters
    public Vertex getGravityVector() {
	return gravityVector;
    }

    public void setGravityVector(Vertex gravityVector) {
	this.gravityVector = gravityVector;
    }
}
