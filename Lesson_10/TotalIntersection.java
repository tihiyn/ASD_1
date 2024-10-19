import java.util.List;
import java.util.Objects;

public class TotalIntersection {
    public PowerSet intersection(List<PowerSet> sets) {
        if (sets == null || sets.isEmpty()) {
            return new PowerSet();
        }

        PowerSet result = sets.get(0);
        for (PowerSet set: sets) {
            result = result.intersection(set);
        }

        return result;
    }
}

