// $ANTLR 3.5.2 Pddl.g 2016-12-12 12:26:57
package parser;

@SuppressWarnings("all")
public class PddlLexer extends Lexer {

    public static final int EOF = -1;
    public static final int T__67 = 67;
    public static final int T__68 = 68;
    public static final int T__69 = 69;
    public static final int T__70 = 70;
    public static final int T__71 = 71;
    public static final int T__72 = 72;
    public static final int T__73 = 73;
    public static final int T__74 = 74;
    public static final int T__75 = 75;
    public static final int T__76 = 76;
    public static final int T__77 = 77;
    public static final int T__78 = 78;
    public static final int T__79 = 79;
    public static final int T__80 = 80;
    public static final int T__81 = 81;
    public static final int T__82 = 82;
    public static final int T__83 = 83;
    public static final int T__84 = 84;
    public static final int T__85 = 85;
    public static final int T__86 = 86;
    public static final int T__87 = 87;
    public static final int T__88 = 88;
    public static final int T__89 = 89;
    public static final int T__90 = 90;
    public static final int T__91 = 91;
    public static final int T__92 = 92;
    public static final int T__93 = 93;
    public static final int T__94 = 94;
    public static final int T__95 = 95;
    public static final int T__96 = 96;
    public static final int T__97 = 97;
    public static final int T__98 = 98;
    public static final int T__99 = 99;
    public static final int T__100 = 100;
    public static final int T__101 = 101;
    public static final int T__102 = 102;
    public static final int T__103 = 103;
    public static final int T__104 = 104;
    public static final int T__105 = 105;
    public static final int T__106 = 106;
    public static final int T__107 = 107;
    public static final int T__108 = 108;
    public static final int T__109 = 109;
    public static final int T__110 = 110;
    public static final int T__111 = 111;
    public static final int T__112 = 112;
    public static final int T__113 = 113;
    public static final int T__114 = 114;
    public static final int T__115 = 115;
    public static final int T__116 = 116;
    public static final int T__117 = 117;
    public static final int T__118 = 118;
    public static final int T__119 = 119;
    public static final int T__120 = 120;
    public static final int T__121 = 121;
    public static final int T__122 = 122;
    public static final int T__123 = 123;
    public static final int T__124 = 124;
    public static final int T__125 = 125;
    public static final int T__126 = 126;
    public static final int T__127 = 127;
    public static final int T__128 = 128;
    public static final int T__129 = 129;
    public static final int T__130 = 130;
    public static final int T__131 = 131;
    public static final int T__132 = 132;
    public static final int T__133 = 133;
    public static final int T__134 = 134;
    public static final int T__135 = 135;
    public static final int T__136 = 136;
    public static final int T__137 = 137;
    public static final int T__138 = 138;
    public static final int T__139 = 139;
    public static final int T__140 = 140;
    public static final int T__141 = 141;
    public static final int T__142 = 142;
    public static final int T__143 = 143;
    public static final int ABS = 4;
    public static final int ACTION = 5;
    public static final int AND_EFFECT = 6;
    public static final int AND_GD = 7;
    public static final int ANY_CHAR = 8;
    public static final int ASSIGN_EFFECT = 9;
    public static final int BINARY_OP = 10;
    public static final int COMPARISON_GD = 11;
    public static final int CONSTANTS = 12;
    public static final int CONSTRAINT = 13;
    public static final int COS = 14;
    public static final int DIGIT = 15;
    public static final int DOMAIN = 16;
    public static final int DOMAIN_NAME = 17;
    public static final int DURATIVE_ACTION = 18;
    public static final int EFFECT = 19;
    public static final int EITHER_TYPE = 20;
    public static final int EQUALITY_CON = 21;
    public static final int EVENT = 22;
    public static final int EXISTS_GD = 23;
    public static final int FORALL_EFFECT = 24;
    public static final int FORALL_GD = 25;
    public static final int FORMULAINIT = 26;
    public static final int FREE_FUNCTIONS = 27;
    public static final int FUNCTIONS = 28;
    public static final int FUNC_HEAD = 29;
    public static final int GLOBAL_CONSTRAINT = 30;
    public static final int GOAL = 31;
    public static final int IMPLY_GD = 32;
    public static final int INIT = 33;
    public static final int INIT_AT = 34;
    public static final int INIT_EQ = 35;
    public static final int LETTER = 36;
    public static final int LINE_COMMENT = 37;
    public static final int MINUS_OP = 38;
    public static final int MULTI_OP = 39;
    public static final int NAME = 40;
    public static final int NOT_EFFECT = 41;
    public static final int NOT_GD = 42;
    public static final int NOT_PRED_INIT = 43;
    public static final int NUMBER = 44;
    public static final int OBJECTS = 45;
    public static final int ONEOF = 46;
    public static final int OR_GD = 47;
    public static final int PRECONDITION = 48;
    public static final int PREDICATES = 49;
    public static final int PRED_HEAD = 50;
    public static final int PRED_INST = 51;
    public static final int PROBLEM = 52;
    public static final int PROBLEM_CONSTRAINT = 53;
    public static final int PROBLEM_DOMAIN = 54;
    public static final int PROBLEM_METRIC = 55;
    public static final int PROBLEM_NAME = 56;
    public static final int PROCESS = 57;
    public static final int REQUIREMENTS = 58;
    public static final int REQUIRE_KEY = 59;
    public static final int SIN = 60;
    public static final int TYPES = 61;
    public static final int UNARY_MINUS = 62;
    public static final int UNKNOWN = 63;
    public static final int VARIABLE = 64;
    public static final int WHEN_EFFECT = 65;
    public static final int WHITESPACE = 66;
    static final String DFA12_eotS
            = "\6\uffff\1\41\2\uffff\1\60\1\uffff\1\62\2\uffff\16\34\27\uffff\1\64\1"
            + "\uffff\4\34\1\150\20\34\1\172\11\34\15\uffff\1\64\1\u008c\1\u008d\1\34"
            + "\1\u008f\2\34\1\uffff\1\u0092\4\34\1\u0097\10\34\1\u00a0\2\34\1\uffff"
            + "\4\34\1\u00a7\5\34\6\uffff\1\64\2\uffff\1\34\1\uffff\2\34\1\uffff\4\34"
            + "\1\uffff\10\34\1\uffff\2\34\1\u00c6\3\34\1\uffff\3\34\1\u00cd\1\34\7\uffff"
            + "\1\64\12\34\1\u00df\5\34\1\u00e5\1\uffff\4\34\1\u00ea\1\34\1\uffff\1\34"
            + "\4\uffff\1\64\1\u00f4\1\u00f5\2\34\1\u00f8\1\u00f9\1\u00fa\1\u00fb\1\u00fc"
            + "\2\34\1\uffff\4\34\1\u0103\1\uffff\4\34\1\uffff\1\34\1\u010a\5\uffff\1"
            + "\64\1\34\2\uffff\2\34\5\uffff\6\34\1\uffff\1\34\1\u011b\3\34\1\u011f\3"
            + "\uffff\1\u0122\2\uffff\1\64\2\34\1\u0127\2\34\1\u012a\1\34\1\u012c\1\u012d"
            + "\1\34\1\uffff\1\34\1\u0130\1\u0132\5\uffff\1\u0136\2\34\1\uffff\2\34\1"
            + "\uffff\1\34\2\uffff\2\34\1\uffff\1\34\1\uffff\1\u0140\3\uffff\2\34\1\u0145"
            + "\2\34\1\u0148\1\u0149\2\34\1\uffff\1\u014d\1\uffff\2\34\1\uffff\1\u0151"
            + "\1\u0152\2\uffff\2\34\3\uffff\1\34\1\u0158\2\uffff\2\34\2\uffff\1\u015c"
            + "\1\uffff\2\34\2\uffff\1\u0160\1\34\2\uffff\1\u0163\1\u0164\2\uffff";
    static final String DFA12_eofS
            = "\u0165\uffff";
    static final String DFA12_minS
            = "\1\11\1\164\4\uffff\1\60\1\uffff\1\141\1\75\1\uffff\1\75\1\101\1\uffff"
            + "\1\142\1\157\1\145\1\151\2\157\1\155\1\141\1\157\1\156\1\162\1\143\1\156"
            + "\1\150\6\uffff\1\143\1\157\1\145\1\146\1\154\4\uffff\1\141\1\uffff\1\151"
            + "\5\uffff\1\165\1\uffff\1\163\1\154\1\144\1\163\1\55\1\163\1\143\1\155"
            + "\1\164\1\144\1\151\1\162\1\154\1\160\1\143\1\55\1\170\1\156\1\164\1\155"
            + "\1\145\1\55\2\145\1\141\1\156\1\155\1\141\1\153\1\145\1\164\2\uffff\1"
            + "\156\1\162\1\uffff\1\162\5\uffff\1\145\1\160\1\162\2\55\1\141\1\55\1\151"
            + "\1\155\1\uffff\1\55\1\162\1\151\1\141\1\150\1\55\1\163\1\141\1\144\1\154"
            + "\1\162\1\166\2\151\1\55\1\142\1\157\1\uffff\1\162\1\146\1\142\1\154\1"
            + "\55\1\145\1\162\2\156\1\150\1\144\1\151\1\141\1\143\1\uffff\1\145\1\141"
            + "\2\uffff\1\171\1\uffff\1\147\1\157\1\uffff\1\145\1\156\1\151\1\145\1\uffff"
            + "\1\164\1\154\1\55\1\171\1\145\1\151\2\155\1\uffff\1\145\1\146\1\55\1\145"
            + "\1\154\1\145\1\uffff\2\164\1\157\1\55\2\151\1\164\1\166\1\164\3\uffff"
            + "\1\164\1\163\1\156\1\163\1\141\1\145\1\156\1\162\1\163\1\154\1\141\1\55"
            + "\1\141\1\157\2\151\1\162\1\55\1\uffff\1\162\1\145\1\55\1\151\1\55\1\167"
            + "\1\uffff\1\156\1\164\1\141\1\145\2\151\2\55\1\164\1\163\5\55\1\146\1\165"
            + "\1\uffff\1\163\1\154\2\172\1\55\1\uffff\1\145\1\155\1\144\1\155\1\uffff"
            + "\1\156\1\55\1\151\1\uffff\1\141\1\144\2\157\1\167\2\uffff\1\55\1\145\5"
            + "\uffff\1\164\1\162\1\145\1\141\2\145\1\uffff\1\156\1\55\1\157\1\160\1"
            + "\145\1\55\1\uffff\1\157\1\151\1\55\1\uffff\1\145\1\156\1\151\1\157\1\55"
            + "\1\145\1\151\1\55\1\164\2\55\1\143\1\uffff\1\167\2\55\1\uffff\2\156\1"
            + "\uffff\2\55\1\164\1\156\1\uffff\1\162\1\156\1\uffff\1\145\2\uffff\1\145"
            + "\1\156\1\uffff\1\141\1\uffff\1\141\1\164\1\141\1\uffff\1\150\1\143\1\55"
            + "\1\147\1\144\2\55\1\146\1\145\1\uffff\1\163\1\143\1\151\1\145\1\uffff"
            + "\2\55\2\uffff\1\164\1\146\2\uffff\1\164\1\156\1\55\2\uffff\1\145\1\157"
            + "\1\uffff\1\151\1\55\1\uffff\2\162\1\157\1\uffff\1\55\1\145\1\156\1\uffff"
            + "\1\55\1\163\2\uffff";
    static final String DFA12_maxS
            = "\1\172\1\164\4\uffff\1\71\1\uffff\1\165\1\75\1\uffff\1\75\1\172\1\uffff"
            + "\1\164\2\157\1\170\2\157\1\163\1\151\1\165\1\166\1\162\1\164\1\156\1\151"
            + "\6\uffff\1\144\1\157\1\165\1\170\1\165\4\uffff\1\162\1\uffff\1\171\5\uffff"
            + "\1\165\1\uffff\1\163\1\167\1\144\1\163\1\172\1\163\1\146\1\155\1\164\1"
            + "\144\1\151\1\162\1\154\1\160\1\143\1\55\1\170\1\156\1\164\1\155\1\145"
            + "\1\172\1\145\1\157\1\141\1\156\1\155\1\141\1\153\1\145\1\164\2\uffff\1"
            + "\156\1\162\1\uffff\1\162\5\uffff\1\157\1\160\1\162\2\172\1\141\1\172\1"
            + "\151\1\155\1\uffff\1\172\1\162\1\151\1\141\1\150\1\172\1\163\1\141\1\144"
            + "\1\154\1\162\1\166\2\151\1\172\1\142\1\157\1\uffff\1\162\1\146\1\142\1"
            + "\154\1\172\1\145\1\162\2\156\1\150\1\163\1\151\1\141\1\146\1\uffff\1\151"
            + "\1\141\2\uffff\1\171\1\uffff\1\147\1\157\1\uffff\1\145\1\156\1\151\1\145"
            + "\1\uffff\1\164\1\154\1\55\1\171\1\145\1\151\2\155\1\uffff\1\145\1\146"
            + "\1\172\1\145\1\154\1\145\1\uffff\2\164\1\157\1\172\2\151\1\164\1\166\1"
            + "\164\3\uffff\1\164\1\163\1\156\1\163\1\141\1\145\1\156\1\162\1\163\1\154"
            + "\1\144\1\172\1\141\1\157\2\151\1\162\1\172\1\uffff\1\162\1\145\1\55\1"
            + "\151\1\172\1\167\1\uffff\1\156\1\164\1\162\1\145\2\151\2\172\1\164\1\163"
            + "\5\172\1\146\1\165\1\uffff\1\163\1\154\3\172\1\uffff\1\145\1\155\1\165"
            + "\1\155\1\uffff\1\156\1\172\1\151\1\uffff\1\141\1\144\1\166\1\157\1\167"
            + "\2\uffff\1\55\1\145\5\uffff\1\164\1\162\1\145\1\141\2\145\1\uffff\1\156"
            + "\1\172\1\157\1\160\1\145\1\172\1\uffff\1\157\1\151\1\55\1\uffff\1\145"
            + "\1\156\1\151\1\157\1\172\1\145\1\151\1\172\1\164\2\172\1\143\1\uffff\1"
            + "\167\2\172\1\uffff\2\156\1\uffff\1\55\1\172\1\164\1\156\1\uffff\1\162"
            + "\1\156\1\uffff\1\145\2\uffff\1\145\1\156\1\uffff\1\142\1\uffff\1\141\1"
            + "\164\1\141\1\uffff\1\150\1\143\1\172\1\147\1\144\2\172\1\146\1\145\1\uffff"
            + "\1\163\1\143\1\151\1\145\1\uffff\2\172\2\uffff\1\164\1\146\2\uffff\1\164"
            + "\1\156\1\172\2\uffff\1\145\1\157\1\uffff\1\151\1\172\1\uffff\2\162\1\157"
            + "\1\uffff\1\172\1\145\1\156\1\uffff\1\172\1\163\2\uffff";
    static final String DFA12_acceptS
            = "\2\uffff\1\2\1\3\1\4\1\5\1\uffff\1\7\2\uffff\1\41\2\uffff\1\45\16\uffff"
            + "\1\117\1\121\1\122\1\123\1\1\1\6\5\uffff\1\25\1\26\1\27\1\30\1\uffff\1"
            + "\35\1\uffff\1\116\1\40\1\37\1\43\1\42\1\uffff\1\120\37\uffff\1\1\1\10"
            + "\2\uffff\1\16\1\uffff\1\21\1\22\1\23\1\24\1\31\11\uffff\1\54\21\uffff"
            + "\1\100\16\uffff\1\34\2\uffff\1\46\1\47\1\uffff\1\52\2\uffff\1\56\4\uffff"
            + "\1\63\10\uffff\1\75\6\uffff\1\106\11\uffff\1\32\1\33\1\36\22\uffff\1\101"
            + "\6\uffff\1\114\21\uffff\1\70\5\uffff\1\77\4\uffff\1\112\3\uffff\1\12\5"
            + "\uffff\1\50\1\53\2\uffff\1\60\1\61\1\62\1\64\1\65\6\uffff\1\76\6\uffff"
            + "\1\115\3\uffff\1\17\14\uffff\1\103\3\uffff\1\113\2\uffff\1\15\4\uffff"
            + "\1\57\2\uffff\1\71\1\uffff\1\73\1\74\2\uffff\1\105\1\uffff\1\107\3\uffff"
            + "\1\44\11\uffff\1\11\4\uffff\1\66\2\uffff\1\102\1\104\2\uffff\1\14\1\13"
            + "\3\uffff\1\67\1\72\2\uffff\1\14\2\uffff\1\55\3\uffff\1\51\3\uffff\1\110"
            + "\2\uffff\1\111\1\20";
    static final String DFA12_specialS
            = "\u0165\uffff}>";
    // $ANTLR end "T__67"
    static final String[] DFA12_transitionS = {
            "\2\37\2\uffff\1\37\22\uffff\1\37\2\uffff\1\1\4\uffff\1\2\1\3\1\4\1\5"
                    + "\1\uffff\1\6\1\uffff\1\7\12\35\1\10\1\36\1\11\1\12\1\13\1\14\1\uffff"
                    + "\32\34\3\uffff\1\15\2\uffff\1\16\1\34\1\17\1\20\1\21\1\22\1\34\1\23\1"
                    + "\24\3\34\1\25\1\26\1\27\1\30\2\34\1\31\1\34\1\32\1\34\1\33\3\34",
            "\1\40",
            "",
            "",
            "",
            "",
            "\12\35",
            "",
            "\1\42\1\uffff\1\43\1\44\1\45\1\46\1\47\1\uffff\1\50\3\uffff\1\51\1\56"
                    + "\1\52\1\53\1\56\1\54\1\56\1\55\1\56",
            "\1\57",
            "",
            "\1\61",
            "\32\64\6\uffff\3\64\1\63\26\64",
            "",
            "\1\65\11\uffff\1\66\1\uffff\1\67\4\uffff\1\70\1\71",
            "\1\72",
            "\1\73\11\uffff\1\74",
            "\1\75\4\uffff\1\76\11\uffff\1\77",
            "\1\100",
            "\1\101",
            "\1\102\1\103\4\uffff\1\104",
            "\1\105\7\uffff\1\106",
            "\1\107\5\uffff\1\110",
            "\1\111\3\uffff\1\112\3\uffff\1\113",
            "\1\114",
            "\1\115\5\uffff\1\116\5\uffff\1\117\4\uffff\1\120",
            "\1\121",
            "\1\122\1\123",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\125\1\56",
            "\1\126",
            "\1\127\3\uffff\1\56\5\uffff\1\130\5\uffff\1\131",
            "\1\132\12\uffff\1\56\4\uffff\1\133\1\uffff\1\56",
            "\1\56\5\uffff\1\134\2\uffff\1\135",
            "",
            "",
            "",
            "",
            "\1\136\20\uffff\1\137",
            "",
            "\1\56\17\uffff\1\140",
            "",
            "",
            "",
            "",
            "",
            "\1\141",
            "",
            "\1\142",
            "\1\143\12\uffff\1\144",
            "\1\145",
            "\1\146",
            "\1\147\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\151",
            "\1\152\2\uffff\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\173",
            "\1\174\11\uffff\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "",
            "",
            "\1\u0085",
            "\1\u0086",
            "",
            "\1\u0087",
            "",
            "",
            "",
            "",
            "",
            "\1\u0088\11\uffff\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u008e",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0090",
            "\1\u0091",
            "",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00a1",
            "\1\u00a2",
            "",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad\16\uffff\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1\1\u00b2\1\uffff\1\56",
            "",
            "\1\u00b3\3\uffff\1\56",
            "\1\u00b4",
            "",
            "",
            "\1\u00b5",
            "",
            "\1\u00b6",
            "\1\u00b7",
            "",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "",
            "\1\u00c4",
            "\1\u00c5",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "",
            "",
            "",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd\2\uffff\1\u00de",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00eb",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee\20\uffff\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00f6",
            "\1\u00f7",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u00fd",
            "\1\u00fe",
            "",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106\20\uffff\1\u0107",
            "\1\u0108",
            "",
            "\1\u0109",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u010b",
            "",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e\6\uffff\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "",
            "",
            "\1\u0112",
            "\1\u0113",
            "",
            "",
            "",
            "",
            "",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "",
            "\1\u011a",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\u0120",
            "\1\u0121",
            "\1\56",
            "",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0128",
            "\1\u0129",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u012b",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u012e",
            "",
            "\1\u012f",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0131\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\u0133",
            "\1\u0134",
            "",
            "\1\u0135",
            "\1\64\2\uffff\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0137",
            "\1\u0138",
            "",
            "\1\u0139",
            "\1\u013a",
            "",
            "\1\u013b",
            "",
            "",
            "\1\u013c",
            "\1\u013d",
            "",
            "\1\u013e\1\u013f",
            "",
            "\1\56",
            "\1\u0141",
            "\1\u0142",
            "",
            "\1\u0143",
            "\1\u0144",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0146",
            "\1\u0147",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u014a",
            "\1\u014b",
            "",
            "\1\u014c",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "",
            "\1\u0153",
            "\1\u0154",
            "",
            "",
            "\1\u0156",
            "\1\u0157",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "",
            "\1\u0159",
            "\1\u015a",
            "",
            "\1\u015b",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f",
            "",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\u0161",
            "\1\u0162",
            "",
            "\1\34\2\uffff\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\56",
            "",
            ""
    };
    // $ANTLR end "T__68"
    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    // $ANTLR end "T__69"
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    // $ANTLR end "T__70"
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    // $ANTLR end "T__71"
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    // $ANTLR end "T__72"
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    // $ANTLR end "T__73"
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    // $ANTLR end "T__74"
    static final short[][] DFA12_transition;
    // $ANTLR end "T__75"

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }
    // $ANTLR end "T__76"

    protected DFA12 dfa12 = new DFA12(this);
    // $ANTLR end "T__77"

    public PddlLexer ( ) {
    }
    // $ANTLR end "T__78"

    public PddlLexer (CharStream input) {
        this(input, new RecognizerSharedState());
    }
    // $ANTLR end "T__79"

    public PddlLexer (CharStream input, RecognizerSharedState state) {
        super(input, state);
    }
    // $ANTLR end "T__80"

    // delegates
    // delegators
    public Lexer[] getDelegates ( ) {
        return new Lexer[]{};
    }
    // $ANTLR end "T__81"

    @Override
    public String getGrammarFileName ( ) {
        return "Pddl.g";
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__67"
    public final void mT__67 ( ) throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:4:7: ( '#t' )
            // Pddl.g:4:9: '#t'
            {
                match("#t");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__68"
    public final void mT__68 ( ) throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:5:7: ( '(' )
            // Pddl.g:5:9: '('
            {
                match('(');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__69"
    public final void mT__69 ( ) throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:6:7: ( ')' )
            // Pddl.g:6:9: ')'
            {
                match(')');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__70"
    public final void mT__70 ( ) throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:7:7: ( '*' )
            // Pddl.g:7:9: '*'
            {
                match('*');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__71"
    public final void mT__71 ( ) throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:8:7: ( '+' )
            // Pddl.g:8:9: '+'
            {
                match('+');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__72"
    public final void mT__72 ( ) throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:9:7: ( '-' )
            // Pddl.g:9:9: '-'
            {
                match('-');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__73"
    public final void mT__73 ( ) throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:10:7: ( '/' )
            // Pddl.g:10:9: '/'
            {
                match('/');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__74"
    public final void mT__74 ( ) throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:11:7: ( ':action' )
            // Pddl.g:11:9: ':action'
            {
                match(":action");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__75"
    public final void mT__75 ( ) throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:12:7: ( ':condition' )
            // Pddl.g:12:9: ':condition'
            {
                match(":condition");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__76"
    public final void mT__76 ( ) throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:13:7: ( ':constants' )
            // Pddl.g:13:9: ':constants'
            {
                match(":constants");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__77"
    public final void mT__77 ( ) throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:14:7: ( ':constraint' )
            // Pddl.g:14:9: ':constraint'
            {
                match(":constraint");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__78"
    public final void mT__78 ( ) throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:15:7: ( ':constraints' )
            // Pddl.g:15:9: ':constraints'
            {
                match(":constraints");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__79"
    public final void mT__79 ( ) throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:16:7: ( ':derived' )
            // Pddl.g:16:9: ':derived'
            {
                match(":derived");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__80"
    public final void mT__80 ( ) throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:17:7: ( ':domain' )
            // Pddl.g:17:9: ':domain'
            {
                match(":domain");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__81"
    public final void mT__81 ( ) throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:18:7: ( ':duration' )
            // Pddl.g:18:9: ':duration'
            {
                match(":duration");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__82"
    public final void mT__82 ( ) throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:19:7: ( ':durative-action' )
            // Pddl.g:19:9: ':durative-action'
            {
                match(":durative-action");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__83"
    public final void mT__83 ( ) throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:20:7: ( ':effect' )
            // Pddl.g:20:9: ':effect'
            {
                match(":effect");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__84"
    public final void mT__84 ( ) throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:21:7: ( ':event' )
            // Pddl.g:21:9: ':event'
            {
                match(":event");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__85"
    public final void mT__85 ( ) throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:22:7: ( ':free_functions' )
            // Pddl.g:22:9: ':free_functions'
            {
                match(":free_functions");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__86"
    public final void mT__86 ( ) throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:23:7: ( ':functions' )
            // Pddl.g:23:9: ':functions'
            {
                match(":functions");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__87"
    public final void mT__87 ( ) throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:24:7: ( ':goal' )
            // Pddl.g:24:9: ':goal'
            {
                match(":goal");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__88"
    public final void mT__88 ( ) throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:25:7: ( ':init' )
            // Pddl.g:25:9: ':init'
            {
                match(":init");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__89"
    public final void mT__89 ( ) throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:26:7: ( ':metric' )
            // Pddl.g:26:9: ':metric'
            {
                match(":metric");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__90"
    public final void mT__90 ( ) throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:27:7: ( ':objects' )
            // Pddl.g:27:9: ':objects'
            {
                match(":objects");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__91"
    public final void mT__91 ( ) throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:28:7: ( ':parameters' )
            // Pddl.g:28:9: ':parameters'
            {
                match(":parameters");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__92"
    public final void mT__92 ( ) throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:29:7: ( ':precondition' )
            // Pddl.g:29:9: ':precondition'
            {
                match(":precondition");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__93"
    public final void mT__93 ( ) throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:30:7: ( ':predicates' )
            // Pddl.g:30:9: ':predicates'
            {
                match(":predicates");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__94"
    public final void mT__94 ( ) throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:31:7: ( ':process' )
            // Pddl.g:31:9: ':process'
            {
                match(":process");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__95"
    public final void mT__95 ( ) throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:32:7: ( ':requirements' )
            // Pddl.g:32:9: ':requirements'
            {
                match(":requirements");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__96"
    public final void mT__96 ( ) throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:33:7: ( ':types' )
            // Pddl.g:33:9: ':types'
            {
                match(":types");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__97"
    public final void mT__97 ( ) throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:34:7: ( '<' )
            // Pddl.g:34:9: '<'
            {
                match('<');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__98"
    public final void mT__98 ( ) throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:35:7: ( '<=' )
            // Pddl.g:35:9: '<='
            {
                match("<=");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__99"
    public final void mT__99 ( ) throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:36:7: ( '=' )
            // Pddl.g:36:9: '='
            {
                match('=');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__100"
    public final void mT__100 ( ) throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:37:8: ( '>' )
            // Pddl.g:37:10: '>'
            {
                match('>');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__101"
    public final void mT__101 ( ) throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:38:8: ( '>=' )
            // Pddl.g:38:10: '>='
            {
                match(">=");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__102"
    public final void mT__102 ( ) throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:39:8: ( '?duration' )
            // Pddl.g:39:10: '?duration'
            {
                match("?duration");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__103"
    public final void mT__103 ( ) throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:40:8: ( '^' )
            // Pddl.g:40:10: '^'
            {
                match('^');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "T__104"
    public final void mT__104 ( ) throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:41:8: ( 'abs' )
            // Pddl.g:41:10: 'abs'
            {
                match("abs");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__120"

    // $ANTLR start "T__105"
    public final void mT__105 ( ) throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:42:8: ( 'all' )
            // Pddl.g:42:10: 'all'
            {
                match("all");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__121"

    // $ANTLR start "T__106"
    public final void mT__106 ( ) throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:43:8: ( 'always' )
            // Pddl.g:43:10: 'always'
            {
                match("always");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__122"

    // $ANTLR start "T__107"
    public final void mT__107 ( ) throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:44:8: ( 'always-within' )
            // Pddl.g:44:10: 'always-within'
            {
                match("always-within");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__123"

    // $ANTLR start "T__108"
    public final void mT__108 ( ) throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:45:8: ( 'and' )
            // Pddl.g:45:10: 'and'
            {
                match("and");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__124"

    // $ANTLR start "T__109"
    public final void mT__109 ( ) throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:46:8: ( 'assign' )
            // Pddl.g:46:10: 'assign'
            {
                match("assign");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__125"

    // $ANTLR start "T__110"
    public final void mT__110 ( ) throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:47:8: ( 'at' )
            // Pddl.g:47:10: 'at'
            {
                match("at");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__126"

    // $ANTLR start "T__111"
    public final void mT__111 ( ) throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:48:8: ( 'at-most-once' )
            // Pddl.g:48:10: 'at-most-once'
            {
                match("at-most-once");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__127"

    // $ANTLR start "T__112"
    public final void mT__112 ( ) throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:49:8: ( 'cos' )
            // Pddl.g:49:10: 'cos'
            {
                match("cos");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__128"

    // $ANTLR start "T__113"
    public final void mT__113 ( ) throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:50:8: ( 'decrease' )
            // Pddl.g:50:10: 'decrease'
            {
                match("decrease");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__129"

    // $ANTLR start "T__114"
    public final void mT__114 ( ) throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:51:8: ( 'define' )
            // Pddl.g:51:10: 'define'
            {
                match("define");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__130"

    // $ANTLR start "T__115"
    public final void mT__115 ( ) throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:52:8: ( 'domain' )
            // Pddl.g:52:10: 'domain'
            {
                match("domain");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__131"

    // $ANTLR start "T__116"
    public final void mT__116 ( ) throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:53:8: ( 'either' )
            // Pddl.g:53:10: 'either'
            {
                match("either");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__132"

    // $ANTLR start "T__117"
    public final void mT__117 ( ) throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:54:8: ( 'end' )
            // Pddl.g:54:10: 'end'
            {
                match("end");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__133"

    // $ANTLR start "T__118"
    public final void mT__118 ( ) throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:55:8: ( 'exists' )
            // Pddl.g:55:10: 'exists'
            {
                match("exists");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__134"

    // $ANTLR start "T__119"
    public final void mT__119 ( ) throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:56:8: ( 'forall' )
            // Pddl.g:56:10: 'forall'
            {
                match("forall");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__135"

    // $ANTLR start "T__120"
    public final void mT__120 ( ) throws RecognitionException {
        try {
            int _type = T__120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:57:8: ( 'hold-after' )
            // Pddl.g:57:10: 'hold-after'
            {
                match("hold-after");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__136"

    // $ANTLR start "T__121"
    public final void mT__121 ( ) throws RecognitionException {
        try {
            int _type = T__121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:58:8: ( 'hold-during' )
            // Pddl.g:58:10: 'hold-during'
            {
                match("hold-during");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__137"

    // $ANTLR start "T__122"
    public final void mT__122 ( ) throws RecognitionException {
        try {
            int _type = T__122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:59:8: ( 'imply' )
            // Pddl.g:59:10: 'imply'
            {
                match("imply");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__138"

    // $ANTLR start "T__123"
    public final void mT__123 ( ) throws RecognitionException {
        try {
            int _type = T__123;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:60:8: ( 'increase' )
            // Pddl.g:60:10: 'increase'
            {
                match("increase");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__139"

    // $ANTLR start "T__124"
    public final void mT__124 ( ) throws RecognitionException {
        try {
            int _type = T__124;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:61:8: ( 'is-violated' )
            // Pddl.g:61:10: 'is-violated'
            {
                match("is-violated");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__140"

    // $ANTLR start "T__125"
    public final void mT__125 ( ) throws RecognitionException {
        try {
            int _type = T__125;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:62:8: ( 'maximize' )
            // Pddl.g:62:10: 'maximize'
            {
                match("maximize");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__141"

    // $ANTLR start "T__126"
    public final void mT__126 ( ) throws RecognitionException {
        try {
            int _type = T__126;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:63:8: ( 'minimize' )
            // Pddl.g:63:10: 'minimize'
            {
                match("minimize");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__142"

    // $ANTLR start "T__127"
    public final void mT__127 ( ) throws RecognitionException {
        try {
            int _type = T__127;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:64:8: ( 'not' )
            // Pddl.g:64:10: 'not'
            {
                match("not");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__143"

    // $ANTLR start "T__128"
    public final void mT__128 ( ) throws RecognitionException {
        try {
            int _type = T__128;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:65:8: ( 'number' )
            // Pddl.g:65:10: 'number'
            {
                match("number");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REQUIRE_KEY"

    // $ANTLR start "T__129"
    public final void mT__129 ( ) throws RecognitionException {
        try {
            int _type = T__129;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:66:8: ( 'oneof' )
            // Pddl.g:66:10: 'oneof'
            {
                match("oneof");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "T__130"
    public final void mT__130 ( ) throws RecognitionException {
        try {
            int _type = T__130;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:67:8: ( 'or' )
            // Pddl.g:67:10: 'or'
            {
                match("or");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "T__131"
    public final void mT__131 ( ) throws RecognitionException {
        try {
            int _type = T__131;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:68:8: ( 'over' )
            // Pddl.g:68:10: 'over'
            {
                match("over");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ANY_CHAR"

    // $ANTLR start "T__132"
    public final void mT__132 ( ) throws RecognitionException {
        try {
            int _type = T__132;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:69:8: ( 'preference' )
            // Pddl.g:69:10: 'preference'
            {
                match("preference");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "T__133"
    public final void mT__133 ( ) throws RecognitionException {
        try {
            int _type = T__133;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:70:8: ( 'problem' )
            // Pddl.g:70:10: 'problem'
            {
                match("problem");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "T__134"
    public final void mT__134 ( ) throws RecognitionException {
        try {
            int _type = T__134;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:71:8: ( 'scale-down' )
            // Pddl.g:71:10: 'scale-down'
            {
                match("scale-down");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "T__135"
    public final void mT__135 ( ) throws RecognitionException {
        try {
            int _type = T__135;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:72:8: ( 'scale-up' )
            // Pddl.g:72:10: 'scale-up'
            {
                match("scale-up");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "T__136"
    public final void mT__136 ( ) throws RecognitionException {
        try {
            int _type = T__136;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:73:8: ( 'sin' )
            // Pddl.g:73:10: 'sin'
            {
                match("sin");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "T__137"
    public final void mT__137 ( ) throws RecognitionException {
        try {
            int _type = T__137;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:74:8: ( 'sometime' )
            // Pddl.g:74:10: 'sometime'
            {
                match("sometime");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__138"
    public final void mT__138 ( ) throws RecognitionException {
        try {
            int _type = T__138;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:75:8: ( 'sometime-after' )
            // Pddl.g:75:10: 'sometime-after'
            {
                match("sometime-after");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__139"
    public final void mT__139 ( ) throws RecognitionException {
        try {
            int _type = T__139;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:76:8: ( 'sometime-before' )
            // Pddl.g:76:10: 'sometime-before'
            {
                match("sometime-before");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__140"
    public final void mT__140 ( ) throws RecognitionException {
        try {
            int _type = T__140;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:77:8: ( 'start' )
            // Pddl.g:77:10: 'start'
            {
                match("start");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__141"
    public final void mT__141 ( ) throws RecognitionException {
        try {
            int _type = T__141;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:78:8: ( 'unknown' )
            // Pddl.g:78:10: 'unknown'
            {
                match("unknown");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__142"
    public final void mT__142 ( ) throws RecognitionException {
        try {
            int _type = T__142;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:79:8: ( 'when' )
            // Pddl.g:79:10: 'when'
            {
                match("when");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "T__143"
    public final void mT__143 ( ) throws RecognitionException {
        try {
            int _type = T__143;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:80:8: ( 'within' )
            // Pddl.g:80:10: 'within'
            {
                match("within");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "REQUIRE_KEY"
    public final void mREQUIRE_KEY ( ) throws RecognitionException {
        try {
            int _type = REQUIRE_KEY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:573:5: ( ':strips' | ':typing' | ':negative-preconditions' | ':disjunctive-preconditions' | ':equality' | ':existential-preconditions' | ':universal-preconditions' | ':quantified-preconditions' | ':conditional-effects' | ':fluents' | ':adl' | ':durative-actions' | ':derived-predicates' | ':timed-initial-literals' | ':preferences' | ':constraints' )
            int alt1 = 16;
            int LA1_0 = input.LA(1);
            if ((LA1_0 == ':')) {
                switch (input.LA(2)) {
                    case 's': {
                        alt1 = 1;
                    }
                    break;
                    case 't': {
                        int LA1_3 = input.LA(3);
                        if ((LA1_3 == 'y')) {
                            alt1 = 2;
                        } else if ((LA1_3 == 'i')) {
                            alt1 = 14;
                        } else {
                            int nvaeMark = input.mark();
                            try {
                                for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                    input.consume();
                                }
                                NoViableAltException nvae
                                        = new NoViableAltException("", 1, 3, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    }
                    break;
                    case 'n': {
                        alt1 = 3;
                    }
                    break;
                    case 'd': {
                        switch (input.LA(3)) {
                            case 'i': {
                                alt1 = 4;
                            }
                            break;
                            case 'u': {
                                alt1 = 12;
                            }
                            break;
                            case 'e': {
                                alt1 = 13;
                            }
                            break;
                            default:
                                int nvaeMark = input.mark();
                                try {
                                    for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                        input.consume();
                                    }
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 1, 5, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                        }
                    }
                    break;
                    case 'e': {
                        int LA1_6 = input.LA(3);
                        if ((LA1_6 == 'q')) {
                            alt1 = 5;
                        } else if ((LA1_6 == 'x')) {
                            alt1 = 6;
                        } else {
                            int nvaeMark = input.mark();
                            try {
                                for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                    input.consume();
                                }
                                NoViableAltException nvae
                                        = new NoViableAltException("", 1, 6, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    }
                    break;
                    case 'u': {
                        alt1 = 7;
                    }
                    break;
                    case 'q': {
                        alt1 = 8;
                    }
                    break;
                    case 'c': {
                        int LA1_9 = input.LA(3);
                        if ((LA1_9 == 'o')) {
                            int LA1_20 = input.LA(4);
                            if ((LA1_20 == 'n')) {
                                int LA1_21 = input.LA(5);
                                if ((LA1_21 == 'd')) {
                                    alt1 = 9;
                                } else if ((LA1_21 == 's')) {
                                    alt1 = 16;
                                } else {
                                    int nvaeMark = input.mark();
                                    try {
                                        for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
                                            input.consume();
                                        }
                                        NoViableAltException nvae
                                                = new NoViableAltException("", 1, 21, input);
                                        throw nvae;
                                    } finally {
                                        input.rewind(nvaeMark);
                                    }
                                }

                            } else {
                                int nvaeMark = input.mark();
                                try {
                                    for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
                                        input.consume();
                                    }
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 1, 20, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                            }

                        } else {
                            int nvaeMark = input.mark();
                            try {
                                for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                    input.consume();
                                }
                                NoViableAltException nvae
                                        = new NoViableAltException("", 1, 9, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    }
                    break;
                    case 'f': {
                        alt1 = 10;
                    }
                    break;
                    case 'a': {
                        alt1 = 11;
                    }
                    break;
                    case 'p': {
                        alt1 = 15;
                    }
                    break;
                    default:
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 1, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                NoViableAltException nvae
                        = new NoViableAltException("", 1, 0, input);
                throw nvae;
            }

            switch (alt1) {
                case 1: // Pddl.g:573:7: ':strips'
                {
                    match(":strips");

                }
                break;
                case 2: // Pddl.g:574:7: ':typing'
                {
                    match(":typing");

                }
                break;
                case 3: // Pddl.g:575:7: ':negative-preconditions'
                {
                    match(":negative-preconditions");

                }
                break;
                case 4: // Pddl.g:576:7: ':disjunctive-preconditions'
                {
                    match(":disjunctive-preconditions");

                }
                break;
                case 5: // Pddl.g:577:7: ':equality'
                {
                    match(":equality");

                }
                break;
                case 6: // Pddl.g:578:7: ':existential-preconditions'
                {
                    match(":existential-preconditions");

                }
                break;
                case 7: // Pddl.g:579:7: ':universal-preconditions'
                {
                    match(":universal-preconditions");

                }
                break;
                case 8: // Pddl.g:580:7: ':quantified-preconditions'
                {
                    match(":quantified-preconditions");

                }
                break;
                case 9: // Pddl.g:581:7: ':conditional-effects'
                {
                    match(":conditional-effects");

                }
                break;
                case 10: // Pddl.g:582:7: ':fluents'
                {
                    match(":fluents");

                }
                break;
                case 11: // Pddl.g:583:7: ':adl'
                {
                    match(":adl");

                }
                break;
                case 12: // Pddl.g:584:7: ':durative-actions'
                {
                    match(":durative-actions");

                }
                break;
                case 13: // Pddl.g:585:7: ':derived-predicates'
                {
                    match(":derived-predicates");

                }
                break;
                case 14: // Pddl.g:586:7: ':timed-initial-literals'
                {
                    match(":timed-initial-literals");

                }
                break;
                case 15: // Pddl.g:587:7: ':preferences'
                {
                    match(":preferences");

                }
                break;
                case 16: // Pddl.g:588:7: ':constraints'
                {
                    match(":constraints");

                }
                break;

            }
            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "NAME"
    public final void mNAME ( ) throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:589:5: ( LETTER ( ANY_CHAR )* )
            // Pddl.g:589:10: LETTER ( ANY_CHAR )*
            {
                mLETTER();

                // Pddl.g:589:17: ( ANY_CHAR )*
                loop2:
                while (true) {
                    int alt2 = 2;
                    int LA2_0 = input.LA(1);
                    if ((LA2_0 == '-' || (LA2_0 >= '0' && LA2_0 <= '9') || (LA2_0 >= 'A' && LA2_0 <= 'Z') || LA2_0 == '_' || (LA2_0 >= 'a' && LA2_0 <= 'z'))) {
                        alt2 = 1;
                    }

                    switch (alt2) {
                        case 1: // Pddl.g:
                        {
                            if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            break loop2;
                    }
                }

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "LETTER"
    public final void mLETTER ( ) throws RecognitionException {
        try {
            // Pddl.g:591:16: ( 'a' .. 'z' | 'A' .. 'Z' )
            // Pddl.g:
            {
                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }
            }

        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "ANY_CHAR"
    public final void mANY_CHAR ( ) throws RecognitionException {
        try {
            // Pddl.g:593:18: ( LETTER | '0' .. '9' | '-' | '_' )
            // Pddl.g:
            {
                if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }
            }

        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE ( ) throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:595:10: ( '?' LETTER ( ANY_CHAR )* )
            // Pddl.g:595:12: '?' LETTER ( ANY_CHAR )*
            {
                match('?');
                mLETTER();

                // Pddl.g:595:23: ( ANY_CHAR )*
                loop3:
                while (true) {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);
                    if ((LA3_0 == '-' || (LA3_0 >= '0' && LA3_0 <= '9') || (LA3_0 >= 'A' && LA3_0 <= 'Z') || LA3_0 == '_' || (LA3_0 >= 'a' && LA3_0 <= 'z'))) {
                        alt3 = 1;
                    }

                    switch (alt3) {
                        case 1: // Pddl.g:
                        {
                            if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            break loop3;
                    }
                }

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "NUMBER"
    public final void mNUMBER ( ) throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:597:8: ( ( '-' )? ( DIGIT )+ ( '.' ( DIGIT )+ )? | '#t' )
            int alt8 = 2;
            int LA8_0 = input.LA(1);
            if ((LA8_0 == '-' || (LA8_0 >= '0' && LA8_0 <= '9'))) {
                alt8 = 1;
            } else if ((LA8_0 == '#')) {
                alt8 = 2;
            } else {
                NoViableAltException nvae
                        = new NoViableAltException("", 8, 0, input);
                throw nvae;
            }

            switch (alt8) {
                case 1: // Pddl.g:597:10: ( '-' )? ( DIGIT )+ ( '.' ( DIGIT )+ )?
                {
                    // Pddl.g:597:10: ( '-' )?
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);
                    if ((LA4_0 == '-')) {
                        alt4 = 1;
                    }
                    switch (alt4) {
                        case 1: // Pddl.g:597:11: '-'
                        {
                            match('-');
                        }
                        break;

                    }

                    // Pddl.g:597:17: ( DIGIT )+
                    int cnt5 = 0;
                    loop5:
                    while (true) {
                        int alt5 = 2;
                        int LA5_0 = input.LA(1);
                        if (((LA5_0 >= '0' && LA5_0 <= '9'))) {
                            alt5 = 1;
                        }

                        switch (alt5) {
                            case 1: // Pddl.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                } else {
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }
                            }
                            break;

                            default:
                                if (cnt5 >= 1) {
                                    break loop5;
                                }
                                EarlyExitException eee = new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    }

                    // Pddl.g:597:24: ( '.' ( DIGIT )+ )?
                    int alt7 = 2;
                    int LA7_0 = input.LA(1);
                    if ((LA7_0 == '.')) {
                        alt7 = 1;
                    }
                    switch (alt7) {
                        case 1: // Pddl.g:597:25: '.' ( DIGIT )+
                        {
                            match('.');
                            // Pddl.g:597:29: ( DIGIT )+
                            int cnt6 = 0;
                            loop6:
                            while (true) {
                                int alt6 = 2;
                                int LA6_0 = input.LA(1);
                                if (((LA6_0 >= '0' && LA6_0 <= '9'))) {
                                    alt6 = 1;
                                }

                                switch (alt6) {
                                    case 1: // Pddl.g:
                                    {
                                        if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                            input.consume();
                                        } else {
                                            MismatchedSetException mse = new MismatchedSetException(null, input);
                                            recover(mse);
                                            throw mse;
                                        }
                                    }
                                    break;

                                    default:
                                        if (cnt6 >= 1) {
                                            break loop6;
                                        }
                                        EarlyExitException eee = new EarlyExitException(6, input);
                                        throw eee;
                                }
                                cnt6++;
                            }

                        }
                        break;

                    }

                }
                break;
                case 2: // Pddl.g:597:40: '#t'
                {
                    match("#t");

                }
                break;

            }
            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "DIGIT"
    public final void mDIGIT ( ) throws RecognitionException {
        try {
            // Pddl.g:599:15: ( '0' .. '9' )
            // Pddl.g:
            {
                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }
            }

        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT ( ) throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:602:5: ( ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // Pddl.g:602:7: ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
                match(';');
                // Pddl.g:602:11: (~ ( '\\n' | '\\r' ) )*
                loop9:
                while (true) {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);
                    if (((LA9_0 >= '\u0000' && LA9_0 <= '\t') || (LA9_0 >= '\u000B' && LA9_0 <= '\f') || (LA9_0 >= '\u000E' && LA9_0 <= '\uFFFF'))) {
                        alt9 = 1;
                    }

                    switch (alt9) {
                        case 1: // Pddl.g:
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\f') || (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            break loop9;
                    }
                }

                // Pddl.g:602:25: ( '\\r' )?
                int alt10 = 2;
                int LA10_0 = input.LA(1);
                if ((LA10_0 == '\r')) {
                    alt10 = 1;
                }
                switch (alt10) {
                    case 1: // Pddl.g:602:25: '\\r'
                    {
                        match('\r');
                    }
                    break;

                }

                match('\n');
                _channel = HIDDEN;
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE ( ) throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Pddl.g:606:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // Pddl.g:606:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
                // Pddl.g:606:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
                int cnt11 = 0;
                loop11:
                while (true) {
                    int alt11 = 2;
                    int LA11_0 = input.LA(1);
                    if (((LA11_0 >= '\t' && LA11_0 <= '\n') || LA11_0 == '\r' || LA11_0 == ' ')) {
                        alt11 = 1;
                    }

                    switch (alt11) {
                        case 1: // Pddl.g:
                        {
                            if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || input.LA(1) == '\r' || input.LA(1) == ' ') {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            if (cnt11 >= 1) {
                                break loop11;
                            }
                            EarlyExitException eee = new EarlyExitException(11, input);
                            throw eee;
                    }
                    cnt11++;
                }

                _channel = HIDDEN;
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    @Override
    public void mTokens ( ) throws RecognitionException {
        // Pddl.g:1:8: ( T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | REQUIRE_KEY | NAME | VARIABLE | NUMBER | LINE_COMMENT | WHITESPACE )
        int alt12 = 83;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1: // Pddl.g:1:10: T__67
            {
                mT__67();

            }
            break;
            case 2: // Pddl.g:1:16: T__68
            {
                mT__68();

            }
            break;
            case 3: // Pddl.g:1:22: T__69
            {
                mT__69();

            }
            break;
            case 4: // Pddl.g:1:28: T__70
            {
                mT__70();

            }
            break;
            case 5: // Pddl.g:1:34: T__71
            {
                mT__71();

            }
            break;
            case 6: // Pddl.g:1:40: T__72
            {
                mT__72();

            }
            break;
            case 7: // Pddl.g:1:46: T__73
            {
                mT__73();

            }
            break;
            case 8: // Pddl.g:1:52: T__74
            {
                mT__74();

            }
            break;
            case 9: // Pddl.g:1:58: T__75
            {
                mT__75();

            }
            break;
            case 10: // Pddl.g:1:64: T__76
            {
                mT__76();

            }
            break;
            case 11: // Pddl.g:1:70: T__77
            {
                mT__77();

            }
            break;
            case 12: // Pddl.g:1:76: T__78
            {
                mT__78();

            }
            break;
            case 13: // Pddl.g:1:82: T__79
            {
                mT__79();

            }
            break;
            case 14: // Pddl.g:1:88: T__80
            {
                mT__80();

            }
            break;
            case 15: // Pddl.g:1:94: T__81
            {
                mT__81();

            }
            break;
            case 16: // Pddl.g:1:100: T__82
            {
                mT__82();

            }
            break;
            case 17: // Pddl.g:1:106: T__83
            {
                mT__83();

            }
            break;
            case 18: // Pddl.g:1:112: T__84
            {
                mT__84();

            }
            break;
            case 19: // Pddl.g:1:118: T__85
            {
                mT__85();

            }
            break;
            case 20: // Pddl.g:1:124: T__86
            {
                mT__86();

            }
            break;
            case 21: // Pddl.g:1:130: T__87
            {
                mT__87();

            }
            break;
            case 22: // Pddl.g:1:136: T__88
            {
                mT__88();

            }
            break;
            case 23: // Pddl.g:1:142: T__89
            {
                mT__89();

            }
            break;
            case 24: // Pddl.g:1:148: T__90
            {
                mT__90();

            }
            break;
            case 25: // Pddl.g:1:154: T__91
            {
                mT__91();

            }
            break;
            case 26: // Pddl.g:1:160: T__92
            {
                mT__92();

            }
            break;
            case 27: // Pddl.g:1:166: T__93
            {
                mT__93();

            }
            break;
            case 28: // Pddl.g:1:172: T__94
            {
                mT__94();

            }
            break;
            case 29: // Pddl.g:1:178: T__95
            {
                mT__95();

            }
            break;
            case 30: // Pddl.g:1:184: T__96
            {
                mT__96();

            }
            break;
            case 31: // Pddl.g:1:190: T__97
            {
                mT__97();

            }
            break;
            case 32: // Pddl.g:1:196: T__98
            {
                mT__98();

            }
            break;
            case 33: // Pddl.g:1:202: T__99
            {
                mT__99();

            }
            break;
            case 34: // Pddl.g:1:208: T__100
            {
                mT__100();

            }
            break;
            case 35: // Pddl.g:1:215: T__101
            {
                mT__101();

            }
            break;
            case 36: // Pddl.g:1:222: T__102
            {
                mT__102();

            }
            break;
            case 37: // Pddl.g:1:229: T__103
            {
                mT__103();

            }
            break;
            case 38: // Pddl.g:1:236: T__104
            {
                mT__104();

            }
            break;
            case 39: // Pddl.g:1:243: T__105
            {
                mT__105();

            }
            break;
            case 40: // Pddl.g:1:250: T__106
            {
                mT__106();

            }
            break;
            case 41: // Pddl.g:1:257: T__107
            {
                mT__107();

            }
            break;
            case 42: // Pddl.g:1:264: T__108
            {
                mT__108();

            }
            break;
            case 43: // Pddl.g:1:271: T__109
            {
                mT__109();

            }
            break;
            case 44: // Pddl.g:1:278: T__110
            {
                mT__110();

            }
            break;
            case 45: // Pddl.g:1:285: T__111
            {
                mT__111();

            }
            break;
            case 46: // Pddl.g:1:292: T__112
            {
                mT__112();

            }
            break;
            case 47: // Pddl.g:1:299: T__113
            {
                mT__113();

            }
            break;
            case 48: // Pddl.g:1:306: T__114
            {
                mT__114();

            }
            break;
            case 49: // Pddl.g:1:313: T__115
            {
                mT__115();

            }
            break;
            case 50: // Pddl.g:1:320: T__116
            {
                mT__116();

            }
            break;
            case 51: // Pddl.g:1:327: T__117
            {
                mT__117();

            }
            break;
            case 52: // Pddl.g:1:334: T__118
            {
                mT__118();

            }
            break;
            case 53: // Pddl.g:1:341: T__119
            {
                mT__119();

            }
            break;
            case 54: // Pddl.g:1:348: T__120
            {
                mT__120();

            }
            break;
            case 55: // Pddl.g:1:355: T__121
            {
                mT__121();

            }
            break;
            case 56: // Pddl.g:1:362: T__122
            {
                mT__122();

            }
            break;
            case 57: // Pddl.g:1:369: T__123
            {
                mT__123();

            }
            break;
            case 58: // Pddl.g:1:376: T__124
            {
                mT__124();

            }
            break;
            case 59: // Pddl.g:1:383: T__125
            {
                mT__125();

            }
            break;
            case 60: // Pddl.g:1:390: T__126
            {
                mT__126();

            }
            break;
            case 61: // Pddl.g:1:397: T__127
            {
                mT__127();

            }
            break;
            case 62: // Pddl.g:1:404: T__128
            {
                mT__128();

            }
            break;
            case 63: // Pddl.g:1:411: T__129
            {
                mT__129();

            }
            break;
            case 64: // Pddl.g:1:418: T__130
            {
                mT__130();

            }
            break;
            case 65: // Pddl.g:1:425: T__131
            {
                mT__131();

            }
            break;
            case 66: // Pddl.g:1:432: T__132
            {
                mT__132();

            }
            break;
            case 67: // Pddl.g:1:439: T__133
            {
                mT__133();

            }
            break;
            case 68: // Pddl.g:1:446: T__134
            {
                mT__134();

            }
            break;
            case 69: // Pddl.g:1:453: T__135
            {
                mT__135();

            }
            break;
            case 70: // Pddl.g:1:460: T__136
            {
                mT__136();

            }
            break;
            case 71: // Pddl.g:1:467: T__137
            {
                mT__137();

            }
            break;
            case 72: // Pddl.g:1:474: T__138
            {
                mT__138();

            }
            break;
            case 73: // Pddl.g:1:481: T__139
            {
                mT__139();

            }
            break;
            case 74: // Pddl.g:1:488: T__140
            {
                mT__140();

            }
            break;
            case 75: // Pddl.g:1:495: T__141
            {
                mT__141();

            }
            break;
            case 76: // Pddl.g:1:502: T__142
            {
                mT__142();

            }
            break;
            case 77: // Pddl.g:1:509: T__143
            {
                mT__143();

            }
            break;
            case 78: // Pddl.g:1:516: REQUIRE_KEY
            {
                mREQUIRE_KEY();

            }
            break;
            case 79: // Pddl.g:1:528: NAME
            {
                mNAME();

            }
            break;
            case 80: // Pddl.g:1:533: VARIABLE
            {
                mVARIABLE();

            }
            break;
            case 81: // Pddl.g:1:542: NUMBER
            {
                mNUMBER();

            }
            break;
            case 82: // Pddl.g:1:549: LINE_COMMENT
            {
                mLINE_COMMENT();

            }
            break;
            case 83: // Pddl.g:1:562: WHITESPACE
            {
                mWHITESPACE();

            }
            break;

        }
    }

    protected class DFA12 extends DFA {

        public DFA12 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }

        @Override
        public String getDescription ( ) {
            return "1:1: Tokens : ( T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | REQUIRE_KEY | NAME | VARIABLE | NUMBER | LINE_COMMENT | WHITESPACE );";
        }
    }

}
