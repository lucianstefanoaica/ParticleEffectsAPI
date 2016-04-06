package ro.uvt.pel.particle_systems;

import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

public interface PEL {

  /**
   * Draws a sprayed particle system which looks like a cone.
   * 
   * @param source represents the spawn point of the particle system meaning that all the particles
   *        from the system will be spawned in this point.
   * @param destination represents the center of the base of the cone shaped particle system.
   * @param texture represents the texture of all the particles from the particle system.
   * @param material represents the material properties of all the particles from the particle
   *        system.
   * @param particleCount represents the number of particles that the particle system spawns per
   *        frame.
   * @param coneRadius represents the radius of the base of the cone shaped particle system.
   * @param particleRadius represents the size of each particle from the particle system.
   * @param fadeQuotient represents the rate at which the particles fade out. It must be a floating
   *        point number between 0.0 and 1.0, the higher the number the faster the particles fade.
   * @param speed represents the speed of each particle from the system. It should be a floating
   *        point number between 0.0 and 1.0 which, again the higher the number the higher the speed
   *        of each particle from the system.
   * @param angle represents the rotation angle of each particle from the system. Initially the
   *        camera is considered to have a rotation angle of 0.0. As the camera rotates to left or
   *        right every particle from the system must also rotate in order to face the camera.
   */
  public void pelCone(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float angle);

  /**
   * Draws a cylinder shaped particle system.
   * 
   * @param source represents the center of the side of the cylinder in which the particles get
   *        spawned.
   * @param destination represents the center of the side of the cylinder towards which the
   *        particles move.
   * @param texture represents the texture of all the particles from the particle system.
   * @param material represents the material properties of all the particles from the particle
   *        system.
   * @param particleCount represents the number of particles that the particle system spawns per
   *        frame.
   * @param cylinderRadius represents the radius of the cylinder shaped particle system.
   * @param particleRadius represents the size of each particle from the particle system.
   * @param fadeQuotient represents the rate at which the particle fade out from the particle
   *        system. It must be a floating point number between 0.0 and 1.0, the higher the number
   *        the faster the particles fade.
   * @param speed represents the speed of each particle from the particle system. It must also be a
   *        floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles move.
   * @param angle represents the rotation angle of each particle from the particle system. It must
   *        coincide with the rotation angle of the camera meaning that if the camera rotates to its
   *        left all the particles from the particle system must also rotate to their left in order
   *        for them to face the camera.
   */
  public void pelCylinder(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float cylinderRadius, float particleRadius, float fadeQuotient,
      float speed, float angle);

  /**
   * Draws the reversed version of the cone shaped particle system. The main difference is that in
   * this particle system the particles get spawned at the base of the cone as opposed to the other
   * type of cone shaped particle system in which the particles get spawned at the tip of the cone
   * shaped particle system.
   * 
   * @param source represents the center of the base of the cone shaped particle system.
   * @param destination represents the tip of the cone shaped particle system. All the particles
   *        from this type of system move towards this point.
   * @param texture represents the texture of all the particles from the particle system.
   * @param material represents the material properties of each particle from the reversed cone
   *        shaped particle system.
   * @param particleCount represents the number of particles that the particle system spawns per
   *        frame.
   * @param coneRadius represents the radius of the cone.
   * @param particleRadius represents the size of each particle from the particle system.
   * @param fadeQuotient represents the rate at which each particle fades out from the particle
   *        system. It must be a floating point number between 0.0 and 1.0, the higher the number
   *        the faster the particles fade.
   * @param speed represents the speed of each particle from the system. It must be a floating point
   *        number between 0.0 and 1.0, the higher the number the higher the speed with which each
   *        particle moves.
   * @param angle represents the rotation angle of each particle from the particle system. This
   *        angle must coincide with the rotation angle of the camera because the particles must
   *        face the camera, so as the camera rotates to left the particles must also rotate to
   *        their left and as the camera rotates to its right the particles must also rotate to
   *        their right.
   */
  public void pelReversedCone(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float angle);

