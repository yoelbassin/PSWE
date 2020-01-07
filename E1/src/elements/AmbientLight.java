package elements;

import primitives.Color;

public class AmbientLight {
    Color intensity;
    public AmbientLight(Color i, double k){
        intensity = i.scale(k);
    }

    public Color getIntensity() {
        return intensity;
    }
}
