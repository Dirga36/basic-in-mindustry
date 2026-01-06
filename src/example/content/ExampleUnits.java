package example.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;

import example.type.unit.ExampleUnitType;
import example.content.ExampleSounds.*;

import mindustry.ai.*;
import mindustry.content.Fx;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

public class ExampleUnits
{
    public static ExampleUnitType cax;

    public static void load() {

        cax = new ExampleUnitType("cax") {{
            constructor = TankUnit::create;
            aiController = GroundAI::new;

            hitSize = 46f;
            treadPullOffset = 1; //probably treads
            speed = 0.48f;
            health = 22000;
            armor = 26f;
            crushDamage = 25f / 5f;
            rotateSpeed = 0.8f;

            float xo = 231f / 2f, yo = 231f / 2f;
            treadRects = new Rect[]{new Rect(27 - xo, 152 - yo, 56, 73), new Rect(24 - xo, 51 - 9 - yo, 29, 17), new Rect(59 - xo, 18 - 9 - yo, 39, 19)};

            weapons.add(new Weapon("example-java-mod-cax-weapon") {{
                shootSound = ExampleSounds.largeCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 32.5f;
                shake = 5f;
                recoil = 5f;
                rotate = true;
                rotateSpeed = 0.6f;
                mirror = false;
                x = 0f;
                y = -1f;
                shadow = 50f;
                heatColor = Color.valueOf("f9350f");
                shootWarmupSpeed = 0.06f;
                cooldownTime = 110f;
                heatColor = Color.valueOf("f9350f");
                minWarmup = 0.9f;

                parts.addAll(
                        new RegionPart("-glow") {{
                            color = Color.red;
                            blending = Blending.additive;
                            outline = mirror = false;
                        }},
                        new RegionPart("-sides") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            moveX = 0.75f;
                            moveY = 0.75f;
                            moveRot = 82f;
                            x = 37 / 4f;
                            y = 8 / 4f;
                        }},
                        new RegionPart("-sinks") {{
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = true;
                            heatColor = new Color(1f, 0.1f, 0.1f);
                            moveX = 17f / 4f;
                            moveY = -15f / 4f;
                            x = 32 / 4f;
                            y = -34 / 4f;
                        }},
                        new RegionPart("-sinks-heat") {{
                            blending = Blending.additive;
                            progress = PartProgress.warmup;
                            mirror = true;
                            outline = false;
                            colorTo = new Color(1f, 0f, 0f, 0.5f);
                            color = colorTo.cpy().a(0f);
                            moveX = 17f / 4f;
                            moveY = -15f / 4f;
                            x = 32 / 4f;
                            y = -34 / 4f;
                        }}
                );

                for (int i = 1; i <= 3; i++) {
                    int fi = i;
                    parts.add(new RegionPart("-blade") {{
                        progress = PartProgress.warmup.delay((3 - fi) * 0.3f).blend(PartProgress.reload, 0.3f);
                        heatProgress = PartProgress.heat.add(0.3f).min(PartProgress.warmup);
                        heatColor = new Color(1f, 0.1f, 0.1f);
                        mirror = true;
                        under = true;
                        moveRot = -40f * fi;
                        moveX = 3f;
                        layerOffset = -0.002f;

                        x = 11 / 4f;
                    }});
                }

                bullet = new BasicBulletType(8f, 360f) {{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 35f;
                    hitSize = 6f;

                    smokeEffect = Fx.shootSmokeTitan;
                    pierceCap = 3;
                    pierce = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.massiveExplosion;

                    shootEffect = new ExplosionEffect() {{
                        lifetime = 40f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 15f;
                        smokeSize = 5f;
                        smokes = 8;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 8;
                        sparkRad = 40f;
                        sparkLen = 4f;
                        sparkStroke = 3f;
                    }};

                    int count = 6;
                    for (int j = 0; j < count; j++) {
                        int s = j;
                        for (int i : Mathf.signs) {
                            float fin = 0.05f + (j + 1) / (float) count;
                            float spd = speed;
                            float life = lifetime / Mathf.lerp(fin, 1f, 0.5f);
                            spawnBullets.add(new BasicBulletType(spd * fin, 60) {{
                                drag = 0.002f;
                                width = 12f;
                                height = 11f;
                                lifetime = life + 5f;
                                weaveRandom = false;
                                hitSize = 5f;
                                pierceCap = 2;
                                pierce = true;
                                pierceBuilding = true;
                                hitColor = backColor = trailColor = Color.valueOf("feb380");
                                frontColor = Color.white;
                                trailWidth = 2.5f;
                                trailLength = 7;
                                weaveScale = (3f + s / 2f) / 1.2f;
                                weaveMag = i * (4f - fin * 2f);

                                splashDamage = 65f;
                                splashDamageRadius = 30f;
                                despawnEffect = new ExplosionEffect() {{
                                    lifetime = 50f;
                                    waveStroke = 4f;
                                    waveColor = sparkColor = trailColor;
                                    waveRad = 30f;
                                    smokeSize = 7f;
                                    smokes = 6;
                                    smokeSizeBase = 0f;
                                    smokeColor = trailColor;
                                    sparks = 5;
                                    sparkRad = 30f;
                                    sparkLen = 3f;
                                    sparkStroke = 1.5f;
                                }};
                            }});
                        }
                    }
                }};
            }});

            parts.add(new RegionPart("-glow") {{
                color = Color.red;
                blending = Blending.additive;
                layer = -1f;
                outline = false;
            }});
        }};

    }
}
