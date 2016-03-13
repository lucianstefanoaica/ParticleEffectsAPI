package ro.uvt.pel.util;

import java.util.Random;

import javax.media.opengl.GL2;

import static javax.media.opengl.GL.GL_FRONT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

public class Material {

    private float ambient[];
    private float diffuse[];
    private float specular[];
    private float shine[];

    public Material(float[] ambient, float[] diffuse, float[] specular,
	    float[] shine) {
	this.ambient = ambient;
	this.diffuse = diffuse;
	this.specular = specular;
	this.shine = shine;
    }

    public Material clone() {
	return new Material(ambient.clone(), diffuse.clone(), specular.clone(),
		shine.clone());
    }

    public void decreaseAlpha(float fadeUnit) {
	ambient[3] -= fadeUnit;
	diffuse[3] -= fadeUnit;
	specular[3] -= fadeUnit;
    }

    public void bind(GL2 gl) {
	gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL_FRONT, GL_SPECULAR, specular, 0);
	gl.glMaterialfv(GL_FRONT, GL_AMBIENT, ambient, 0);
	gl.glMaterialfv(GL_FRONT, GL_SHININESS, shine, 0);
    }

    public Material randomize() {
	Random rand = new Random();
	float[] ambient = new float[4];
	float[] diffuse = new float[4];
	float[] specular = new float[4];
	float[] shine = new float[1];

	for (int i = 0; i < 4; ++i) {
	    ambient[i] = rand.nextFloat();
	    specular[i] = rand.nextFloat();
	    diffuse[i] = rand.nextFloat();
	}

	shine[0] = this.shine[0];

	return new Material(ambient, diffuse, specular, shine);
    }

    // getters and setters
    public float[] getAmbient() {
	return ambient;
    }

    public void setAmbient(float[] ambient) {
	this.ambient = ambient;
    }

    public float[] getDiffuse() {
	return diffuse;
    }

    public void setDiffuse(float[] diffuse) {
	this.diffuse = diffuse;
    }

    public float[] getSpecular() {
	return specular;
    }

    public void setSpecular(float[] specular) {
	this.specular = specular;
    }

    public float[] getShine() {
	return shine;
    }

    public void setShine(float[] shine) {
	this.shine = shine;
    }
}