  /**
   * Draws a particle system which looks like a fountain. It is based on the cone shaped particle
   * system, it actually works the same way the cone shaped particle system works. The main
   * difference is that the particles from the fountain shaped particle system get affected by
   * gravity whilst the particles from the cone shaped particle system don't.
   * 
   * @param source represents the spawn point of the particles from the fountain particle system.
   *        All the particles from the particle system will be created in this point.
   * @param destination represents the center of the sphere of points towards which every particle
   *        from the particle system moves.
   * @param weight represents the weight vector of each particle from the fountain particle system.
   *        In other words it represents the rate at which the particles get affected by gravity.
   * @param texture represents the texture of each particle from the fountain particle system.
   * @param material represents the material properties of each particle from the fountain particle
   *        system.
   * @param particleCount represents the number of particles that the fountain particle system spawn
   *        per frame.
   * @param fountainRadius represents the radius of the fountain particle system, more simply put
   *        this parameter controls how scattered the fountain actually is.
   * @param particleRadius represents the radius of each particle from the particle system.
   * @param fadeQuotient represents the fade rate of each particle from the fountain particle
   *        system. It must be a floating point number between 0.0 and 1.0, the higher the number,
   *        the faster the particles from the particle system fade.
   * @param speed represents the speed of each particle from the particle system. It must be a
   *        floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles move.
   * @param angle represents the rotation angle of each particle from the fountain particle system.
   *        The rotation angle of each particle from the fountain particle system must coincide with
   *        the rotation angle of the camera. Whenever the camera rotates to its left the particles
   *        must also rotate to their left and whenever the camera rotates to its right the
   *        particles must also rotate to their right because the particles must face the camera.
   */
  public void pelFountain(Vertex source, Vertex destination, Vertex weight, Texture texture,
      Material material, int particleCount, float fountainRadius, float particleRadius,
      float fadeQuotient, float speed, float angle);

  /**
   * Draws a line of falling particles. If the fade quotient of the particles is small enough then
   * the effect will look like a curtain of particles.
   * 
   * @param left represents the first point of the two points required to define a line.
   * @param right represents the second point of the two points required to define a line.
   * @param weight represents the weight vector (the rate at which the particles fall) of each
   *        particle from the line particle system.
   * @param texture represents the texture of each particle from the line particle system.
   * @param material represents the material properties of each particle from the line particle
   *        system.
   * @param particleCount represents the number of particles that the line particle system spawns
   *        per frame.
   * @param particleRadius represents the size of the particles from the line particle system.
   * @param fadeQuotient represents the fade rate of each particle from the line particle system. It
   *        must be a floating point number between 0.0 and 1.0, the higher the number the faster
   *        the particles from the line particle system fade.
   * @param angle represents the rotation angle of each particle from the line particle system. The
   *        rotation angle of the particles from the system must coincide with the rotation angle of
   *        the camera. Whenever the camera rotates to its left the particles must also rotate to
   *        their left and whenever the camera rotates to its right the particles must also rotate
   *        to their right, this is because the particles must always face the camera.
   */
  public void pelLine(Vertex left, Vertex right, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float angle);

  /**
   * Draws a circle of fire on the ground of your scene.
   * 
   * @param center represents the center of the circle of fire.
   * @param weight represents the weight vector of each particle from the particle system. If this
   *        vector would be the zero vector then the particles would not move at all. This vector
   *        actually dictates how the particles from the particle system move.
   * @param texture represents the texture of each particle from the particle system.
   * @param material represents the material properties of each particle from the particle system.
   * @param particleCount represents the number of particles that the particle system spawns per
   *        frame.
   * @param fireRadius represents the radius of the circle of fire particle system.
   * @param particleRadius represents the size of the particles from the system.
   * @param fadeQuotient represents the fade rate of each particle from the particle system. It must
   *        be a floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles fade.
   * @param angle represents the rotation angle of each particle from the particle system. The
   *        rotation angle of each particle must coincide with that of the camera because whenever
   *        the camera rotates to its left the particles must also rotate to their left and whenever
   *        the camera rotates to its right the particles must also rotate to their right.
   */
  public void pelFire(Vertex center, Vertex weight, Texture texture, Material material,
      int particleCount, float fireRadius, float particleRadius, float fadeQuotient, float angle);

