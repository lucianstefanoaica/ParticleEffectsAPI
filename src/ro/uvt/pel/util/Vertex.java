package ro.uvt.pel.util;

public class Vertex {

    private float positionX;
    private float positionY;
    private float positionZ;

    private float textureU;
    private float textureV;

    private float normalX;
    private float normalY;
    private float normalZ;

    private int index;

    public Vertex() {
    }

    public Vertex(float positionX, float positionY, float positionZ) {
	this.positionX = positionX;
	this.positionY = positionY;
	this.positionZ = positionZ;
    }

    public Vertex add(Vertex vector) {
	positionX += vector.getPositionX();
	positionY += vector.getPositionY();
	positionZ += vector.getPositionZ();

	return this;
    }

    public Vertex clone() {
	return new Vertex(positionX, positionY, positionZ);
    }

    @Override
    public String toString() {
	return "Vertex [positionX=" + positionX + ", positionY=" + positionY
		+ ", positionZ=" + positionZ + "]";
    }

    // getters & setters
    public float getPositionX() {
	return positionX;
    }

    public void setPositionX(float positionX) {
	this.positionX = positionX;
    }

    public float getPositionY() {
	return positionY;
    }

    public void setPositionY(float positionY) {
	this.positionY = positionY;
    }

    public float getPositionZ() {
	return positionZ;
    }

    public void setPositionZ(float positionZ) {
	this.positionZ = positionZ;
    }

    public float getTextureU() {
	return textureU;
    }

    public void setTextureU(float textureU) {
	this.textureU = textureU;
    }

    public float getTextureV() {
	return textureV;
    }

    public void setTextureV(float textureV) {
	this.textureV = textureV;
    }

    public float getNormalX() {
	return normalX;
    }

    public void setNormalX(float normalX) {
	this.normalX = normalX;
    }

    public float getNormalY() {
	return normalY;
    }

    public void setNormalY(float normalY) {
	this.normalY = normalY;
    }

    public float getNormalZ() {
	return normalZ;
    }

    public void setNormalZ(float normalZ) {
	this.normalZ = normalZ;
    }

    public int getIndex() {
	return index;
    }

    public void setIndex(int index) {
	this.index = index;
    }
}
