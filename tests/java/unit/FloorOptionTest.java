import com.hstairs.enhsp.integration.model.PlannerCliOptions;
import com.hstairs.ppmajal.extraUtils.PlannerExitException;
import com.hstairs.ppmajal.pddl.heuristics.PDDLHeuristic;
import com.hstairs.ppmajal.pddl.heuristics.advanced.CPZeroCut;
import com.hstairs.ppmajal.pddl.heuristics.advanced.H1;
import enhsp2.ENHSP;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FloorOptionTest extends TestCase {

    public void testCliDefaultsFloorToTrueAndAcceptsFalse() throws Exception {
        ENHSP defaults = parse();
        ENHSP disabled = parse("-floor", "false");

        assertTrue(floor(defaults));
        assertFalse(floor(disabled));
    }

    public void testGuiOptionsAlwaysEmitTheFloorValue() {
        PlannerCliOptions options = PlannerCliOptions.defaults();
        List<String> args = new ArrayList<>();
        options.appendArgs(args);
        assertEquals("true", valueAfter(args, "-floor"));

        options.floor = false;
        args.clear();
        options.appendArgs(args);
        assertEquals("false", valueAfter(args, "-floor"));
    }

    public void testCliRejectsLegacyHybridOption() {
        assertOptionRejected("-hybrid", "false");
    }

    public void testCliDefaultsCrdToNoneAndAcceptsAllModes() throws Exception {
        assertEquals(H1.CrdMode.NONE, crd(parse()));
        assertEquals(H1.CrdMode.STATIC, crd(parse("-crd", "static")));
        assertEquals(H1.CrdMode.ONLINE, crd(parse("-crd", "ONLINE")));
    }

    public void testGuiOptionsEmitCrdMode() {
        PlannerCliOptions options = PlannerCliOptions.defaults();
        List<String> args = new ArrayList<>();
        options.appendArgs(args);
        assertEquals("none", valueAfter(args, "-crd"));

        options.crd = "online";
        args.clear();
        options.appendArgs(args);
        assertEquals("online", valueAfter(args, "-crd"));
    }

    public void testGuiOptionsDoNotEmitZeroingMode() {
        PlannerCliOptions options = PlannerCliOptions.defaults();
        List<String> args = new ArrayList<>();
        options.appendArgs(args);
        assertFalse(args.contains("-selzero"));
        assertFalse(args.contains("-selzer"));
    }

    public void testCliExposesSelectiveZeroingModes() throws Exception {
        assertEquals(CPZeroCut.ZeroingMode.BASE,
                zeroingMode(parse("-selzero", "base")));
        assertEquals(CPZeroCut.ZeroingMode.FLOOR,
                zeroingMode(parse("-selzero", "floor")));
        assertEquals(CPZeroCut.ZeroingMode.NUM,
                zeroingMode(parse("-selzero", "num")));
        assertEquals(CPZeroCut.ZeroingMode.NUM_FRACTIONAL,
                zeroingMode(parse("-selzero", "numfract")));
        assertEquals(CPZeroCut.ZeroingMode.FLOOR,
                zeroingMode(parse("-selzer")));
        assertEquals(CPZeroCut.ZeroingMode.NUM_FRACTIONAL,
                zeroingMode(parse("-selzer", "numfract")));
        assertOptionRejected("-selzero", "num", "-floor", "false");
        assertOptionRejected("-selzero", "numfract", "-floor", "false");
    }

    public void testExtendedHybridHeuristicNamesAreNotAdvertised() {
        String help = PDDLHeuristic.getHelpString();
        assertFalse(help.contains("hrmaxhybrid"));
        assertFalse(help.contains("hrmaxssnphybrid"));
        assertFalse(help.contains("hrmaxssnpstatehybrid"));
        assertFalse(help.contains("cpzerocuthybrid"));
        assertFalse(help.contains("cpzerocutssnphybrid"));
        assertFalse(help.contains("cpzerocutssnpstatehybrid"));
        assertFalse(help.contains("hrmaxssnp"));
        assertFalse(help.contains("hrmaxssnpstate"));
        assertFalse(help.contains("hmaxssnp"));
        assertFalse(help.contains("hmaxssnpstate"));
        assertFalse(help.contains("cpzerocutssnp"));
        assertFalse(help.contains("cpzerocutssnpstate"));
    }

    private ENHSP parse(String... extra) {
        List<String> args = new ArrayList<>(List.of(
                "-o", "domain.pddl",
                "-f", "problem.pddl"
        ));
        args.addAll(List.of(extra));
        ENHSP planner = new ENHSP(false);
        planner.parseInput(args.toArray(new String[0]));
        return planner;
    }

    private boolean floor(ENHSP planner) throws Exception {
        Field field = ENHSP.class.getDeclaredField("floor");
        field.setAccessible(true);
        return field.getBoolean(planner);
    }

    private H1.CrdMode crd(ENHSP planner) throws Exception {
        Field field = ENHSP.class.getDeclaredField("crdMode");
        field.setAccessible(true);
        return (H1.CrdMode) field.get(planner);
    }

    private CPZeroCut.ZeroingMode zeroingMode(ENHSP planner) throws Exception {
        Field field = ENHSP.class.getDeclaredField("zeroingMode");
        field.setAccessible(true);
        return (CPZeroCut.ZeroingMode) field.get(planner);
    }

    private void assertOptionRejected(String... option) {
        try {
            parse(option);
            fail("The option must be rejected");
        } catch (PlannerExitException expected) {
            assertEquals(-1, expected.exitCode());
        }
    }

    private String valueAfter(List<String> args, String option) {
        int index = args.indexOf(option);
        assertTrue(index >= 0);
        return args.get(index + 1);
    }
}