  /**
   * Draws a pulsating particle system in the shape of a ring.
   * 
   * @param center represents the center of the ring.
   * @param texture represents the texture of each particle from the particle system.
   * @param material represents the material properties of each particle from the particle system.
   * @param particleCount represents the number of particles that the particle system spawns per
   *        frame.
   * @param particleRadius represents the size of each particle from the particle system.
   * @param fadeQuotient represents the fade rate of each particle from the particle system. It must
   *        be a floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles fade.
   * @param speed represents the speed of each particle from the particle system. It must be a
   *        floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles move.
   * @param angle represents the rotation angle of each particle from the system. This angle must
   *        coincide with the rotation angle of the camera because the particles must always face
   *        the camera. Whenever the camera rotates to its left the particles must also rotate to
   *        their left and whenever the camera rotates to its right the particles must also rotate
   *        to their right.
   */
  public void pelRing(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  /**
   * Draws a series of fireworks explosions.
   * 
   * @param sources represents a list of all the sources of the explosions.
   * @param weight represents how much gravity affects every particle from every explosion.
   * @param texture represents the texture of each particle from every explosion.
   * @param material represents the material properties of each particle from every explosion.
   * @param particleCount represents the number of particles that each explosion has.
   * @param particleRadius represents the size of every particle from every explosion.
   * @param fadeQuotient represents the fade rate of each particle from every explosion. It must be
   *        a floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles fade out.
   * @param speed represents the speed of the particles from every explosion. It must be a floating
   *        point number between 0.0 and 1.0, the higher the number the faster the particles move.
   * @param angle represents the rotation angle of each particle from every explosion. This angle
   *        must coincide with the rotation angle of the camera meaning that whenever the camera
   *        rotates to its left the particles must also rotate to their left and if the camera
   *        rotates to its right the particles must also rotate to their right, this is because the
   *        particles must always face the camera.
   */
  public void pelFireworks(List<Vertex> sources, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float speed, float angle);

  /**
   * Draws a pulsating particle system in the shape of an atom.
   * 
   * @param center represents the center of the atom.
   * @param texture represents the texture of each particle from the atom.
   * @param material represents the material properties of each particle from the atom.
   * @param particleCount represents the number of particles that the atom spawns per each pulse.
   * @param particleRadius represents the size of each particle from the atom.
   * @param fadeQuotient represents the fade rate of each particle from the atom. It must be a
   *        floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles fade out.
   * @param speed represents the speed of each particle from the atom. It must be a floating point
   *        number between 0.0 and 1.0, the higher the number the faster the particles move.
   * @param angle represents the rotation angle of each particle from the atom. The angle must
   *        coincide with the rotation angle of the camera meaning that whenever the camera rotates
   *        to its left the particles must also rotate to their left and whenever the camera rotates
   *        to its right the particles must also rotate to their right, this is because the
   *        particles must always face the camera.
   */
  public void pelAtom(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  /**
   * Draws a disk of fire in which the particles are spawned in the center of the disk and move
   * towards the edge of the disk.
   * 
   * @param center represents the position from the center of the disk.
   * @param texture represents the texture of each particle from the disk.
   * @param material represents the material properties of each particle from the disk.
   * @param particleCount represents the number of particle that the disk particle system spawns per
   *        frame.
   * @param particleRadius represents the size of each particle from the disk particle system.
   * @param fadeQuotient represents the fade rate of each particle from the disk particle system. It
   *        must be a floating point number between 0.0 and 1.0, the higher the number the faster
   *        the particles from the system fade out.
   * @param speed represents the speed of each particle from the disk particle system. It must be a
   *        floating point number between 0.0 and 1.0, the higher the number the faster the
   *        particles move.
   * @param angle represents the rotation angle of each particle from the disk particle system. This
   *        angle must coincide with the rotation angle of the camera meaning the whenever the
   *        camera rotates to its left the particles must also rotate to their left and whenever the
   *        camera rotates to its right the particles must also rotate to their right, this is
   *        because the particles must always face the camera.
   */
  public void pelDisk(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  /**
   * Registers the GLAutoDrawable object on which the library will draws the particle effects.
   * 
   * @param drawable represents the GLAutoDrawable object on which the library will draw the
   *        particle effects.
   */
  public void registerGLAutoDrawable(GLAutoDrawable drawable);
}
