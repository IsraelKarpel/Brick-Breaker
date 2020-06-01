import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
* the class run the animations.
**/
public class AnimationRunner {
        private GUI gui;
        private int framesPerSecond;
        private biuoop.Sleeper sleeper;
        private GameLevel gameLevel;
        private LevelInformation information;

        /**
        * constructors.
        * @param g the gui.
        **/
        public AnimationRunner(GUI g) {
            this.framesPerSecond = 60;
            this.sleeper = new biuoop.Sleeper();
            this.gui = g;
        }

        /**
        * set the Gui of the game.
        * @param g the gui.
        **/
        public void setGui(GUI g) {
        this.gui = g;
        }

        /**
        * set the informaion of the level.
        * @param informat the level information.
        **/
        public void setInformation(LevelInformation informat) {
            this.information = informat;
        }

        /**
        * set the gamelevel of the game.
        * @param g the game.
        **/
        public void setGame(GameLevel g) {
            this.gameLevel = g;
        }

        /**
        * get the gui of the game.
        * @return the gui of the game
        **/
        public GUI getGui() {
            return this.gui;
        }
        /**
        * run the animation.
        * @param animation that will be desplayed eventually
        **/
        public void run(Animation animation) {
            int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();
                animation.doOneFrame(d);
                d.setColor(Color.black);
                gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    this.sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }

        /**
        * close the gui.
        **/
        public void closer() {
            gui.close();
        }
    }