
package ro.space.read.textures;

import static javax.media.opengl.GL.GL_NEAREST;
import static javax.media.opengl.GL.GL_REPEAT;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_WRAP_S;
import static javax.media.opengl.GL.GL_TEXTURE_WRAP_T;

import java.net.URL;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureReader {

  private GL2 gl;

  private String filesLocation;

  public TextureReader(GL2 gl, String filesLocation) {
    this.gl = gl;
    this.filesLocation = filesLocation;
    setGLTextureFlags();
  }

  public Texture readTexture(String fileName, String fileType) {
    try {
      String filePath = filesLocation + fileName;

      URL texturePicture = getClass().getClassLoader().getResource(filePath);

      Texture resultTexture = TextureIO.newTexture(texturePicture, false, fileType);

      resultTexture.enable(gl);

      return resultTexture;

    } catch (Exception e) {
      System.err.println(e.toString());
      return null;
    }
  }

  public void setGLTextureFlags() {
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
  }
}