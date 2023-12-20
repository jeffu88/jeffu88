package Model.Tanks;

import java.util.Timer;
import java.util.TimerTask;
/** Timer that is responsible for projectile cooldown. */
public class ShootDelayTimer {
    private Timer timer;
    private boolean canShoot;
    /** Creates timer object. */
    public ShootDelayTimer() {
        canShoot=true;
    }
    /** */
    public boolean getCanShoot() {return canShoot;}
    /** Method that starts timer after shot. */
    public void afterShootDelay(int milliseconds) {
        timer = new Timer();
        canShoot = false;
        timer.schedule(new shootDelay(), milliseconds);  //creating new timer that change canShoot to true after given time
    }
    private void setCanShoot(boolean state) {canShoot = state;}

    class shootDelay extends TimerTask {
        @Override
        public void run() {
            setCanShoot(true);
            timer.cancel();
        }
    }
}
