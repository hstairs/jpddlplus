// $ANTLR 3.5.2 Pddl.g 2016-12-12 12:26:56
package parser;

import java.util.HashMap;

@SuppressWarnings("all")
public class PddlParser extends Parser {

    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACTION", "AND_EFFECT",
            "AND_GD", "ANY_CHAR", "ASSIGN_EFFECT", "BINARY_OP", "COMPARISON_GD", "CONSTANTS",
            "CONSTRAINT", "COS", "DIGIT", "DOMAIN", "DOMAIN_NAME", "DURATIVE_ACTION",
            "EFFECT", "EITHER_TYPE", "EQUALITY_CON", "EVENT", "EXISTS_GD", "FORALL_EFFECT",
            "FORALL_GD", "FORMULAINIT", "FREE_FUNCTIONS", "FUNCTIONS", "FUNC_HEAD",
            "GLOBAL_CONSTRAINT", "GOAL", "IMPLY_GD", "INIT", "INIT_AT", "INIT_EQ",
            "LETTER", "LINE_COMMENT", "MINUS_OP", "MULTI_OP", "NAME", "NOT_EFFECT",
            "NOT_GD", "NOT_PRED_INIT", "NUMBER", "OBJECTS", "ONEOF", "OR_GD", "PRECONDITION",
            "PREDICATES", "PRED_HEAD", "PRED_INST", "PROBLEM", "PROBLEM_CONSTRAINT",
            "PROBLEM_DOMAIN", "PROBLEM_METRIC", "PROBLEM_NAME", "PROCESS", "REQUIREMENTS",
            "REQUIRE_KEY", "SIN", "TYPES", "UNARY_MINUS", "UNKNOWN", "VARIABLE", "WHEN_EFFECT",
            "WHITESPACE", "'#t'", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "':action'",
            "':condition'", "':constants'", "':constraint'", "':constraints'", "':derived'",
            "':domain'", "':duration'", "':durative-action'", "':effect'", "':event'",
            "':free_functions'", "':functions'", "':goal'", "':init'", "':metric'",
            "':objects'", "':parameters'", "':precondition'", "':predicates'", "':process'",
            "':requirements'", "':types'", "'<'", "'<='", "'='", "'>'", "'>='", "'?duration'",
            "'^'", "'abs'", "'all'", "'always'", "'always-within'", "'and'", "'assign'",
            "'at'", "'at-most-once'", "'cos'", "'decrease'", "'define'", "'domain'",
            "'either'", "'end'", "'exists'", "'forall'", "'hold-after'", "'hold-during'",
            "'imply'", "'increase'", "'is-violated'", "'maximize'", "'minimize'",
            "'not'", "'number'", "'oneof'", "'or'", "'over'", "'preference'", "'problem'",
            "'scale-down'", "'scale-up'", "'sin'", "'sometime'", "'sometime-after'",
            "'sometime-before'", "'start'", "'unknown'", "'when'", "'within'"
    };
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
    public static final BitSet FOLLOW_domain_in_pddlDoc394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_problem_in_pddlDoc398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_domain413 = new BitSet(new long[]{0x0000000000000000L, 0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_domain415 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_domainName_in_domain417 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_requireDef_in_domain425 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_typesDef_in_domain434 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_constantsDef_in_domain443 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_predicatesDef_in_domain452 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_functionsDef_in_domain461 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_free_functionsDef_in_domain470 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_constraints_in_domain479 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_structureDef_in_domain488 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    // $ANTLR end "pddlDoc"
    public static final BitSet FOLLOW_69_in_domain497 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_free_functionsDef581 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000200000L});
    // $ANTLR end "domain"
    public static final BitSet FOLLOW_85_in_free_functionsDef583 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_functionList_in_free_functionsDef585 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "free_functionsDef"
    public static final BitSet FOLLOW_69_in_free_functionsDef587 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_domainName610 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L});
    // $ANTLR end "domainName"
    public static final BitSet FOLLOW_115_in_domainName612 = new BitSet(new long[]{0x0000010000000000L});

    ;
    public static final BitSet FOLLOW_NAME_in_domainName614 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "requireDef"
    public static final BitSet FOLLOW_69_in_domainName616 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_requireDef643 = new BitSet(new long[]{0x0000000000000000L, 0x0000000080000000L});
    // $ANTLR end "typesDef"
    public static final BitSet FOLLOW_95_in_requireDef645 = new BitSet(new long[]{0x0800000000000000L});

    ;
    public static final BitSet FOLLOW_REQUIRE_KEY_in_requireDef647 = new BitSet(new long[]{0x0800000000000000L, 0x0000000000000020L});
    // $ANTLR end "typedNameList"
    public static final BitSet FOLLOW_69_in_requireDef650 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_typesDef671 = new BitSet(new long[]{0x0000000000000000L, 0x0000000100000000L});
    // $ANTLR end "singleTypeNameList"
    public static final BitSet FOLLOW_96_in_typesDef673 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_typedNameList_in_typesDef675 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "type"
    public static final BitSet FOLLOW_69_in_typesDef677 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_typedNameList704 = new BitSet(new long[]{0x0000010000000002L});
    // $ANTLR end "primType"
    public static final BitSet FOLLOW_singleTypeNameList_in_typedNameList709 = new BitSet(new long[]{0x0000010000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_typedNameList712 = new BitSet(new long[]{0x0000010000000002L});
    // $ANTLR end "functionsDef"
    public static final BitSet FOLLOW_NAME_in_singleTypeNameList732 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000100L});

    ;
    public static final BitSet FOLLOW_72_in_singleTypeNameList735 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000010L});
    // $ANTLR end "functionList"
    public static final BitSet FOLLOW_type_in_singleTypeNameList739 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_type766 = new BitSet(new long[]{0x0000000000000000L, 0x0010000000000000L});
    // $ANTLR end "atomicFunctionSkeleton"
    public static final BitSet FOLLOW_116_in_type768 = new BitSet(new long[]{0x0000010000000000L});

    ;
    public static final BitSet FOLLOW_primType_in_type770 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});
    // $ANTLR end "functionSymbol"
    public static final BitSet FOLLOW_69_in_type773 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_primType_in_type794 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "functionType"
    public static final BitSet FOLLOW_NAME_in_primType804 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_functionsDef814 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000400000L});
    // $ANTLR end "constantsDef"
    public static final BitSet FOLLOW_86_in_functionsDef816 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_functionList_in_functionsDef818 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "predicatesDef"
    public static final BitSet FOLLOW_69_in_functionsDef820 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_functionList841 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000110L});
    // $ANTLR end "atomicFormulaSkeleton"
    public static final BitSet FOLLOW_72_in_functionList845 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L});

    ;
    public static final BitSet FOLLOW_functionType_in_functionList847 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000010L});
    // $ANTLR end "predicate"
    public static final BitSet FOLLOW_68_in_atomicFunctionSkeleton863 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_functionSymbol_in_atomicFunctionSkeleton866 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    // $ANTLR end "typedVariableList"
    public static final BitSet FOLLOW_typedVariableList_in_atomicFunctionSkeleton869 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_atomicFunctionSkeleton871 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "singleTypeVarList"
    public static final BitSet FOLLOW_128_in_functionType894 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_constantsDef905 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000001000L});
    // $ANTLR end "constraints"
    public static final BitSet FOLLOW_76_in_constantsDef907 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_typedNameList_in_constantsDef909 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "structureDef"
    public static final BitSet FOLLOW_69_in_constantsDef911 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_predicatesDef931 = new BitSet(new long[]{0x0000000000000000L, 0x0000000020000000L});
    // $ANTLR end "actionDef"
    public static final BitSet FOLLOW_93_in_predicatesDef933 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_atomicFormulaSkeleton_in_predicatesDef935 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    // $ANTLR end "eventDef"
    public static final BitSet FOLLOW_69_in_predicatesDef938 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_atomicFormulaSkeleton959 = new BitSet(new long[]{0x0000010000000000L});
    // $ANTLR end "processDef"
    public static final BitSet FOLLOW_predicate_in_atomicFormulaSkeleton962 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_atomicFormulaSkeleton965 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "constraintDef"
    public static final BitSet FOLLOW_69_in_atomicFormulaSkeleton967 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_predicate978 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "actionSymbol"
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList993 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000001L});

    ;
    public static final BitSet FOLLOW_singleTypeVarList_in_typedVariableList998 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000001L});
    // $ANTLR end "constraintSymbol"
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList1001 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000001L});

    ;
    public static final BitSet FOLLOW_VARIABLE_in_singleTypeVarList1021 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000101L});
    // $ANTLR end "actionDefBody"
    public static final BitSet FOLLOW_72_in_singleTypeVarList1024 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_type_in_singleTypeVarList1028 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "constraintDefBody"
    public static final BitSet FOLLOW_68_in_constraints1059 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000004000L});

    ;
    public static final BitSet FOLLOW_78_in_constraints1062 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "goalDesc"
    public static final BitSet FOLLOW_conGD_in_constraints1065 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_constraints1067 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "equality"
    public static final BitSet FOLLOW_actionDef_in_structureDef1079 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_durativeActionDef_in_structureDef1084 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "fComp"
    public static final BitSet FOLLOW_derivedDef_in_structureDef1089 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_constraintDef_in_structureDef1094 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "atomicTermFormula"
    public static final BitSet FOLLOW_processDef_in_structureDef1099 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_eventDef_in_structureDef1104 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "term"
    public static final BitSet FOLLOW_68_in_actionDef1119 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000400L});

    ;
    public static final BitSet FOLLOW_74_in_actionDef1121 = new BitSet(new long[]{0x0000010000000000L});
    // $ANTLR end "durativeActionDef"
    public static final BitSet FOLLOW_actionSymbol_in_actionDef1123 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008000000L});

    ;
    public static final BitSet FOLLOW_91_in_actionDef1132 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "daDefBody"
    public static final BitSet FOLLOW_68_in_actionDef1135 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_actionDef1137 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "daGD"
    public static final BitSet FOLLOW_69_in_actionDef1139 = new BitSet(new long[]{0x0000000000000000L, 0x0000000010080020L});

    ;
    public static final BitSet FOLLOW_actionDefBody_in_actionDef1152 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "prefTimedGD"
    public static final BitSet FOLLOW_69_in_actionDef1154 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_eventDef1187 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000100000L});
    // $ANTLR end "timedGD"
    public static final BitSet FOLLOW_84_in_eventDef1189 = new BitSet(new long[]{0x0000010000000000L});

    ;
    public static final BitSet FOLLOW_actionSymbol_in_eventDef1191 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008000000L});
    // $ANTLR end "timeSpecifier"
    public static final BitSet FOLLOW_91_in_eventDef1200 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_eventDef1203 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    // $ANTLR end "interval"
    public static final BitSet FOLLOW_typedVariableList_in_eventDef1205 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_eventDef1207 = new BitSet(new long[]{0x0000000000000000L, 0x0000000010080020L});
    // $ANTLR end "derivedDef"
    public static final BitSet FOLLOW_actionDefBody_in_eventDef1220 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_eventDef1222 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "fExp"
    public static final BitSet FOLLOW_68_in_processDef1255 = new BitSet(new long[]{0x0000000000000000L, 0x0000000040000000L});

    ;
    public static final BitSet FOLLOW_94_in_processDef1257 = new BitSet(new long[]{0x0000010000000000L});
    // $ANTLR end "fExp2"
    public static final BitSet FOLLOW_actionSymbol_in_processDef1259 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008000000L});

    ;
    public static final BitSet FOLLOW_91_in_processDef1268 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "fHead"
    public static final BitSet FOLLOW_68_in_processDef1271 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_processDef1273 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "effect"
    public static final BitSet FOLLOW_69_in_processDef1275 = new BitSet(new long[]{0x0000000000000000L, 0x0000000010080020L});

    ;
    public static final BitSet FOLLOW_actionDefBody_in_processDef1288 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "cEffect"
    public static final BitSet FOLLOW_69_in_processDef1290 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_constraintDef1324 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000002000L});
    // $ANTLR end "pEffect"
    public static final BitSet FOLLOW_77_in_constraintDef1326 = new BitSet(new long[]{0x0000010000000000L});

    ;
    public static final BitSet FOLLOW_constraintSymbol_in_constraintDef1328 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008000000L});
    // $ANTLR end "condEffect"
    public static final BitSet FOLLOW_91_in_constraintDef1337 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_constraintDef1340 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    // $ANTLR end "binaryOp"
    public static final BitSet FOLLOW_typedVariableList_in_constraintDef1342 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_constraintDef1344 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000820L});
    // $ANTLR end "multiOp"
    public static final BitSet FOLLOW_constraintDefBody_in_constraintDef1357 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_constraintDef1359 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "binaryComp"
    public static final BitSet FOLLOW_NAME_in_actionSymbol1393 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_constraintSymbol1402 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "assignOp"
    public static final BitSet FOLLOW_92_in_actionDefBody1417 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_actionDefBody1421 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "durationConstraint"
    public static final BitSet FOLLOW_69_in_actionDefBody1423 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000080000L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_actionDefBody1428 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000080000L});
    // $ANTLR end "simpleDurationConstraint"
    public static final BitSet FOLLOW_83_in_actionDefBody1438 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_actionDefBody1442 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "durOp"
    public static final BitSet FOLLOW_69_in_actionDefBody1444 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_effect_in_actionDefBody1449 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "durValue"
    public static final BitSet FOLLOW_75_in_constraintDefBody1484 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_constraintDefBody1488 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "daEffect"
    public static final BitSet FOLLOW_69_in_constraintDefBody1490 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_constraintDefBody1495 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "timedEffect"
    public static final BitSet FOLLOW_atomicTermFormula_in_goalDesc1533 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1538 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    // $ANTLR end "fAssignDA"
    public static final BitSet FOLLOW_108_in_goalDesc1540 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1542 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    // $ANTLR end "fExpDA"
    public static final BitSet FOLLOW_69_in_goalDesc1545 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1570 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    // $ANTLR end "problem"
    public static final BitSet FOLLOW_130_in_goalDesc1572 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1574 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    // $ANTLR end "problemDecl"
    public static final BitSet FOLLOW_69_in_goalDesc1577 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1602 = new BitSet(new long[]{0x0000000000000000L, 0x8000000000000000L});
    // $ANTLR end "problemDomain"
    public static final BitSet FOLLOW_127_in_goalDesc1604 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1606 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "objectDecl"
    public static final BitSet FOLLOW_69_in_goalDesc1608 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1632 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000002L});
    // $ANTLR end "init"
    public static final BitSet FOLLOW_129_in_goalDesc1634 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1637 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    // $ANTLR end "initEl"
    public static final BitSet FOLLOW_69_in_goalDesc1640 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1655 = new BitSet(new long[]{0x0000000000000000L, 0x0400000000000000L});
    // $ANTLR end "nameLiteral"
    public static final BitSet FOLLOW_122_in_goalDesc1657 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1659 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "atomicNameFormula"
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1661 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_goalDesc1663 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "goal"
    public static final BitSet FOLLOW_68_in_goalDesc1689 = new BitSet(new long[]{0x0000000000000000L, 0x0040000000000000L});

    ;
    public static final BitSet FOLLOW_118_in_goalDesc1691 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "probConstraints"
    public static final BitSet FOLLOW_68_in_goalDesc1693 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1695 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "prefConGD"
    public static final BitSet FOLLOW_69_in_goalDesc1697 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1699 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end "metricSpec"
    public static final BitSet FOLLOW_69_in_goalDesc1701 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1727 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    // $ANTLR end "optimization"
    public static final BitSet FOLLOW_119_in_goalDesc1729 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1731 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    // $ANTLR end "metricFExp"
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1733 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});

    ;
    public static final BitSet FOLLOW_69_in_goalDesc1735 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "conGD"
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1737 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end synpred18_Pddl
    public static final BitSet FOLLOW_69_in_goalDesc1739 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred50_Pddl
    public static final BitSet FOLLOW_fComp_in_goalDesc1768 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred53_Pddl
    public static final BitSet FOLLOW_equality_in_goalDesc1795 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred66_Pddl
    public static final BitSet FOLLOW_68_in_equality1823 = new BitSet(new long[]{0x0000000000000000L, 0x0000000800000000L});
    // $ANTLR end synpred67_Pddl
    public static final BitSet FOLLOW_99_in_equality1826 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000001L});
    // $ANTLR end synpred68_Pddl
    public static final BitSet FOLLOW_term_in_equality1828 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000001L});
    // $ANTLR end synpred69_Pddl
    public static final BitSet FOLLOW_term_in_equality1830 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end synpred70_Pddl
    public static final BitSet FOLLOW_69_in_equality1832 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred100_Pddl
    public static final BitSet FOLLOW_68_in_fComp1843 = new BitSet(new long[]{0x0000000000000000L, 0x0000003E00000000L});
    // $ANTLR end synpred102_Pddl
    public static final BitSet FOLLOW_binaryComp_in_fComp1846 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    // $ANTLR end synpred103_Pddl
    public static final BitSet FOLLOW_fExp_in_fComp1848 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    // $ANTLR end synpred104_Pddl
    public static final BitSet FOLLOW_fExp_in_fComp1850 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end synpred105_Pddl
    public static final BitSet FOLLOW_69_in_fComp1852 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred106_Pddl
    public static final BitSet FOLLOW_68_in_atomicTermFormula1864 = new BitSet(new long[]{0x0000010000000000L});
    // $ANTLR end synpred107_Pddl
    public static final BitSet FOLLOW_predicate_in_atomicTermFormula1866 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000021L});
    // $ANTLR end synpred108_Pddl
    public static final BitSet FOLLOW_term_in_atomicTermFormula1868 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000021L});
    // $ANTLR end synpred109_Pddl
    public static final BitSet FOLLOW_69_in_atomicTermFormula1871 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred116_Pddl
    public static final BitSet FOLLOW_68_in_durativeActionDef1908 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000040000L});
    // $ANTLR end synpred127_Pddl
    public static final BitSet FOLLOW_82_in_durativeActionDef1910 = new BitSet(new long[]{0x0000010000000000L});
    // $ANTLR end synpred128_Pddl
    public static final BitSet FOLLOW_actionSymbol_in_durativeActionDef1912 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008000000L});
    // $ANTLR end synpred130_Pddl
    public static final BitSet FOLLOW_91_in_durativeActionDef1921 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred132_Pddl
    public static final BitSet FOLLOW_68_in_durativeActionDef1924 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    // $ANTLR end synpred134_Pddl
    public static final BitSet FOLLOW_typedVariableList_in_durativeActionDef1927 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    // $ANTLR end synpred135_Pddl
    public static final BitSet FOLLOW_69_in_durativeActionDef1931 = new BitSet(new long[]{0x0000000000000000L, 0x00000000000A0800L});
    // $ANTLR end synpred137_Pddl
    public static final BitSet FOLLOW_daDefBody_in_durativeActionDef1944 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_durativeActionDef1946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_daDefBody1979 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_durationConstraint_in_daDefBody1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_daDefBody1986 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daDefBody1990 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daDefBody1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daGD_in_daDefBody1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_daDefBody2006 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daDefBody2010 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daDefBody2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daEffect_in_daDefBody2017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefTimedGD_in_daGD2032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daGD2037 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_daGD2039 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_daGD_in_daGD2041 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_daGD2044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daGD2049 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_daGD2051 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daGD2053 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_daGD2055 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daGD2057 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daGD_in_daGD2059 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daGD2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_prefTimedGD2077 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_132_in_prefTimedGD2079 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_NAME_in_prefTimedGD2081 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2084 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_prefTimedGD2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_timedGD2097 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_timedGD2099 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedGD2101 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2103 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_timedGD2105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_timedGD2110 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_timedGD2112 = new BitSet(new long[]{0x0000000000000000L, 0x0000020000000000L});
    public static final BitSet FOLLOW_interval_in_timedGD2114 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2116 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_timedGD2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_interval2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_derivedDef2153 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_derivedDef2156 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000011L});
    public static final BitSet FOLLOW_typedVariableList_in_derivedDef2159 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_derivedDef2161 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_derivedDef2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_fExp2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExp2183 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_fExp2185 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_fExp2187 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp2_in_fExp2189 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExp2191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExp2208 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_fExp2210 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_fExp2212 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExp2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExp2227 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_136_in_fExp2229 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_fExp2231 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExp2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExp2246 = new BitSet(new long[]{0x0000000000000000L, 0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_fExp2248 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_fExp2250 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExp2252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExp2265 = new BitSet(new long[]{0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_104_in_fExp2267 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_fExp2269 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExp2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_fExp2284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExp22296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fHead2306 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2308 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_term_in_fHead2310 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_69_in_fHead2313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_effect2348 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_effect2350 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_cEffect_in_effect2352 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_effect2355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cEffect_in_effect2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_cEffect2380 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_cEffect2382 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_cEffect2384 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_cEffect2386 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_cEffect2388 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_effect_in_cEffect2390 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_cEffect2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_cEffect2410 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000004000L});
    public static final BitSet FOLLOW_142_in_cEffect2412 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_cEffect2414 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_condEffect_in_cEffect2416 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_cEffect2418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_cEffect2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_pEffect2447 = new BitSet(new long[]{0x0000000000000000L, 0x0802200000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_assignOp_in_pEffect2449 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fHead_in_pEffect2451 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_pEffect2453 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_pEffect2455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_pEffect2475 = new BitSet(new long[]{0x0000000000000000L, 0x8000000000000000L});
    public static final BitSet FOLLOW_127_in_pEffect2477 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2479 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_pEffect2481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_condEffect2510 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_condEffect2512 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2514 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_condEffect2517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_durationConstraint2636 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_durationConstraint2638 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2640 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_durationConstraint2643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_durationConstraint2648 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_durationConstraint2650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_simpleDurationConstraint2666 = new BitSet(new long[]{0x0000000000000000L, 0x0000002C00000000L});
    public static final BitSet FOLLOW_durOp_in_simpleDurationConstraint2668 = new BitSet(new long[]{0x0000000000000000L, 0x0000004000000000L});
    public static final BitSet FOLLOW_102_in_simpleDurationConstraint2670 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_durValue_in_simpleDurationConstraint2672 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_simpleDurationConstraint2674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_simpleDurationConstraint2679 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_simpleDurationConstraint2681 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_simpleDurationConstraint2683 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2685 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_simpleDurationConstraint2687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_durValue2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_durValue2718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daEffect2728 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_daEffect2730 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2732 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_daEffect2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daEffect2745 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_daEffect2747 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daEffect2749 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_daEffect2751 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daEffect2753 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2755 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daEffect2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daEffect2762 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000004000L});
    public static final BitSet FOLLOW_142_in_daEffect2764 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daGD_in_daEffect2766 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2768 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daEffect2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_daEffect2775 = new BitSet(new long[]{0x0000000000000000L, 0x0802200000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_assignOp_in_daEffect2777 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fHead_in_daEffect2779 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_daEffect2781 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_daEffect2783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_timedEffect2794 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_timedEffect2796 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2798 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daEffect_in_timedEffect2800 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_timedEffect2802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_timedEffect2812 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_timedEffect2814 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2816 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_fAssignDA_in_timedEffect2818 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_timedEffect2820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_timedEffect2825 = new BitSet(new long[]{0x0000000000000000L, 0x0802200000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_assignOp_in_timedEffect2827 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fHead_in_timedEffect2829 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_timedEffect2831 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_timedEffect2833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fAssignDA2853 = new BitSet(new long[]{0x0000000000000000L, 0x0802200000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_assignOp_in_fAssignDA2855 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fHead_in_fAssignDA2857 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_fAssignDA2859 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fAssignDA2861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_fExpDA2872 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_fExpDA2876 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2878 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2880 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_72_in_fExpDA2886 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2888 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_fExpDA2892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_fExpDA2897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExpDA2902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_problem2916 = new BitSet(new long[]{0x0000000000000000L, 0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_problem2918 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_problemDecl_in_problem2920 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_problemDomain_in_problem2925 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_requireDef_in_problem2933 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_objectDecl_in_problem2942 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_init_in_problem2951 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goal_in_problem2959 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_probConstraints_in_problem2967 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_metricSpec_in_problem2976 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_problem2992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_problemDecl3049 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_133_in_problemDecl3051 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDecl3053 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_problemDecl3055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_problemDomain3081 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_problemDomain3083 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDomain3085 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_problemDomain3087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_objectDecl3107 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_objectDecl3109 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_typedNameList_in_objectDecl3111 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_objectDecl3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_init3133 = new BitSet(new long[]{0x0000000000000000L, 0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_init3135 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_initEl_in_init3137 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_init3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_init3155 = new BitSet(new long[]{0x0000000000000000L, 0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_init3157 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_init3159 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_init3161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_initEl3185 = new BitSet(new long[]{0x0000000000000000L, 0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_initEl3187 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fHead_in_initEl3189 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3191 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_initEl3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_initEl3216 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_initEl3218 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3220 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3222 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_initEl3224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_initEl3240 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000002000L});
    public static final BitSet FOLLOW_141_in_initEl3242 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3245 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_initEl3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_initEl3261 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_initEl3263 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3266 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_initEl3269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_initEl3284 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_130_in_initEl3286 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3289 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_initEl3292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_nameLiteral3318 = new BitSet(new long[]{0x0000000000000000L, 0x8000000000000000L});
    public static final BitSet FOLLOW_127_in_nameLiteral3320 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3322 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_nameLiteral3324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_atomicNameFormula3343 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_predicate_in_atomicNameFormula3345 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_NAME_in_atomicNameFormula3347 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_atomicNameFormula3350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_goal3375 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_goal3377 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_goal3379 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_goal3382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_probConstraints3400 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_probConstraints3402 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_prefConGD_in_probConstraints3405 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_probConstraints3407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_prefConGD3429 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_prefConGD3431 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3433 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_prefConGD3436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_prefConGD3441 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_prefConGD3443 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_prefConGD3445 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_prefConGD3447 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_prefConGD3449 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3451 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_prefConGD3453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_prefConGD3458 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_132_in_prefConGD3460 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_NAME_in_prefConGD3462 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3465 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_prefConGD3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_metricSpec3483 = new BitSet(new long[]{0x0000000000000000L, 0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_metricSpec3485 = new BitSet(new long[]{0x0000000000000000L, 0x6000000000000000L});
    public static final BitSet FOLLOW_optimization_in_metricSpec3487 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricSpec3489 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_metricSpec3491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_metricFExp3528 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_metricFExp3530 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3532 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3534 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_metricFExp3536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_metricFExp3558 = new BitSet(new long[]{0x0000000000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_multiOp_in_metricFExp3560 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3562 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3564 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000038L});
    public static final BitSet FOLLOW_69_in_metricFExp3567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_metricFExp3591 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_metricFExp3593 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3595 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_metricFExp3597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_metricFExp3614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_metricFExp3619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_metricFExp3634 = new BitSet(new long[]{0x0000000000000000L, 0x1000000000000000L});
    public static final BitSet FOLLOW_124_in_metricFExp3636 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_NAME_in_metricFExp3638 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_metricFExp3640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3654 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_conGD3656 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_conGD_in_conGD3658 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_conGD3661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3666 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_conGD3668 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3670 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_conGD3672 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3674 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_conGD_in_conGD3676 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3683 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_conGD3685 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_conGD3687 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3689 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3717 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_conGD3719 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3721 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3728 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000200L});
    public static final BitSet FOLLOW_137_in_conGD3730 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3732 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3740 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000008000L});
    public static final BitSet FOLLOW_143_in_conGD3742 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3744 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3746 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3753 = new BitSet(new long[]{0x0000000000000000L, 0x0000800000000000L});
    public static final BitSet FOLLOW_111_in_conGD3755 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3757 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3764 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_138_in_conGD3766 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3768 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3770 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3777 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000800L});
    public static final BitSet FOLLOW_139_in_conGD3779 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3781 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3783 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3790 = new BitSet(new long[]{0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_107_in_conGD3792 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3794 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3796 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3798 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3805 = new BitSet(new long[]{0x0000000000000000L, 0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_conGD3807 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3809 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3811 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3813 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_conGD3820 = new BitSet(new long[]{0x0000000000000000L, 0x0100000000000000L});
    public static final BitSet FOLLOW_120_in_conGD3822 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3824 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3826 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_conGD3828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fComp_in_synpred50_Pddl1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred53_Pddl1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred66_Pddl2183 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred66_Pddl2185 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_synpred66_Pddl2187 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp2_in_synpred66_Pddl2189 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred66_Pddl2191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred67_Pddl2208 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_synpred67_Pddl2210 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_synpred67_Pddl2212 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred67_Pddl2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred68_Pddl2227 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_136_in_synpred68_Pddl2229 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_synpred68_Pddl2231 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred68_Pddl2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred69_Pddl2246 = new BitSet(new long[]{0x0000000000000000L, 0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_synpred69_Pddl2248 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_synpred69_Pddl2250 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred69_Pddl2252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred70_Pddl2265 = new BitSet(new long[]{0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_104_in_synpred70_Pddl2267 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_fExp_in_synpred70_Pddl2269 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred70_Pddl2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_synpred100_Pddl2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred102_Pddl2728 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_synpred102_Pddl2730 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_daEffect_in_synpred102_Pddl2732 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_synpred102_Pddl2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_synpred103_Pddl2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred104_Pddl2745 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_synpred104_Pddl2747 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred104_Pddl2749 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred104_Pddl2751 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred104_Pddl2753 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daEffect_in_synpred104_Pddl2755 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred104_Pddl2757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred105_Pddl2762 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000004000L});
    public static final BitSet FOLLOW_142_in_synpred105_Pddl2764 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daGD_in_synpred105_Pddl2766 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_timedEffect_in_synpred105_Pddl2768 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred105_Pddl2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred106_Pddl2794 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_synpred106_Pddl2796 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred106_Pddl2798 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_daEffect_in_synpred106_Pddl2800 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred106_Pddl2802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred107_Pddl2812 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_synpred107_Pddl2814 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred107_Pddl2816 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_fAssignDA_in_synpred107_Pddl2818 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred107_Pddl2820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binaryOp_in_synpred108_Pddl2876 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_synpred108_Pddl2878 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_synpred108_Pddl2880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred109_Pddl2872 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred109_Pddl2876 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_synpred109_Pddl2878 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_synpred109_Pddl2880 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_72_in_synpred109_Pddl2886 = new BitSet(new long[]{0x0000110000000000L, 0x0000004000000018L});
    public static final BitSet FOLLOW_fExpDA_in_synpred109_Pddl2888 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred109_Pddl2892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred116_Pddl3133 = new BitSet(new long[]{0x0000000000000000L, 0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_synpred116_Pddl3135 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_initEl_in_synpred116_Pddl3137 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_synpred116_Pddl3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred127_Pddl3429 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_synpred127_Pddl3431 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_prefConGD_in_synpred127_Pddl3433 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_69_in_synpred127_Pddl3436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred128_Pddl3441 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_synpred128_Pddl3443 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred128_Pddl3445 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000021L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred128_Pddl3447 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred128_Pddl3449 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_prefConGD_in_synpred128_Pddl3451 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred128_Pddl3453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred130_Pddl3458 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_132_in_synpred130_Pddl3460 = new BitSet(new long[]{0x0000010000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_NAME_in_synpred130_Pddl3462 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_conGD_in_synpred130_Pddl3465 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred130_Pddl3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred132_Pddl3528 = new BitSet(new long[]{0x0000000000000000L, 0x00000080000003C0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred132_Pddl3530 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_synpred132_Pddl3532 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_synpred132_Pddl3534 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred132_Pddl3536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred134_Pddl3558 = new BitSet(new long[]{0x0000000000000000L, 0x00000000000000C0L});
    public static final BitSet FOLLOW_multiOp_in_synpred134_Pddl3560 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_synpred134_Pddl3562 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_synpred134_Pddl3564 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000038L});
    public static final BitSet FOLLOW_69_in_synpred134_Pddl3567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred135_Pddl3591 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_synpred135_Pddl3593 = new BitSet(new long[]{0x0000110000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricFExp_in_synpred135_Pddl3595 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_synpred135_Pddl3597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_synpred137_Pddl3619 = new BitSet(new long[]{0x0000000000000002L});
    static final String DFA14_eotS
            = "\4\uffff";
    static final String DFA14_eofS
            = "\4\uffff";
    static final String DFA14_minS
            = "\2\50\2\uffff";
    static final String DFA14_maxS
            = "\1\105\1\110\2\uffff";
    static final String DFA14_acceptS
            = "\2\uffff\1\1\1\2";
    static final String DFA14_specialS
            = "\4\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1\34\uffff\1\2",
            "\1\1\34\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };
    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;
    static final String DFA12_eotS
            = "\4\uffff";
    static final String DFA12_eofS
            = "\4\uffff";
    static final String DFA12_minS
            = "\2\50\2\uffff";
    static final String DFA12_maxS
            = "\1\105\1\110\2\uffff";
    static final String DFA12_acceptS
            = "\2\uffff\1\2\1\1";
    static final String DFA12_specialS
            = "\4\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\34\uffff\1\2",
            "\1\1\34\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };
    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;
    static final String DFA25_eotS
            = "\4\uffff";
    static final String DFA25_eofS
            = "\2\2\2\uffff";
    static final String DFA25_minS
            = "\2\100\2\uffff";
    static final String DFA25_maxS
            = "\1\105\1\110\2\uffff";
    static final String DFA25_acceptS
            = "\2\uffff\1\1\1\2";
    static final String DFA25_specialS
            = "\4\uffff}>";
    static final String[] DFA25_transitionS = {
            "\1\1\3\uffff\2\2",
            "\1\1\3\uffff\2\2\2\uffff\1\3",
            "",
            ""
    };
    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;
    static final String DFA23_eotS
            = "\4\uffff";
    static final String DFA23_eofS
            = "\2\2\2\uffff";
    static final String DFA23_minS
            = "\2\100\2\uffff";
    static final String DFA23_maxS
            = "\1\105\1\110\2\uffff";
    static final String DFA23_acceptS
            = "\2\uffff\1\2\1\1";
    static final String DFA23_specialS
            = "\4\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\1\3\uffff\2\2",
            "\1\1\3\uffff\2\2\2\uffff\1\3",
            "",
            ""
    };
    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;
    static final String DFA71_eotS
            = "\57\uffff";
    static final String DFA71_eofS
            = "\57\uffff";
    static final String DFA71_minS
            = "\1\104\1\130\1\104\1\50\1\uffff\1\104\1\50\2\104\1\uffff\6\50\1\104\1"
            + "\50\1\104\1\50\1\104\2\50\1\105\1\50\1\0\1\104\1\50\2\0\1\50\1\105\2\50"
            + "\1\104\1\50\1\104\2\50\2\104\1\0\1\50\1\0\1\50\1\105\1\104";
    static final String DFA71_maxS
            = "\1\104\1\130\1\105\1\u008d\1\uffff\2\104\2\105\1\uffff\1\105\1\u0082\1"
            + "\u0088\2\104\1\u0082\1\105\1\u0082\7\105\1\0\1\104\1\105\2\0\3\105\1\104"
            + "\3\105\1\u0082\3\105\1\0\1\105\1\0\3\105";
    static final String DFA71_acceptS
            = "\4\uffff\1\1\4\uffff\1\2\45\uffff";
    static final String DFA71_specialS
            = "\31\uffff\1\0\2\uffff\1\1\1\2\13\uffff\1\3\1\uffff\1\4\3\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4",
            "\1\12\70\uffff\2\11\1\6\2\11\6\uffff\1\11\1\uffff\1\4\7\uffff\2\11\2"
                    + "\uffff\1\11\4\uffff\1\5\1\uffff\1\7\1\10\12\uffff\1\4",
            "",
            "\1\13",
            "\1\15\3\uffff\1\11\23\uffff\1\11\2\uffff\1\16\1\14",
            "\1\17\1\20",
            "\1\21\1\22",
            "",
            "\1\23\27\uffff\1\11\4\uffff\1\24",
            "\1\25\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"
                    + "\1\11\1\uffff\2\11",
            "\1\26\32\uffff\1\26\2\uffff\4\11\35\uffff\2\11\7\uffff\1\11\27\uffff"
                    + "\1\11",
            "\1\11\3\uffff\1\27\23\uffff\1\11\2\uffff\2\11",
            "\1\11\3\uffff\1\27\26\uffff\2\11",
            "\1\30\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"
                    + "\1\11\1\uffff\2\11",
            "\1\4\1\31",
            "\1\33\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"
                    + "\1\32\1\uffff\2\11",
            "\1\4\1\34",
            "\1\23\27\uffff\1\11\4\uffff\1\24",
            "\1\4\1\35",
            "\1\36\27\uffff\1\11\4\uffff\1\37",
            "\1\40\27\uffff\1\40\4\uffff\1\41",
            "\1\42",
            "\1\43\27\uffff\1\11\4\uffff\1\44",
            "\1\uffff",
            "\1\45",
            "\1\46\27\uffff\1\11\4\uffff\1\47",
            "\1\uffff",
            "\1\uffff",
            "\1\36\27\uffff\1\11\4\uffff\1\37",
            "\1\50",
            "\1\40\27\uffff\1\40\4\uffff\1\41",
            "\1\11\3\uffff\1\27\26\uffff\2\11",
            "\1\4\1\51",
            "\1\43\27\uffff\1\11\4\uffff\1\44",
            "\1\17\1\20",
            "\1\52\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"
                    + "\1\11\1\uffff\2\11",
            "\1\46\27\uffff\1\11\4\uffff\1\47",
            "\1\21\1\22",
            "\1\4\1\53",
            "\1\uffff",
            "\1\54\27\uffff\1\11\4\uffff\1\55",
            "\1\uffff",
            "\1\54\27\uffff\1\11\4\uffff\1\55",
            "\1\56",
            "\1\21\1\22"
    };
    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA25 dfa25 = new DFA25(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA71 dfa71 = new DFA71(this);
    private boolean wasError = false;
    // delegators
    public PddlParser (TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PddlParser (TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[227 + 1];

    }

    // delegates
    public Parser[] getDelegates ( ) {
        return new Parser[]{};
    }

    public TreeAdaptor getTreeAdaptor ( ) {
        return adaptor;
    }

    public void setTreeAdaptor (TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    public String[] getTokenNames ( ) {
        return PddlParser.tokenNames;
    }

    @Override
    public String getGrammarFileName ( ) {
        return "Pddl.g";
    }

    public void reportError (RecognitionException e) {
        wasError = true;
        super.reportError(e);
    }

    public boolean invalidGrammar ( ) {
        return wasError;
    }

    // $ANTLR start "pddlDoc"
    // Pddl.g:104:1: pddlDoc : ( domain | problem );
    public final PddlParser.pddlDoc_return pddlDoc ( ) throws RecognitionException {
        PddlParser.pddlDoc_return retval = new PddlParser.pddlDoc_return();
        retval.start = input.LT(1);
        int pddlDoc_StartIndex = input.index();

        Object root_0 = null;

        ParserRuleReturnScope domain1 = null;
        ParserRuleReturnScope problem2 = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 1)) {
                return retval;
            }

            // Pddl.g:104:9: ( domain | problem )
            int alt1 = 2;
            int LA1_0 = input.LA(1);
            if ((LA1_0 == 68)) {
                int LA1_1 = input.LA(2);
                if ((LA1_1 == 114)) {
                    int LA1_2 = input.LA(3);
                    if ((LA1_2 == 68)) {
                        int LA1_3 = input.LA(4);
                        if ((LA1_3 == 115)) {
                            alt1 = 1;
                        } else if ((LA1_3 == 133)) {
                            alt1 = 2;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            int nvaeMark = input.mark();
                            try {
                                for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
                                    input.consume();
                                }
                                NoViableAltException nvae
                                        = new NoViableAltException("", 1, 3, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                input.consume();
                            }
                            NoViableAltException nvae
                                    = new NoViableAltException("", 1, 2, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                    }

                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
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
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 1, 0, input);
                throw nvae;
            }

            switch (alt1) {
                case 1: // Pddl.g:104:11: domain
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_domain_in_pddlDoc394);
                    domain1 = domain();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, domain1.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:104:20: problem
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_problem_in_pddlDoc398);
                    problem2 = problem();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, problem2.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 1, pddlDoc_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "domain"
    // Pddl.g:108:1: domain : '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) ;
    public final PddlParser.domain_return domain ( ) throws RecognitionException {
        PddlParser.domain_return retval = new PddlParser.domain_return();
        retval.start = input.LT(1);
        int domain_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal3 = null;
        Token string_literal4 = null;
        Token char_literal14 = null;
        ParserRuleReturnScope domainName5 = null;
        ParserRuleReturnScope requireDef6 = null;
        ParserRuleReturnScope typesDef7 = null;
        ParserRuleReturnScope constantsDef8 = null;
        ParserRuleReturnScope predicatesDef9 = null;
        ParserRuleReturnScope functionsDef10 = null;
        ParserRuleReturnScope free_functionsDef11 = null;
        ParserRuleReturnScope constraints12 = null;
        ParserRuleReturnScope structureDef13 = null;

        Object char_literal3_tree = null;
        Object string_literal4_tree = null;
        Object char_literal14_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_114 = new RewriteRuleTokenStream(adaptor, "token 114");
        RewriteRuleSubtreeStream stream_typesDef = new RewriteRuleSubtreeStream(adaptor, "rule typesDef");
        RewriteRuleSubtreeStream stream_requireDef = new RewriteRuleSubtreeStream(adaptor, "rule requireDef");
        RewriteRuleSubtreeStream stream_free_functionsDef = new RewriteRuleSubtreeStream(adaptor, "rule free_functionsDef");
        RewriteRuleSubtreeStream stream_domainName = new RewriteRuleSubtreeStream(adaptor, "rule domainName");
        RewriteRuleSubtreeStream stream_predicatesDef = new RewriteRuleSubtreeStream(adaptor, "rule predicatesDef");
        RewriteRuleSubtreeStream stream_constantsDef = new RewriteRuleSubtreeStream(adaptor, "rule constantsDef");
        RewriteRuleSubtreeStream stream_functionsDef = new RewriteRuleSubtreeStream(adaptor, "rule functionsDef");
        RewriteRuleSubtreeStream stream_constraints = new RewriteRuleSubtreeStream(adaptor, "rule constraints");
        RewriteRuleSubtreeStream stream_structureDef = new RewriteRuleSubtreeStream(adaptor, "rule structureDef");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 2)) {
                return retval;
            }

            // Pddl.g:109:5: ( '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) )
            // Pddl.g:109:7: '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')'
            {
                char_literal3 = (Token) match(input, 68, FOLLOW_68_in_domain413);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal3);
                }

                string_literal4 = (Token) match(input, 114, FOLLOW_114_in_domain415);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_114.add(string_literal4);
                }

                pushFollow(FOLLOW_domainName_in_domain417);
                domainName5 = domainName();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_domainName.add(domainName5.getTree());
                }
                // Pddl.g:110:7: ( requireDef )?
                int alt2 = 2;
                int LA2_0 = input.LA(1);
                if ((LA2_0 == 68)) {
                    int LA2_1 = input.LA(2);
                    if ((LA2_1 == 95)) {
                        alt2 = 1;
                    }
                }
                switch (alt2) {
                    case 1: // Pddl.g:110:7: requireDef
                    {
                        pushFollow(FOLLOW_requireDef_in_domain425);
                        requireDef6 = requireDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_requireDef.add(requireDef6.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:111:7: ( typesDef )?
                int alt3 = 2;
                int LA3_0 = input.LA(1);
                if ((LA3_0 == 68)) {
                    int LA3_1 = input.LA(2);
                    if ((LA3_1 == 96)) {
                        alt3 = 1;
                    }
                }
                switch (alt3) {
                    case 1: // Pddl.g:111:7: typesDef
                    {
                        pushFollow(FOLLOW_typesDef_in_domain434);
                        typesDef7 = typesDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_typesDef.add(typesDef7.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:112:7: ( constantsDef )?
                int alt4 = 2;
                int LA4_0 = input.LA(1);
                if ((LA4_0 == 68)) {
                    int LA4_1 = input.LA(2);
                    if ((LA4_1 == 76)) {
                        alt4 = 1;
                    }
                }
                switch (alt4) {
                    case 1: // Pddl.g:112:7: constantsDef
                    {
                        pushFollow(FOLLOW_constantsDef_in_domain443);
                        constantsDef8 = constantsDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_constantsDef.add(constantsDef8.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:113:7: ( predicatesDef )?
                int alt5 = 2;
                int LA5_0 = input.LA(1);
                if ((LA5_0 == 68)) {
                    int LA5_1 = input.LA(2);
                    if ((LA5_1 == 93)) {
                        alt5 = 1;
                    }
                }
                switch (alt5) {
                    case 1: // Pddl.g:113:7: predicatesDef
                    {
                        pushFollow(FOLLOW_predicatesDef_in_domain452);
                        predicatesDef9 = predicatesDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_predicatesDef.add(predicatesDef9.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:114:7: ( functionsDef )?
                int alt6 = 2;
                int LA6_0 = input.LA(1);
                if ((LA6_0 == 68)) {
                    int LA6_1 = input.LA(2);
                    if ((LA6_1 == 86)) {
                        alt6 = 1;
                    }
                }
                switch (alt6) {
                    case 1: // Pddl.g:114:7: functionsDef
                    {
                        pushFollow(FOLLOW_functionsDef_in_domain461);
                        functionsDef10 = functionsDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_functionsDef.add(functionsDef10.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:115:7: ( free_functionsDef )?
                int alt7 = 2;
                int LA7_0 = input.LA(1);
                if ((LA7_0 == 68)) {
                    int LA7_1 = input.LA(2);
                    if ((LA7_1 == 85)) {
                        alt7 = 1;
                    }
                }
                switch (alt7) {
                    case 1: // Pddl.g:115:7: free_functionsDef
                    {
                        pushFollow(FOLLOW_free_functionsDef_in_domain470);
                        free_functionsDef11 = free_functionsDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_free_functionsDef.add(free_functionsDef11.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:116:7: ( constraints )?
                int alt8 = 2;
                int LA8_0 = input.LA(1);
                if ((LA8_0 == 68)) {
                    int LA8_1 = input.LA(2);
                    if ((LA8_1 == 78)) {
                        alt8 = 1;
                    }
                }
                switch (alt8) {
                    case 1: // Pddl.g:116:7: constraints
                    {
                        pushFollow(FOLLOW_constraints_in_domain479);
                        constraints12 = constraints();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_constraints.add(constraints12.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:117:7: ( structureDef )*
                loop9:
                while (true) {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);
                    if ((LA9_0 == 68)) {
                        alt9 = 1;
                    }

                    switch (alt9) {
                        case 1: // Pddl.g:117:7: structureDef
                        {
                            pushFollow(FOLLOW_structureDef_in_domain488);
                            structureDef13 = structureDef();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_structureDef.add(structureDef13.getTree());
                            }
                        }
                        break;

                        default:
                            break loop9;
                    }
                }

                char_literal14 = (Token) match(input, 69, FOLLOW_69_in_domain497);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal14);
                }

                // AST REWRITE
                // elements: functionsDef, free_functionsDef, structureDef, requireDef, predicatesDef, domainName, constantsDef, constraints, typesDef
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 119:7: -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
                    {
                        // Pddl.g:119:10: ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DOMAIN, "DOMAIN"), root_1);
                            adaptor.addChild(root_1, stream_domainName.nextTree());
                            // Pddl.g:119:30: ( requireDef )?
                            if (stream_requireDef.hasNext()) {
                                adaptor.addChild(root_1, stream_requireDef.nextTree());
                            }
                            stream_requireDef.reset();

                            // Pddl.g:119:42: ( typesDef )?
                            if (stream_typesDef.hasNext()) {
                                adaptor.addChild(root_1, stream_typesDef.nextTree());
                            }
                            stream_typesDef.reset();

                            // Pddl.g:120:17: ( constantsDef )?
                            if (stream_constantsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_constantsDef.nextTree());
                            }
                            stream_constantsDef.reset();

                            // Pddl.g:120:31: ( predicatesDef )?
                            if (stream_predicatesDef.hasNext()) {
                                adaptor.addChild(root_1, stream_predicatesDef.nextTree());
                            }
                            stream_predicatesDef.reset();

                            // Pddl.g:120:46: ( functionsDef )?
                            if (stream_functionsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_functionsDef.nextTree());
                            }
                            stream_functionsDef.reset();

                            // Pddl.g:120:60: ( free_functionsDef )?
                            if (stream_free_functionsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_free_functionsDef.nextTree());
                            }
                            stream_free_functionsDef.reset();

                            // Pddl.g:121:17: ( constraints )?
                            if (stream_constraints.hasNext()) {
                                adaptor.addChild(root_1, stream_constraints.nextTree());
                            }
                            stream_constraints.reset();

                            // Pddl.g:121:30: ( structureDef )*
                            while (stream_structureDef.hasNext()) {
                                adaptor.addChild(root_1, stream_structureDef.nextTree());
                            }
                            stream_structureDef.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 2, domain_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "free_functionsDef"
    // Pddl.g:124:1: free_functionsDef : '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) ;
    public final PddlParser.free_functionsDef_return free_functionsDef ( ) throws RecognitionException {
        PddlParser.free_functionsDef_return retval = new PddlParser.free_functionsDef_return();
        retval.start = input.LT(1);
        int free_functionsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal15 = null;
        Token string_literal16 = null;
        Token char_literal18 = null;
        ParserRuleReturnScope functionList17 = null;

        Object char_literal15_tree = null;
        Object string_literal16_tree = null;
        Object char_literal18_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_85 = new RewriteRuleTokenStream(adaptor, "token 85");
        RewriteRuleSubtreeStream stream_functionList = new RewriteRuleSubtreeStream(adaptor, "rule functionList");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 3)) {
                return retval;
            }

            // Pddl.g:125:2: ( '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) )
            // Pddl.g:125:4: '(' ':free_functions' functionList ')'
            {
                char_literal15 = (Token) match(input, 68, FOLLOW_68_in_free_functionsDef581);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal15);
                }

                string_literal16 = (Token) match(input, 85, FOLLOW_85_in_free_functionsDef583);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_85.add(string_literal16);
                }

                pushFollow(FOLLOW_functionList_in_free_functionsDef585);
                functionList17 = functionList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_functionList.add(functionList17.getTree());
                }
                char_literal18 = (Token) match(input, 69, FOLLOW_69_in_free_functionsDef587);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal18);
                }

                // AST REWRITE
                // elements: functionList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 126:2: -> ^( FREE_FUNCTIONS functionList )
                    {
                        // Pddl.g:126:5: ^( FREE_FUNCTIONS functionList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FREE_FUNCTIONS, "FREE_FUNCTIONS"), root_1);
                            adaptor.addChild(root_1, stream_functionList.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 3, free_functionsDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "domainName"
    // Pddl.g:129:1: domainName : '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) ;
    public final PddlParser.domainName_return domainName ( ) throws RecognitionException {
        PddlParser.domainName_return retval = new PddlParser.domainName_return();
        retval.start = input.LT(1);
        int domainName_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal19 = null;
        Token string_literal20 = null;
        Token NAME21 = null;
        Token char_literal22 = null;

        Object char_literal19_tree = null;
        Object string_literal20_tree = null;
        Object NAME21_tree = null;
        Object char_literal22_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_115 = new RewriteRuleTokenStream(adaptor, "token 115");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 4)) {
                return retval;
            }

            // Pddl.g:130:5: ( '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) )
            // Pddl.g:130:7: '(' 'domain' NAME ')'
            {
                char_literal19 = (Token) match(input, 68, FOLLOW_68_in_domainName610);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal19);
                }

                string_literal20 = (Token) match(input, 115, FOLLOW_115_in_domainName612);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_115.add(string_literal20);
                }

                NAME21 = (Token) match(input, NAME, FOLLOW_NAME_in_domainName614);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME21);
                }

                char_literal22 = (Token) match(input, 69, FOLLOW_69_in_domainName616);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal22);
                }

                // AST REWRITE
                // elements: NAME
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 131:6: -> ^( DOMAIN_NAME NAME )
                    {
                        // Pddl.g:131:9: ^( DOMAIN_NAME NAME )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DOMAIN_NAME, "DOMAIN_NAME"), root_1);
                            adaptor.addChild(root_1, stream_NAME.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 4, domainName_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "requireDef"
    // Pddl.g:134:1: requireDef : '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) ;
    public final PddlParser.requireDef_return requireDef ( ) throws RecognitionException {
        PddlParser.requireDef_return retval = new PddlParser.requireDef_return();
        retval.start = input.LT(1);
        int requireDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal23 = null;
        Token string_literal24 = null;
        Token REQUIRE_KEY25 = null;
        Token char_literal26 = null;

        Object char_literal23_tree = null;
        Object string_literal24_tree = null;
        Object REQUIRE_KEY25_tree = null;
        Object char_literal26_tree = null;
        RewriteRuleTokenStream stream_REQUIRE_KEY = new RewriteRuleTokenStream(adaptor, "token REQUIRE_KEY");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_95 = new RewriteRuleTokenStream(adaptor, "token 95");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 5)) {
                return retval;
            }

            // Pddl.g:135:2: ( '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) )
            // Pddl.g:135:4: '(' ':requirements' ( REQUIRE_KEY )+ ')'
            {
                char_literal23 = (Token) match(input, 68, FOLLOW_68_in_requireDef643);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal23);
                }

                string_literal24 = (Token) match(input, 95, FOLLOW_95_in_requireDef645);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_95.add(string_literal24);
                }

                // Pddl.g:135:24: ( REQUIRE_KEY )+
                int cnt10 = 0;
                loop10:
                while (true) {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);
                    if ((LA10_0 == REQUIRE_KEY)) {
                        alt10 = 1;
                    }

                    switch (alt10) {
                        case 1: // Pddl.g:135:24: REQUIRE_KEY
                        {
                            REQUIRE_KEY25 = (Token) match(input, REQUIRE_KEY, FOLLOW_REQUIRE_KEY_in_requireDef647);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_REQUIRE_KEY.add(REQUIRE_KEY25);
                            }

                        }
                        break;

                        default:
                            if (cnt10 >= 1) {
                                break loop10;
                            }
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            EarlyExitException eee = new EarlyExitException(10, input);
                            throw eee;
                    }
                    cnt10++;
                }

                char_literal26 = (Token) match(input, 69, FOLLOW_69_in_requireDef650);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal26);
                }

                // AST REWRITE
                // elements: REQUIRE_KEY
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 136:2: -> ^( REQUIREMENTS ( REQUIRE_KEY )+ )
                    {
                        // Pddl.g:136:5: ^( REQUIREMENTS ( REQUIRE_KEY )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(REQUIREMENTS, "REQUIREMENTS"), root_1);
                            if (!(stream_REQUIRE_KEY.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_REQUIRE_KEY.hasNext()) {
                                adaptor.addChild(root_1, stream_REQUIRE_KEY.nextNode());
                            }
                            stream_REQUIRE_KEY.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 5, requireDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "typesDef"
    // Pddl.g:139:1: typesDef : '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) ;
    public final PddlParser.typesDef_return typesDef ( ) throws RecognitionException {
        PddlParser.typesDef_return retval = new PddlParser.typesDef_return();
        retval.start = input.LT(1);
        int typesDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal27 = null;
        Token string_literal28 = null;
        Token char_literal30 = null;
        ParserRuleReturnScope typedNameList29 = null;

        Object char_literal27_tree = null;
        Object string_literal28_tree = null;
        Object char_literal30_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_96 = new RewriteRuleTokenStream(adaptor, "token 96");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 6)) {
                return retval;
            }

            // Pddl.g:140:2: ( '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) )
            // Pddl.g:140:4: '(' ':types' typedNameList ')'
            {
                char_literal27 = (Token) match(input, 68, FOLLOW_68_in_typesDef671);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal27);
                }

                string_literal28 = (Token) match(input, 96, FOLLOW_96_in_typesDef673);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_96.add(string_literal28);
                }

                pushFollow(FOLLOW_typedNameList_in_typesDef675);
                typedNameList29 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList29.getTree());
                }
                char_literal30 = (Token) match(input, 69, FOLLOW_69_in_typesDef677);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal30);
                }

                // AST REWRITE
                // elements: typedNameList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 141:4: -> ^( TYPES typedNameList )
                    {
                        // Pddl.g:141:7: ^( TYPES typedNameList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(TYPES, "TYPES"), root_1);
                            adaptor.addChild(root_1, stream_typedNameList.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 6, typesDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "typedNameList"
    // Pddl.g:145:1: typedNameList : ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) ;
    public final PddlParser.typedNameList_return typedNameList ( ) throws RecognitionException {
        PddlParser.typedNameList_return retval = new PddlParser.typedNameList_return();
        retval.start = input.LT(1);
        int typedNameList_StartIndex = input.index();

        Object root_0 = null;

        Token NAME31 = null;
        Token NAME33 = null;
        ParserRuleReturnScope singleTypeNameList32 = null;

        Object NAME31_tree = null;
        Object NAME33_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 7)) {
                return retval;
            }

            // Pddl.g:146:5: ( ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) )
            // Pddl.g:146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
                int alt14 = 2;
                alt14 = dfa14.predict(input);
                switch (alt14) {
                    case 1: // Pddl.g:146:8: ( NAME )*
                    {
                        // Pddl.g:146:8: ( NAME )*
                        loop11:
                        while (true) {
                            int alt11 = 2;
                            int LA11_0 = input.LA(1);
                            if ((LA11_0 == NAME)) {
                                alt11 = 1;
                            }

                            switch (alt11) {
                                case 1: // Pddl.g:146:8: NAME
                                {
                                    NAME31 = (Token) match(input, NAME, FOLLOW_NAME_in_typedNameList704);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        NAME31_tree = (Object) adaptor.create(NAME31);
                                        adaptor.addChild(root_0, NAME31_tree);
                                    }

                                }
                                break;

                                default:
                                    break loop11;
                            }
                        }

                    }
                    break;
                    case 2: // Pddl.g:146:16: ( singleTypeNameList )+ ( NAME )*
                    {
                        // Pddl.g:146:16: ( singleTypeNameList )+
                        int cnt12 = 0;
                        loop12:
                        while (true) {
                            int alt12 = 2;
                            alt12 = dfa12.predict(input);
                            switch (alt12) {
                                case 1: // Pddl.g:146:16: singleTypeNameList
                                {
                                    pushFollow(FOLLOW_singleTypeNameList_in_typedNameList709);
                                    singleTypeNameList32 = singleTypeNameList();
                                    state._fsp--;
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        adaptor.addChild(root_0, singleTypeNameList32.getTree());
                                    }

                                }
                                break;

                                default:
                                    if (cnt12 >= 1) {
                                        break loop12;
                                    }
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return retval;
                                    }
                                    EarlyExitException eee = new EarlyExitException(12, input);
                                    throw eee;
                            }
                            cnt12++;
                        }

                        // Pddl.g:146:36: ( NAME )*
                        loop13:
                        while (true) {
                            int alt13 = 2;
                            int LA13_0 = input.LA(1);
                            if ((LA13_0 == NAME)) {
                                alt13 = 1;
                            }

                            switch (alt13) {
                                case 1: // Pddl.g:146:36: NAME
                                {
                                    NAME33 = (Token) match(input, NAME, FOLLOW_NAME_in_typedNameList712);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        NAME33_tree = (Object) adaptor.create(NAME33);
                                        adaptor.addChild(root_0, NAME33_tree);
                                    }

                                }
                                break;

                                default:
                                    break loop13;
                            }
                        }

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 7, typedNameList_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "singleTypeNameList"
    // Pddl.g:149:1: singleTypeNameList : ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ ;
    public final PddlParser.singleTypeNameList_return singleTypeNameList ( ) throws RecognitionException {
        PddlParser.singleTypeNameList_return retval = new PddlParser.singleTypeNameList_return();
        retval.start = input.LT(1);
        int singleTypeNameList_StartIndex = input.index();

        Object root_0 = null;

        Token NAME34 = null;
        Token char_literal35 = null;
        ParserRuleReturnScope t = null;

        Object NAME34_tree = null;
        Object char_literal35_tree = null;
        RewriteRuleTokenStream stream_72 = new RewriteRuleTokenStream(adaptor, "token 72");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");
        RewriteRuleSubtreeStream stream_type = new RewriteRuleSubtreeStream(adaptor, "rule type");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 8)) {
                return retval;
            }

            // Pddl.g:150:5: ( ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ )
            // Pddl.g:150:7: ( ( NAME )+ '-' t= type )
            {
                // Pddl.g:150:7: ( ( NAME )+ '-' t= type )
                // Pddl.g:150:8: ( NAME )+ '-' t= type
                {
                    // Pddl.g:150:8: ( NAME )+
                    int cnt15 = 0;
                    loop15:
                    while (true) {
                        int alt15 = 2;
                        int LA15_0 = input.LA(1);
                        if ((LA15_0 == NAME)) {
                            alt15 = 1;
                        }

                        switch (alt15) {
                            case 1: // Pddl.g:150:8: NAME
                            {
                                NAME34 = (Token) match(input, NAME, FOLLOW_NAME_in_singleTypeNameList732);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_NAME.add(NAME34);
                                }

                            }
                            break;

                            default:
                                if (cnt15 >= 1) {
                                    break loop15;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    }

                    char_literal35 = (Token) match(input, 72, FOLLOW_72_in_singleTypeNameList735);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_72.add(char_literal35);
                    }

                    pushFollow(FOLLOW_type_in_singleTypeNameList739);
                    t = type();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_type.add(t.getTree());
                    }
                }

                // AST REWRITE
                // elements: t, NAME
                // token labels:
                // rule labels: t, retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_t = new RewriteRuleSubtreeStream(adaptor, "rule t", t != null ? t.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 151:4: -> ( ^( NAME $t) )+
                    {
                        if (!(stream_t.hasNext() || stream_NAME.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_t.hasNext() || stream_NAME.hasNext()) {
                            // Pddl.g:151:7: ^( NAME $t)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_NAME.nextNode(), root_1);
                                adaptor.addChild(root_1, stream_t.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_t.reset();
                        stream_NAME.reset();

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 8, singleTypeNameList_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "type"
    // Pddl.g:154:1: type : ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType );
    public final PddlParser.type_return type ( ) throws RecognitionException {
        PddlParser.type_return retval = new PddlParser.type_return();
        retval.start = input.LT(1);
        int type_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal36 = null;
        Token string_literal37 = null;
        Token char_literal39 = null;
        ParserRuleReturnScope primType38 = null;
        ParserRuleReturnScope primType40 = null;

        Object char_literal36_tree = null;
        Object string_literal37_tree = null;
        Object char_literal39_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_116 = new RewriteRuleTokenStream(adaptor, "token 116");
        RewriteRuleSubtreeStream stream_primType = new RewriteRuleSubtreeStream(adaptor, "rule primType");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 9)) {
                return retval;
            }

            // Pddl.g:155:2: ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType )
            int alt17 = 2;
            int LA17_0 = input.LA(1);
            if ((LA17_0 == 68)) {
                alt17 = 1;
            } else if ((LA17_0 == NAME)) {
                alt17 = 2;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 17, 0, input);
                throw nvae;
            }

            switch (alt17) {
                case 1: // Pddl.g:155:4: ( '(' 'either' ( primType )+ ')' )
                {
                    // Pddl.g:155:4: ( '(' 'either' ( primType )+ ')' )
                    // Pddl.g:155:6: '(' 'either' ( primType )+ ')'
                    {
                        char_literal36 = (Token) match(input, 68, FOLLOW_68_in_type766);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_68.add(char_literal36);
                        }

                        string_literal37 = (Token) match(input, 116, FOLLOW_116_in_type768);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_116.add(string_literal37);
                        }

                        // Pddl.g:155:19: ( primType )+
                        int cnt16 = 0;
                        loop16:
                        while (true) {
                            int alt16 = 2;
                            int LA16_0 = input.LA(1);
                            if ((LA16_0 == NAME)) {
                                alt16 = 1;
                            }

                            switch (alt16) {
                                case 1: // Pddl.g:155:19: primType
                                {
                                    pushFollow(FOLLOW_primType_in_type770);
                                    primType38 = primType();
                                    state._fsp--;
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_primType.add(primType38.getTree());
                                    }
                                }
                                break;

                                default:
                                    if (cnt16 >= 1) {
                                        break loop16;
                                    }
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return retval;
                                    }
                                    EarlyExitException eee = new EarlyExitException(16, input);
                                    throw eee;
                            }
                            cnt16++;
                        }

                        char_literal39 = (Token) match(input, 69, FOLLOW_69_in_type773);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_69.add(char_literal39);
                        }

                    }

                    // AST REWRITE
                    // elements: primType
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 156:4: -> ^( EITHER_TYPE ( primType )+ )
                        {
                            // Pddl.g:156:7: ^( EITHER_TYPE ( primType )+ )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EITHER_TYPE, "EITHER_TYPE"), root_1);
                                if (!(stream_primType.hasNext())) {
                                    throw new RewriteEarlyExitException();
                                }
                                while (stream_primType.hasNext()) {
                                    adaptor.addChild(root_1, stream_primType.nextTree());
                                }
                                stream_primType.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                            adaptor.addChild(root_0, new String("debugLevel"));
                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:157:4: primType
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_primType_in_type794);
                    primType40 = primType();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, primType40.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 9, type_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "primType"
    // Pddl.g:160:1: primType : NAME ;
    public final PddlParser.primType_return primType ( ) throws RecognitionException {
        PddlParser.primType_return retval = new PddlParser.primType_return();
        retval.start = input.LT(1);
        int primType_StartIndex = input.index();

        Object root_0 = null;

        Token NAME41 = null;

        Object NAME41_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 10)) {
                return retval;
            }

            // Pddl.g:160:10: ( NAME )
            // Pddl.g:160:12: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME41 = (Token) match(input, NAME, FOLLOW_NAME_in_primType804);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    NAME41_tree = (Object) adaptor.create(NAME41);
                    adaptor.addChild(root_0, NAME41_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 10, primType_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "functionsDef"
    // Pddl.g:162:1: functionsDef : '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) ;
    public final PddlParser.functionsDef_return functionsDef ( ) throws RecognitionException {
        PddlParser.functionsDef_return retval = new PddlParser.functionsDef_return();
        retval.start = input.LT(1);
        int functionsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal42 = null;
        Token string_literal43 = null;
        Token char_literal45 = null;
        ParserRuleReturnScope functionList44 = null;

        Object char_literal42_tree = null;
        Object string_literal43_tree = null;
        Object char_literal45_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_86 = new RewriteRuleTokenStream(adaptor, "token 86");
        RewriteRuleSubtreeStream stream_functionList = new RewriteRuleSubtreeStream(adaptor, "rule functionList");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 11)) {
                return retval;
            }

            // Pddl.g:163:2: ( '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) )
            // Pddl.g:163:4: '(' ':functions' functionList ')'
            {
                char_literal42 = (Token) match(input, 68, FOLLOW_68_in_functionsDef814);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal42);
                }

                string_literal43 = (Token) match(input, 86, FOLLOW_86_in_functionsDef816);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_86.add(string_literal43);
                }

                pushFollow(FOLLOW_functionList_in_functionsDef818);
                functionList44 = functionList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_functionList.add(functionList44.getTree());
                }
                char_literal45 = (Token) match(input, 69, FOLLOW_69_in_functionsDef820);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal45);
                }

                // AST REWRITE
                // elements: functionList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 164:2: -> ^( FUNCTIONS functionList )
                    {
                        // Pddl.g:164:5: ^( FUNCTIONS functionList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FUNCTIONS, "FUNCTIONS"), root_1);
                            adaptor.addChild(root_1, stream_functionList.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 11, functionsDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "functionList"
    // Pddl.g:167:1: functionList : ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* ;
    public final PddlParser.functionList_return functionList ( ) throws RecognitionException {
        PddlParser.functionList_return retval = new PddlParser.functionList_return();
        retval.start = input.LT(1);
        int functionList_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal47 = null;
        ParserRuleReturnScope atomicFunctionSkeleton46 = null;
        ParserRuleReturnScope functionType48 = null;

        Object char_literal47_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 12)) {
                return retval;
            }

            // Pddl.g:168:2: ( ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* )
            // Pddl.g:168:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:168:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
                loop20:
                while (true) {
                    int alt20 = 2;
                    int LA20_0 = input.LA(1);
                    if ((LA20_0 == 68)) {
                        alt20 = 1;
                    }

                    switch (alt20) {
                        case 1: // Pddl.g:168:5: ( atomicFunctionSkeleton )+ ( '-' functionType )?
                        {
                            // Pddl.g:168:5: ( atomicFunctionSkeleton )+
                            int cnt18 = 0;
                            loop18:
                            while (true) {
                                int alt18 = 2;
                                int LA18_0 = input.LA(1);
                                if ((LA18_0 == 68)) {
                                    int LA18_2 = input.LA(2);
                                    if ((synpred18_Pddl())) {
                                        alt18 = 1;
                                    }

                                }

                                switch (alt18) {
                                    case 1: // Pddl.g:168:5: atomicFunctionSkeleton
                                    {
                                        pushFollow(FOLLOW_atomicFunctionSkeleton_in_functionList841);
                                        atomicFunctionSkeleton46 = atomicFunctionSkeleton();
                                        state._fsp--;
                                        if (state.failed) {
                                            return retval;
                                        }
                                        if (state.backtracking == 0) {
                                            adaptor.addChild(root_0, atomicFunctionSkeleton46.getTree());
                                        }

                                    }
                                    break;

                                    default:
                                        if (cnt18 >= 1) {
                                            break loop18;
                                        }
                                        if (state.backtracking > 0) {
                                            state.failed = true;
                                            return retval;
                                        }
                                        EarlyExitException eee = new EarlyExitException(18, input);
                                        throw eee;
                                }
                                cnt18++;
                            }

                            // Pddl.g:168:29: ( '-' functionType )?
                            int alt19 = 2;
                            int LA19_0 = input.LA(1);
                            if ((LA19_0 == 72)) {
                                alt19 = 1;
                            }
                            switch (alt19) {
                                case 1: // Pddl.g:168:30: '-' functionType
                                {
                                    char_literal47 = (Token) match(input, 72, FOLLOW_72_in_functionList845);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        char_literal47_tree = (Object) adaptor.create(char_literal47);
                                        adaptor.addChild(root_0, char_literal47_tree);
                                    }

                                    pushFollow(FOLLOW_functionType_in_functionList847);
                                    functionType48 = functionType();
                                    state._fsp--;
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        adaptor.addChild(root_0, functionType48.getTree());
                                    }

                                }
                                break;

                            }

                        }
                        break;

                        default:
                            break loop20;
                    }
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 12, functionList_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "atomicFunctionSkeleton"
    // Pddl.g:171:1: atomicFunctionSkeleton : '(' ! functionSymbol ^ typedVariableList ')' !;
    public final PddlParser.atomicFunctionSkeleton_return atomicFunctionSkeleton ( ) throws RecognitionException {
        PddlParser.atomicFunctionSkeleton_return retval = new PddlParser.atomicFunctionSkeleton_return();
        retval.start = input.LT(1);
        int atomicFunctionSkeleton_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal49 = null;
        Token char_literal52 = null;
        ParserRuleReturnScope functionSymbol50 = null;
        ParserRuleReturnScope typedVariableList51 = null;

        Object char_literal49_tree = null;
        Object char_literal52_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 13)) {
                return retval;
            }

            // Pddl.g:172:2: ( '(' ! functionSymbol ^ typedVariableList ')' !)
            // Pddl.g:172:4: '(' ! functionSymbol ^ typedVariableList ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal49 = (Token) match(input, 68, FOLLOW_68_in_atomicFunctionSkeleton863);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_functionSymbol_in_atomicFunctionSkeleton866);
                functionSymbol50 = functionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    root_0 = (Object) adaptor.becomeRoot(functionSymbol50.getTree(), root_0);
                }
                pushFollow(FOLLOW_typedVariableList_in_atomicFunctionSkeleton869);
                typedVariableList51 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList51.getTree());
                }

                char_literal52 = (Token) match(input, 69, FOLLOW_69_in_atomicFunctionSkeleton871);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 13, atomicFunctionSkeleton_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "functionSymbol"
    // Pddl.g:175:1: functionSymbol : ( NAME | '#t' );
    public final PddlParser.functionSymbol_return functionSymbol ( ) throws RecognitionException {
        PddlParser.functionSymbol_return retval = new PddlParser.functionSymbol_return();
        retval.start = input.LT(1);
        int functionSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token set53 = null;

        Object set53_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 14)) {
                return retval;
            }

            // Pddl.g:175:16: ( NAME | '#t' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set53 = input.LT(1);
                if (input.LA(1) == NAME || input.LA(1) == 67) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set53));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 14, functionSymbol_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "functionType"
    // Pddl.g:177:1: functionType : 'number' ;
    public final PddlParser.functionType_return functionType ( ) throws RecognitionException {
        PddlParser.functionType_return retval = new PddlParser.functionType_return();
        retval.start = input.LT(1);
        int functionType_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal54 = null;

        Object string_literal54_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 15)) {
                return retval;
            }

            // Pddl.g:177:14: ( 'number' )
            // Pddl.g:177:16: 'number'
            {
                root_0 = (Object) adaptor.nil();

                string_literal54 = (Token) match(input, 128, FOLLOW_128_in_functionType894);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal54_tree = (Object) adaptor.create(string_literal54);
                    adaptor.addChild(root_0, string_literal54_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 15, functionType_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "constantsDef"
    // Pddl.g:179:1: constantsDef : '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) ;
    public final PddlParser.constantsDef_return constantsDef ( ) throws RecognitionException {
        PddlParser.constantsDef_return retval = new PddlParser.constantsDef_return();
        retval.start = input.LT(1);
        int constantsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal55 = null;
        Token string_literal56 = null;
        Token char_literal58 = null;
        ParserRuleReturnScope typedNameList57 = null;

        Object char_literal55_tree = null;
        Object string_literal56_tree = null;
        Object char_literal58_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_76 = new RewriteRuleTokenStream(adaptor, "token 76");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 16)) {
                return retval;
            }

            // Pddl.g:180:2: ( '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) )
            // Pddl.g:180:4: '(' ':constants' typedNameList ')'
            {
                char_literal55 = (Token) match(input, 68, FOLLOW_68_in_constantsDef905);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal55);
                }

                string_literal56 = (Token) match(input, 76, FOLLOW_76_in_constantsDef907);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_76.add(string_literal56);
                }

                pushFollow(FOLLOW_typedNameList_in_constantsDef909);
                typedNameList57 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList57.getTree());
                }
                char_literal58 = (Token) match(input, 69, FOLLOW_69_in_constantsDef911);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal58);
                }

                // AST REWRITE
                // elements: typedNameList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 181:2: -> ^( CONSTANTS typedNameList )
                    {
                        // Pddl.g:181:5: ^( CONSTANTS typedNameList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(CONSTANTS, "CONSTANTS"), root_1);
                            adaptor.addChild(root_1, stream_typedNameList.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 16, constantsDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "predicatesDef"
    // Pddl.g:184:1: predicatesDef : '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) ;
    public final PddlParser.predicatesDef_return predicatesDef ( ) throws RecognitionException {
        PddlParser.predicatesDef_return retval = new PddlParser.predicatesDef_return();
        retval.start = input.LT(1);
        int predicatesDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal59 = null;
        Token string_literal60 = null;
        Token char_literal62 = null;
        ParserRuleReturnScope atomicFormulaSkeleton61 = null;

        Object char_literal59_tree = null;
        Object string_literal60_tree = null;
        Object char_literal62_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_93 = new RewriteRuleTokenStream(adaptor, "token 93");
        RewriteRuleSubtreeStream stream_atomicFormulaSkeleton = new RewriteRuleSubtreeStream(adaptor, "rule atomicFormulaSkeleton");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 17)) {
                return retval;
            }

            // Pddl.g:185:2: ( '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) )
            // Pddl.g:185:4: '(' ':predicates' ( atomicFormulaSkeleton )+ ')'
            {
                char_literal59 = (Token) match(input, 68, FOLLOW_68_in_predicatesDef931);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal59);
                }

                string_literal60 = (Token) match(input, 93, FOLLOW_93_in_predicatesDef933);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_93.add(string_literal60);
                }

                // Pddl.g:185:22: ( atomicFormulaSkeleton )+
                int cnt21 = 0;
                loop21:
                while (true) {
                    int alt21 = 2;
                    int LA21_0 = input.LA(1);
                    if ((LA21_0 == 68)) {
                        alt21 = 1;
                    }

                    switch (alt21) {
                        case 1: // Pddl.g:185:22: atomicFormulaSkeleton
                        {
                            pushFollow(FOLLOW_atomicFormulaSkeleton_in_predicatesDef935);
                            atomicFormulaSkeleton61 = atomicFormulaSkeleton();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_atomicFormulaSkeleton.add(atomicFormulaSkeleton61.getTree());
                            }
                        }
                        break;

                        default:
                            if (cnt21 >= 1) {
                                break loop21;
                            }
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            EarlyExitException eee = new EarlyExitException(21, input);
                            throw eee;
                    }
                    cnt21++;
                }

                char_literal62 = (Token) match(input, 69, FOLLOW_69_in_predicatesDef938);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal62);
                }

                // AST REWRITE
                // elements: atomicFormulaSkeleton
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 186:2: -> ^( PREDICATES ( atomicFormulaSkeleton )+ )
                    {
                        // Pddl.g:186:5: ^( PREDICATES ( atomicFormulaSkeleton )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PREDICATES, "PREDICATES"), root_1);
                            if (!(stream_atomicFormulaSkeleton.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_atomicFormulaSkeleton.hasNext()) {
                                adaptor.addChild(root_1, stream_atomicFormulaSkeleton.nextTree());
                            }
                            stream_atomicFormulaSkeleton.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 17, predicatesDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "atomicFormulaSkeleton"
    // Pddl.g:189:1: atomicFormulaSkeleton : '(' ! predicate ^ typedVariableList ')' !;
    public final PddlParser.atomicFormulaSkeleton_return atomicFormulaSkeleton ( ) throws RecognitionException {
        PddlParser.atomicFormulaSkeleton_return retval = new PddlParser.atomicFormulaSkeleton_return();
        retval.start = input.LT(1);
        int atomicFormulaSkeleton_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal63 = null;
        Token char_literal66 = null;
        ParserRuleReturnScope predicate64 = null;
        ParserRuleReturnScope typedVariableList65 = null;

        Object char_literal63_tree = null;
        Object char_literal66_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 18)) {
                return retval;
            }

            // Pddl.g:190:2: ( '(' ! predicate ^ typedVariableList ')' !)
            // Pddl.g:190:4: '(' ! predicate ^ typedVariableList ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal63 = (Token) match(input, 68, FOLLOW_68_in_atomicFormulaSkeleton959);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_predicate_in_atomicFormulaSkeleton962);
                predicate64 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    root_0 = (Object) adaptor.becomeRoot(predicate64.getTree(), root_0);
                }
                pushFollow(FOLLOW_typedVariableList_in_atomicFormulaSkeleton965);
                typedVariableList65 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList65.getTree());
                }

                char_literal66 = (Token) match(input, 69, FOLLOW_69_in_atomicFormulaSkeleton967);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 18, atomicFormulaSkeleton_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "predicate"
    // Pddl.g:193:1: predicate : NAME ;
    public final PddlParser.predicate_return predicate ( ) throws RecognitionException {
        PddlParser.predicate_return retval = new PddlParser.predicate_return();
        retval.start = input.LT(1);
        int predicate_StartIndex = input.index();

        Object root_0 = null;

        Token NAME67 = null;

        Object NAME67_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 19)) {
                return retval;
            }

            // Pddl.g:193:11: ( NAME )
            // Pddl.g:193:13: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME67 = (Token) match(input, NAME, FOLLOW_NAME_in_predicate978);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    NAME67_tree = (Object) adaptor.create(NAME67);
                    adaptor.addChild(root_0, NAME67_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 19, predicate_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "typedVariableList"
    // Pddl.g:196:1: typedVariableList : ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) ;
    public final PddlParser.typedVariableList_return typedVariableList ( ) throws RecognitionException {
        PddlParser.typedVariableList_return retval = new PddlParser.typedVariableList_return();
        retval.start = input.LT(1);
        int typedVariableList_StartIndex = input.index();

        Object root_0 = null;

        Token VARIABLE68 = null;
        Token VARIABLE70 = null;
        ParserRuleReturnScope singleTypeVarList69 = null;

        Object VARIABLE68_tree = null;
        Object VARIABLE70_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 20)) {
                return retval;
            }

            // Pddl.g:197:5: ( ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) )
            // Pddl.g:197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
                int alt25 = 2;
                alt25 = dfa25.predict(input);
                switch (alt25) {
                    case 1: // Pddl.g:197:8: ( VARIABLE )*
                    {
                        // Pddl.g:197:8: ( VARIABLE )*
                        loop22:
                        while (true) {
                            int alt22 = 2;
                            int LA22_0 = input.LA(1);
                            if ((LA22_0 == VARIABLE)) {
                                alt22 = 1;
                            }

                            switch (alt22) {
                                case 1: // Pddl.g:197:8: VARIABLE
                                {
                                    VARIABLE68 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_typedVariableList993);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        VARIABLE68_tree = (Object) adaptor.create(VARIABLE68);
                                        adaptor.addChild(root_0, VARIABLE68_tree);
                                    }

                                }
                                break;

                                default:
                                    break loop22;
                            }
                        }

                    }
                    break;
                    case 2: // Pddl.g:197:20: ( singleTypeVarList )+ ( VARIABLE )*
                    {
                        // Pddl.g:197:20: ( singleTypeVarList )+
                        int cnt23 = 0;
                        loop23:
                        while (true) {
                            int alt23 = 2;
                            alt23 = dfa23.predict(input);
                            switch (alt23) {
                                case 1: // Pddl.g:197:20: singleTypeVarList
                                {
                                    pushFollow(FOLLOW_singleTypeVarList_in_typedVariableList998);
                                    singleTypeVarList69 = singleTypeVarList();
                                    state._fsp--;
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        adaptor.addChild(root_0, singleTypeVarList69.getTree());
                                    }

                                }
                                break;

                                default:
                                    if (cnt23 >= 1) {
                                        break loop23;
                                    }
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return retval;
                                    }
                                    EarlyExitException eee = new EarlyExitException(23, input);
                                    throw eee;
                            }
                            cnt23++;
                        }

                        // Pddl.g:197:39: ( VARIABLE )*
                        loop24:
                        while (true) {
                            int alt24 = 2;
                            int LA24_0 = input.LA(1);
                            if ((LA24_0 == VARIABLE)) {
                                alt24 = 1;
                            }

                            switch (alt24) {
                                case 1: // Pddl.g:197:39: VARIABLE
                                {
                                    VARIABLE70 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_typedVariableList1001);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        VARIABLE70_tree = (Object) adaptor.create(VARIABLE70);
                                        adaptor.addChild(root_0, VARIABLE70_tree);
                                    }

                                }
                                break;

                                default:
                                    break loop24;
                            }
                        }

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 20, typedVariableList_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "singleTypeVarList"
    // Pddl.g:200:1: singleTypeVarList : ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ ;
    public final PddlParser.singleTypeVarList_return singleTypeVarList ( ) throws RecognitionException {
        PddlParser.singleTypeVarList_return retval = new PddlParser.singleTypeVarList_return();
        retval.start = input.LT(1);
        int singleTypeVarList_StartIndex = input.index();

        Object root_0 = null;

        Token VARIABLE71 = null;
        Token char_literal72 = null;
        ParserRuleReturnScope t = null;

        Object VARIABLE71_tree = null;
        Object char_literal72_tree = null;
        RewriteRuleTokenStream stream_VARIABLE = new RewriteRuleTokenStream(adaptor, "token VARIABLE");
        RewriteRuleTokenStream stream_72 = new RewriteRuleTokenStream(adaptor, "token 72");
        RewriteRuleSubtreeStream stream_type = new RewriteRuleSubtreeStream(adaptor, "rule type");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 21)) {
                return retval;
            }

            // Pddl.g:201:5: ( ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ )
            // Pddl.g:201:7: ( ( VARIABLE )+ '-' t= type )
            {
                // Pddl.g:201:7: ( ( VARIABLE )+ '-' t= type )
                // Pddl.g:201:8: ( VARIABLE )+ '-' t= type
                {
                    // Pddl.g:201:8: ( VARIABLE )+
                    int cnt26 = 0;
                    loop26:
                    while (true) {
                        int alt26 = 2;
                        int LA26_0 = input.LA(1);
                        if ((LA26_0 == VARIABLE)) {
                            alt26 = 1;
                        }

                        switch (alt26) {
                            case 1: // Pddl.g:201:8: VARIABLE
                            {
                                VARIABLE71 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_singleTypeVarList1021);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_VARIABLE.add(VARIABLE71);
                                }

                            }
                            break;

                            default:
                                if (cnt26 >= 1) {
                                    break loop26;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    }

                    char_literal72 = (Token) match(input, 72, FOLLOW_72_in_singleTypeVarList1024);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_72.add(char_literal72);
                    }

                    pushFollow(FOLLOW_type_in_singleTypeVarList1028);
                    t = type();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_type.add(t.getTree());
                    }
                }

                // AST REWRITE
                // elements: t, VARIABLE
                // token labels:
                // rule labels: t, retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_t = new RewriteRuleSubtreeStream(adaptor, "rule t", t != null ? t.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 202:7: -> ( ^( VARIABLE $t) )+
                    {
                        if (!(stream_t.hasNext() || stream_VARIABLE.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_t.hasNext() || stream_VARIABLE.hasNext()) {
                            // Pddl.g:202:10: ^( VARIABLE $t)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_VARIABLE.nextNode(), root_1);
                                adaptor.addChild(root_1, stream_t.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_t.reset();
                        stream_VARIABLE.reset();

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 21, singleTypeVarList_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "constraints"
    // Pddl.g:205:1: constraints : '(' ! ':constraints' ^ conGD ')' !;
    public final PddlParser.constraints_return constraints ( ) throws RecognitionException {
        PddlParser.constraints_return retval = new PddlParser.constraints_return();
        retval.start = input.LT(1);
        int constraints_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal73 = null;
        Token string_literal74 = null;
        Token char_literal76 = null;
        ParserRuleReturnScope conGD75 = null;

        Object char_literal73_tree = null;
        Object string_literal74_tree = null;
        Object char_literal76_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 22)) {
                return retval;
            }

            // Pddl.g:206:2: ( '(' ! ':constraints' ^ conGD ')' !)
            // Pddl.g:206:4: '(' ! ':constraints' ^ conGD ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal73 = (Token) match(input, 68, FOLLOW_68_in_constraints1059);
                if (state.failed) {
                    return retval;
                }
                string_literal74 = (Token) match(input, 78, FOLLOW_78_in_constraints1062);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal74_tree = (Object) adaptor.create(string_literal74);
                    root_0 = (Object) adaptor.becomeRoot(string_literal74_tree, root_0);
                }

                pushFollow(FOLLOW_conGD_in_constraints1065);
                conGD75 = conGD();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, conGD75.getTree());
                }

                char_literal76 = (Token) match(input, 69, FOLLOW_69_in_constraints1067);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 22, constraints_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "structureDef"
    // Pddl.g:209:1: structureDef : ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef );
    public final PddlParser.structureDef_return structureDef ( ) throws RecognitionException {
        PddlParser.structureDef_return retval = new PddlParser.structureDef_return();
        retval.start = input.LT(1);
        int structureDef_StartIndex = input.index();

        Object root_0 = null;

        ParserRuleReturnScope actionDef77 = null;
        ParserRuleReturnScope durativeActionDef78 = null;
        ParserRuleReturnScope derivedDef79 = null;
        ParserRuleReturnScope constraintDef80 = null;
        ParserRuleReturnScope processDef81 = null;
        ParserRuleReturnScope eventDef82 = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 23)) {
                return retval;
            }

            // Pddl.g:210:2: ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef )
            int alt27 = 6;
            int LA27_0 = input.LA(1);
            if ((LA27_0 == 68)) {
                switch (input.LA(2)) {
                    case 74: {
                        alt27 = 1;
                    }
                    break;
                    case 82: {
                        alt27 = 2;
                    }
                    break;
                    case 79: {
                        alt27 = 3;
                    }
                    break;
                    case 77: {
                        alt27 = 4;
                    }
                    break;
                    case 94: {
                        alt27 = 5;
                    }
                    break;
                    case 84: {
                        alt27 = 6;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 27, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 27, 0, input);
                throw nvae;
            }

            switch (alt27) {
                case 1: // Pddl.g:210:4: actionDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_actionDef_in_structureDef1079);
                    actionDef77 = actionDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, actionDef77.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:211:4: durativeActionDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_durativeActionDef_in_structureDef1084);
                    durativeActionDef78 = durativeActionDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durativeActionDef78.getTree());
                    }

                }
                break;
                case 3: // Pddl.g:212:4: derivedDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_derivedDef_in_structureDef1089);
                    derivedDef79 = derivedDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, derivedDef79.getTree());
                    }

                }
                break;
                case 4: // Pddl.g:213:4: constraintDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_constraintDef_in_structureDef1094);
                    constraintDef80 = constraintDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, constraintDef80.getTree());
                    }

                }
                break;
                case 5: // Pddl.g:214:4: processDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_processDef_in_structureDef1099);
                    processDef81 = processDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, processDef81.getTree());
                    }

                }
                break;
                case 6: // Pddl.g:215:4: eventDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_eventDef_in_structureDef1104);
                    eventDef82 = eventDef();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, eventDef82.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 23, structureDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "actionDef"
    // Pddl.g:221:1: actionDef : '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.actionDef_return actionDef ( ) throws RecognitionException {
        PddlParser.actionDef_return retval = new PddlParser.actionDef_return();
        retval.start = input.LT(1);
        int actionDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal83 = null;
        Token string_literal84 = null;
        Token string_literal86 = null;
        Token char_literal87 = null;
        Token char_literal89 = null;
        Token char_literal91 = null;
        ParserRuleReturnScope actionSymbol85 = null;
        ParserRuleReturnScope typedVariableList88 = null;
        ParserRuleReturnScope actionDefBody90 = null;

        Object char_literal83_tree = null;
        Object string_literal84_tree = null;
        Object string_literal86_tree = null;
        Object char_literal87_tree = null;
        Object char_literal89_tree = null;
        Object char_literal91_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleTokenStream stream_74 = new RewriteRuleTokenStream(adaptor, "token 74");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 24)) {
                return retval;
            }

            // Pddl.g:222:2: ( '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:222:4: '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal83 = (Token) match(input, 68, FOLLOW_68_in_actionDef1119);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal83);
                }

                string_literal84 = (Token) match(input, 74, FOLLOW_74_in_actionDef1121);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_74.add(string_literal84);
                }

                pushFollow(FOLLOW_actionSymbol_in_actionDef1123);
                actionSymbol85 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol85.getTree());
                }
                string_literal86 = (Token) match(input, 91, FOLLOW_91_in_actionDef1132);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_91.add(string_literal86);
                }

                char_literal87 = (Token) match(input, 68, FOLLOW_68_in_actionDef1135);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal87);
                }

                pushFollow(FOLLOW_typedVariableList_in_actionDef1137);
                typedVariableList88 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList88.getTree());
                }
                char_literal89 = (Token) match(input, 69, FOLLOW_69_in_actionDef1139);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal89);
                }

                pushFollow(FOLLOW_actionDefBody_in_actionDef1152);
                actionDefBody90 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody90.getTree());
                }
                char_literal91 = (Token) match(input, 69, FOLLOW_69_in_actionDef1154);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal91);
                }

                // AST REWRITE
                // elements: typedVariableList, actionSymbol, actionDefBody
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 225:8: -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:225:11: ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ACTION, "ACTION"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:225:33: ( typedVariableList )?
                            if (stream_typedVariableList.hasNext()) {
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                            }
                            stream_typedVariableList.reset();

                            adaptor.addChild(root_1, stream_actionDefBody.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 24, actionDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "eventDef"
    // Pddl.g:227:1: eventDef : '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.eventDef_return eventDef ( ) throws RecognitionException {
        PddlParser.eventDef_return retval = new PddlParser.eventDef_return();
        retval.start = input.LT(1);
        int eventDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal92 = null;
        Token string_literal93 = null;
        Token string_literal95 = null;
        Token char_literal96 = null;
        Token char_literal98 = null;
        Token char_literal100 = null;
        ParserRuleReturnScope actionSymbol94 = null;
        ParserRuleReturnScope typedVariableList97 = null;
        ParserRuleReturnScope actionDefBody99 = null;

        Object char_literal92_tree = null;
        Object string_literal93_tree = null;
        Object string_literal95_tree = null;
        Object char_literal96_tree = null;
        Object char_literal98_tree = null;
        Object char_literal100_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleTokenStream stream_84 = new RewriteRuleTokenStream(adaptor, "token 84");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 25)) {
                return retval;
            }

            // Pddl.g:228:2: ( '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:228:4: '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal92 = (Token) match(input, 68, FOLLOW_68_in_eventDef1187);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal92);
                }

                string_literal93 = (Token) match(input, 84, FOLLOW_84_in_eventDef1189);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_84.add(string_literal93);
                }

                pushFollow(FOLLOW_actionSymbol_in_eventDef1191);
                actionSymbol94 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol94.getTree());
                }
                string_literal95 = (Token) match(input, 91, FOLLOW_91_in_eventDef1200);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_91.add(string_literal95);
                }

                char_literal96 = (Token) match(input, 68, FOLLOW_68_in_eventDef1203);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal96);
                }

                pushFollow(FOLLOW_typedVariableList_in_eventDef1205);
                typedVariableList97 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList97.getTree());
                }
                char_literal98 = (Token) match(input, 69, FOLLOW_69_in_eventDef1207);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal98);
                }

                pushFollow(FOLLOW_actionDefBody_in_eventDef1220);
                actionDefBody99 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody99.getTree());
                }
                char_literal100 = (Token) match(input, 69, FOLLOW_69_in_eventDef1222);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal100);
                }

                // AST REWRITE
                // elements: typedVariableList, actionDefBody, actionSymbol
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 231:8: -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:231:11: ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EVENT, "EVENT"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:231:32: ( typedVariableList )?
                            if (stream_typedVariableList.hasNext()) {
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                            }
                            stream_typedVariableList.reset();

                            adaptor.addChild(root_1, stream_actionDefBody.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 25, eventDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "processDef"
    // Pddl.g:233:1: processDef : '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.processDef_return processDef ( ) throws RecognitionException {
        PddlParser.processDef_return retval = new PddlParser.processDef_return();
        retval.start = input.LT(1);
        int processDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal101 = null;
        Token string_literal102 = null;
        Token string_literal104 = null;
        Token char_literal105 = null;
        Token char_literal107 = null;
        Token char_literal109 = null;
        ParserRuleReturnScope actionSymbol103 = null;
        ParserRuleReturnScope typedVariableList106 = null;
        ParserRuleReturnScope actionDefBody108 = null;

        Object char_literal101_tree = null;
        Object string_literal102_tree = null;
        Object string_literal104_tree = null;
        Object char_literal105_tree = null;
        Object char_literal107_tree = null;
        Object char_literal109_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleTokenStream stream_94 = new RewriteRuleTokenStream(adaptor, "token 94");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 26)) {
                return retval;
            }

            // Pddl.g:234:2: ( '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:234:4: '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal101 = (Token) match(input, 68, FOLLOW_68_in_processDef1255);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal101);
                }

                string_literal102 = (Token) match(input, 94, FOLLOW_94_in_processDef1257);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_94.add(string_literal102);
                }

                pushFollow(FOLLOW_actionSymbol_in_processDef1259);
                actionSymbol103 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol103.getTree());
                }
                string_literal104 = (Token) match(input, 91, FOLLOW_91_in_processDef1268);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_91.add(string_literal104);
                }

                char_literal105 = (Token) match(input, 68, FOLLOW_68_in_processDef1271);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal105);
                }

                pushFollow(FOLLOW_typedVariableList_in_processDef1273);
                typedVariableList106 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList106.getTree());
                }
                char_literal107 = (Token) match(input, 69, FOLLOW_69_in_processDef1275);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal107);
                }

                pushFollow(FOLLOW_actionDefBody_in_processDef1288);
                actionDefBody108 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody108.getTree());
                }
                char_literal109 = (Token) match(input, 69, FOLLOW_69_in_processDef1290);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal109);
                }

                // AST REWRITE
                // elements: actionDefBody, actionSymbol, typedVariableList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 237:8: -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:237:11: ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROCESS, "PROCESS"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:237:34: ( typedVariableList )?
                            if (stream_typedVariableList.hasNext()) {
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                            }
                            stream_typedVariableList.reset();

                            adaptor.addChild(root_1, stream_actionDefBody.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 26, processDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "constraintDef"
    // Pddl.g:240:1: constraintDef : '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) ;
    public final PddlParser.constraintDef_return constraintDef ( ) throws RecognitionException {
        PddlParser.constraintDef_return retval = new PddlParser.constraintDef_return();
        retval.start = input.LT(1);
        int constraintDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal110 = null;
        Token string_literal111 = null;
        Token string_literal113 = null;
        Token char_literal114 = null;
        Token char_literal116 = null;
        Token char_literal118 = null;
        ParserRuleReturnScope constraintSymbol112 = null;
        ParserRuleReturnScope typedVariableList115 = null;
        ParserRuleReturnScope constraintDefBody117 = null;

        Object char_literal110_tree = null;
        Object string_literal111_tree = null;
        Object string_literal113_tree = null;
        Object char_literal114_tree = null;
        Object char_literal116_tree = null;
        Object char_literal118_tree = null;
        RewriteRuleTokenStream stream_77 = new RewriteRuleTokenStream(adaptor, "token 77");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_constraintDefBody = new RewriteRuleSubtreeStream(adaptor, "rule constraintDefBody");
        RewriteRuleSubtreeStream stream_constraintSymbol = new RewriteRuleSubtreeStream(adaptor, "rule constraintSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 27)) {
                return retval;
            }

            // Pddl.g:241:2: ( '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) )
            // Pddl.g:241:4: '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')'
            {
                char_literal110 = (Token) match(input, 68, FOLLOW_68_in_constraintDef1324);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal110);
                }

                string_literal111 = (Token) match(input, 77, FOLLOW_77_in_constraintDef1326);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_77.add(string_literal111);
                }

                pushFollow(FOLLOW_constraintSymbol_in_constraintDef1328);
                constraintSymbol112 = constraintSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_constraintSymbol.add(constraintSymbol112.getTree());
                }
                string_literal113 = (Token) match(input, 91, FOLLOW_91_in_constraintDef1337);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_91.add(string_literal113);
                }

                char_literal114 = (Token) match(input, 68, FOLLOW_68_in_constraintDef1340);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal114);
                }

                pushFollow(FOLLOW_typedVariableList_in_constraintDef1342);
                typedVariableList115 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList115.getTree());
                }
                char_literal116 = (Token) match(input, 69, FOLLOW_69_in_constraintDef1344);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal116);
                }

                pushFollow(FOLLOW_constraintDefBody_in_constraintDef1357);
                constraintDefBody117 = constraintDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_constraintDefBody.add(constraintDefBody117.getTree());
                }
                char_literal118 = (Token) match(input, 69, FOLLOW_69_in_constraintDef1359);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal118);
                }

                // AST REWRITE
                // elements: constraintSymbol, typedVariableList, constraintDefBody
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 244:8: -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
                    {
                        // Pddl.g:244:11: ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(GLOBAL_CONSTRAINT, "GLOBAL_CONSTRAINT"), root_1);
                            adaptor.addChild(root_1, stream_constraintSymbol.nextTree());
                            // Pddl.g:244:48: ( typedVariableList )?
                            if (stream_typedVariableList.hasNext()) {
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                            }
                            stream_typedVariableList.reset();

                            adaptor.addChild(root_1, stream_constraintDefBody.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 27, constraintDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "actionSymbol"
    // Pddl.g:248:1: actionSymbol : NAME ;
    public final PddlParser.actionSymbol_return actionSymbol ( ) throws RecognitionException {
        PddlParser.actionSymbol_return retval = new PddlParser.actionSymbol_return();
        retval.start = input.LT(1);
        int actionSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token NAME119 = null;

        Object NAME119_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 28)) {
                return retval;
            }

            // Pddl.g:248:14: ( NAME )
            // Pddl.g:248:16: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME119 = (Token) match(input, NAME, FOLLOW_NAME_in_actionSymbol1393);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    NAME119_tree = (Object) adaptor.create(NAME119);
                    adaptor.addChild(root_0, NAME119_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 28, actionSymbol_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "constraintSymbol"
    // Pddl.g:250:1: constraintSymbol : NAME ;
    public final PddlParser.constraintSymbol_return constraintSymbol ( ) throws RecognitionException {
        PddlParser.constraintSymbol_return retval = new PddlParser.constraintSymbol_return();
        retval.start = input.LT(1);
        int constraintSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token NAME120 = null;

        Object NAME120_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 29)) {
                return retval;
            }

            // Pddl.g:250:18: ( NAME )
            // Pddl.g:250:20: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME120 = (Token) match(input, NAME, FOLLOW_NAME_in_constraintSymbol1402);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    NAME120_tree = (Object) adaptor.create(NAME120);
                    adaptor.addChild(root_0, NAME120_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 29, constraintSymbol_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "actionDefBody"
    // Pddl.g:255:1: actionDefBody : ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) ;
    public final PddlParser.actionDefBody_return actionDefBody ( ) throws RecognitionException {
        PddlParser.actionDefBody_return retval = new PddlParser.actionDefBody_return();
        retval.start = input.LT(1);
        int actionDefBody_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal121 = null;
        Token char_literal122 = null;
        Token char_literal123 = null;
        Token string_literal125 = null;
        Token char_literal126 = null;
        Token char_literal127 = null;
        ParserRuleReturnScope goalDesc124 = null;
        ParserRuleReturnScope effect128 = null;

        Object string_literal121_tree = null;
        Object char_literal122_tree = null;
        Object char_literal123_tree = null;
        Object string_literal125_tree = null;
        Object char_literal126_tree = null;
        Object char_literal127_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_92 = new RewriteRuleTokenStream(adaptor, "token 92");
        RewriteRuleTokenStream stream_83 = new RewriteRuleTokenStream(adaptor, "token 83");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_effect = new RewriteRuleSubtreeStream(adaptor, "rule effect");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 30)) {
                return retval;
            }

            // Pddl.g:256:2: ( ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) )
            // Pddl.g:256:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )?
            {
                // Pddl.g:256:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )?
                int alt29 = 2;
                int LA29_0 = input.LA(1);
                if ((LA29_0 == 92)) {
                    alt29 = 1;
                }
                switch (alt29) {
                    case 1: // Pddl.g:256:6: ':precondition' ( ( '(' ')' ) | goalDesc )
                    {
                        string_literal121 = (Token) match(input, 92, FOLLOW_92_in_actionDefBody1417);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_92.add(string_literal121);
                        }

                        // Pddl.g:256:22: ( ( '(' ')' ) | goalDesc )
                        int alt28 = 2;
                        int LA28_0 = input.LA(1);
                        if ((LA28_0 == 68)) {
                            int LA28_1 = input.LA(2);
                            if ((LA28_1 == 69)) {
                                alt28 = 1;
                            } else if ((LA28_1 == NAME || (LA28_1 >= 97 && LA28_1 <= 101) || LA28_1 == 108 || (LA28_1 >= 118 && LA28_1 <= 119) || LA28_1 == 122 || LA28_1 == 127 || (LA28_1 >= 129 && LA28_1 <= 130))) {
                                alt28 = 2;
                            } else {
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                int nvaeMark = input.mark();
                                try {
                                    input.consume();
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 28, 1, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                            }

                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            NoViableAltException nvae
                                    = new NoViableAltException("", 28, 0, input);
                            throw nvae;
                        }

                        switch (alt28) {
                            case 1: // Pddl.g:256:23: ( '(' ')' )
                            {
                                // Pddl.g:256:23: ( '(' ')' )
                                // Pddl.g:256:24: '(' ')'
                                {
                                    char_literal122 = (Token) match(input, 68, FOLLOW_68_in_actionDefBody1421);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal122);
                                    }

                                    char_literal123 = (Token) match(input, 69, FOLLOW_69_in_actionDefBody1423);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_69.add(char_literal123);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:256:35: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_actionDefBody1428);
                                goalDesc124 = goalDesc();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_goalDesc.add(goalDesc124.getTree());
                                }
                            }
                            break;

                        }

                    }
                    break;

                }

                // Pddl.g:257:4: ( ':effect' ( ( '(' ')' ) | effect ) )?
                int alt31 = 2;
                int LA31_0 = input.LA(1);
                if ((LA31_0 == 83)) {
                    alt31 = 1;
                }
                switch (alt31) {
                    case 1: // Pddl.g:257:6: ':effect' ( ( '(' ')' ) | effect )
                    {
                        string_literal125 = (Token) match(input, 83, FOLLOW_83_in_actionDefBody1438);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_83.add(string_literal125);
                        }

                        // Pddl.g:257:16: ( ( '(' ')' ) | effect )
                        int alt30 = 2;
                        int LA30_0 = input.LA(1);
                        if ((LA30_0 == 68)) {
                            int LA30_1 = input.LA(2);
                            if ((LA30_1 == 69)) {
                                alt30 = 1;
                            } else if ((LA30_1 == NAME || (LA30_1 >= 108 && LA30_1 <= 109) || LA30_1 == 113 || LA30_1 == 119 || LA30_1 == 123 || LA30_1 == 127 || (LA30_1 >= 134 && LA30_1 <= 135) || LA30_1 == 142)) {
                                alt30 = 2;
                            } else {
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                int nvaeMark = input.mark();
                                try {
                                    input.consume();
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 30, 1, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                            }

                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            NoViableAltException nvae
                                    = new NoViableAltException("", 30, 0, input);
                            throw nvae;
                        }

                        switch (alt30) {
                            case 1: // Pddl.g:257:17: ( '(' ')' )
                            {
                                // Pddl.g:257:17: ( '(' ')' )
                                // Pddl.g:257:18: '(' ')'
                                {
                                    char_literal126 = (Token) match(input, 68, FOLLOW_68_in_actionDefBody1442);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal126);
                                    }

                                    char_literal127 = (Token) match(input, 69, FOLLOW_69_in_actionDefBody1444);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_69.add(char_literal127);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:257:29: effect
                            {
                                pushFollow(FOLLOW_effect_in_actionDefBody1449);
                                effect128 = effect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_effect.add(effect128.getTree());
                                }
                            }
                            break;

                        }

                    }
                    break;

                }

                // AST REWRITE
                // elements: goalDesc, effect
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 258:4: -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? )
                    {
                        // Pddl.g:258:7: ^( PRECONDITION ( goalDesc )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
                            // Pddl.g:258:22: ( goalDesc )?
                            if (stream_goalDesc.hasNext()) {
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                            }
                            stream_goalDesc.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                        // Pddl.g:258:33: ^( EFFECT ( effect )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EFFECT, "EFFECT"), root_1);
                            // Pddl.g:258:42: ( effect )?
                            if (stream_effect.hasNext()) {
                                adaptor.addChild(root_1, stream_effect.nextTree());
                            }
                            stream_effect.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 30, actionDefBody_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "constraintDefBody"
    // Pddl.g:261:1: constraintDefBody : ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) ;
    public final PddlParser.constraintDefBody_return constraintDefBody ( ) throws RecognitionException {
        PddlParser.constraintDefBody_return retval = new PddlParser.constraintDefBody_return();
        retval.start = input.LT(1);
        int constraintDefBody_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal129 = null;
        Token char_literal130 = null;
        Token char_literal131 = null;
        ParserRuleReturnScope goalDesc132 = null;

        Object string_literal129_tree = null;
        Object char_literal130_tree = null;
        Object char_literal131_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_75 = new RewriteRuleTokenStream(adaptor, "token 75");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 31)) {
                return retval;
            }

            // Pddl.g:262:2: ( ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) )
            // Pddl.g:262:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
            {
                // Pddl.g:262:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
                int alt33 = 2;
                int LA33_0 = input.LA(1);
                if ((LA33_0 == 75)) {
                    alt33 = 1;
                }
                switch (alt33) {
                    case 1: // Pddl.g:262:6: ':condition' ( ( '(' ')' ) | goalDesc )
                    {
                        string_literal129 = (Token) match(input, 75, FOLLOW_75_in_constraintDefBody1484);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_75.add(string_literal129);
                        }

                        // Pddl.g:262:19: ( ( '(' ')' ) | goalDesc )
                        int alt32 = 2;
                        int LA32_0 = input.LA(1);
                        if ((LA32_0 == 68)) {
                            int LA32_1 = input.LA(2);
                            if ((LA32_1 == 69)) {
                                alt32 = 1;
                            } else if ((LA32_1 == NAME || (LA32_1 >= 97 && LA32_1 <= 101) || LA32_1 == 108 || (LA32_1 >= 118 && LA32_1 <= 119) || LA32_1 == 122 || LA32_1 == 127 || (LA32_1 >= 129 && LA32_1 <= 130))) {
                                alt32 = 2;
                            } else {
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                int nvaeMark = input.mark();
                                try {
                                    input.consume();
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 32, 1, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                            }

                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            NoViableAltException nvae
                                    = new NoViableAltException("", 32, 0, input);
                            throw nvae;
                        }

                        switch (alt32) {
                            case 1: // Pddl.g:262:20: ( '(' ')' )
                            {
                                // Pddl.g:262:20: ( '(' ')' )
                                // Pddl.g:262:21: '(' ')'
                                {
                                    char_literal130 = (Token) match(input, 68, FOLLOW_68_in_constraintDefBody1488);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal130);
                                    }

                                    char_literal131 = (Token) match(input, 69, FOLLOW_69_in_constraintDefBody1490);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_69.add(char_literal131);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:262:32: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_constraintDefBody1495);
                                goalDesc132 = goalDesc();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_goalDesc.add(goalDesc132.getTree());
                                }
                            }
                            break;

                        }

                    }
                    break;

                }

                // AST REWRITE
                // elements: goalDesc
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 263:4: -> ^( PRECONDITION ( goalDesc )? )
                    {
                        // Pddl.g:263:7: ^( PRECONDITION ( goalDesc )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
                            // Pddl.g:263:22: ( goalDesc )?
                            if (stream_goalDesc.hasNext()) {
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                            }
                            stream_goalDesc.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 31, constraintDefBody_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "goalDesc"
    // Pddl.g:278:1: goalDesc : ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) );
    public final PddlParser.goalDesc_return goalDesc ( ) throws RecognitionException {
        PddlParser.goalDesc_return retval = new PddlParser.goalDesc_return();
        retval.start = input.LT(1);
        int goalDesc_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal134 = null;
        Token string_literal135 = null;
        Token char_literal137 = null;
        Token char_literal138 = null;
        Token string_literal139 = null;
        Token char_literal141 = null;
        Token char_literal142 = null;
        Token string_literal143 = null;
        Token char_literal145 = null;
        Token char_literal146 = null;
        Token string_literal147 = null;
        Token char_literal149 = null;
        Token char_literal150 = null;
        Token string_literal151 = null;
        Token char_literal154 = null;
        Token char_literal155 = null;
        Token string_literal156 = null;
        Token char_literal157 = null;
        Token char_literal159 = null;
        Token char_literal161 = null;
        Token char_literal162 = null;
        Token string_literal163 = null;
        Token char_literal164 = null;
        Token char_literal166 = null;
        Token char_literal168 = null;
        ParserRuleReturnScope atomicTermFormula133 = null;
        ParserRuleReturnScope goalDesc136 = null;
        ParserRuleReturnScope goalDesc140 = null;
        ParserRuleReturnScope goalDesc144 = null;
        ParserRuleReturnScope goalDesc148 = null;
        ParserRuleReturnScope goalDesc152 = null;
        ParserRuleReturnScope goalDesc153 = null;
        ParserRuleReturnScope typedVariableList158 = null;
        ParserRuleReturnScope goalDesc160 = null;
        ParserRuleReturnScope typedVariableList165 = null;
        ParserRuleReturnScope goalDesc167 = null;
        ParserRuleReturnScope fComp169 = null;
        ParserRuleReturnScope equality170 = null;

        Object char_literal134_tree = null;
        Object string_literal135_tree = null;
        Object char_literal137_tree = null;
        Object char_literal138_tree = null;
        Object string_literal139_tree = null;
        Object char_literal141_tree = null;
        Object char_literal142_tree = null;
        Object string_literal143_tree = null;
        Object char_literal145_tree = null;
        Object char_literal146_tree = null;
        Object string_literal147_tree = null;
        Object char_literal149_tree = null;
        Object char_literal150_tree = null;
        Object string_literal151_tree = null;
        Object char_literal154_tree = null;
        Object char_literal155_tree = null;
        Object string_literal156_tree = null;
        Object char_literal157_tree = null;
        Object char_literal159_tree = null;
        Object char_literal161_tree = null;
        Object char_literal162_tree = null;
        Object string_literal163_tree = null;
        Object char_literal164_tree = null;
        Object char_literal166_tree = null;
        Object char_literal168_tree = null;
        RewriteRuleTokenStream stream_122 = new RewriteRuleTokenStream(adaptor, "token 122");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_127 = new RewriteRuleTokenStream(adaptor, "token 127");
        RewriteRuleTokenStream stream_129 = new RewriteRuleTokenStream(adaptor, "token 129");
        RewriteRuleTokenStream stream_118 = new RewriteRuleTokenStream(adaptor, "token 118");
        RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(adaptor, "token 108");
        RewriteRuleTokenStream stream_119 = new RewriteRuleTokenStream(adaptor, "token 119");
        RewriteRuleTokenStream stream_130 = new RewriteRuleTokenStream(adaptor, "token 130");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_fComp = new RewriteRuleSubtreeStream(adaptor, "rule fComp");
        RewriteRuleSubtreeStream stream_equality = new RewriteRuleSubtreeStream(adaptor, "rule equality");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 32)) {
                return retval;
            }

            // Pddl.g:279:2: ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) )
            int alt37 = 10;
            int LA37_0 = input.LA(1);
            if ((LA37_0 == 68)) {
                switch (input.LA(2)) {
                    case 108: {
                        alt37 = 2;
                    }
                    break;
                    case 130: {
                        alt37 = 3;
                    }
                    break;
                    case 127: {
                        alt37 = 4;
                    }
                    break;
                    case 129: {
                        alt37 = 5;
                    }
                    break;
                    case 122: {
                        alt37 = 6;
                    }
                    break;
                    case 118: {
                        alt37 = 7;
                    }
                    break;
                    case 119: {
                        alt37 = 8;
                    }
                    break;
                    case 99: {
                        switch (input.LA(3)) {
                            case NAME: {
                                switch (input.LA(4)) {
                                    case NAME: {
                                        int LA37_14 = input.LA(5);
                                        if ((LA37_14 == 69)) {
                                            int LA37_15 = input.LA(6);
                                            if ((synpred50_Pddl())) {
                                                alt37 = 9;
                                            } else if ((true)) {
                                                alt37 = 10;
                                            }

                                        } else {
                                            if (state.backtracking > 0) {
                                                state.failed = true;
                                                return retval;
                                            }
                                            int nvaeMark = input.mark();
                                            try {
                                                for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
                                                    input.consume();
                                                }
                                                NoViableAltException nvae
                                                        = new NoViableAltException("", 37, 14, input);
                                                throw nvae;
                                            } finally {
                                                input.rewind(nvaeMark);
                                            }
                                        }

                                    }
                                    break;
                                    case NUMBER:
                                    case 67:
                                    case 68: {
                                        alt37 = 9;
                                    }
                                    break;
                                    case VARIABLE: {
                                        alt37 = 10;
                                    }
                                    break;
                                    default:
                                        if (state.backtracking > 0) {
                                            state.failed = true;
                                            return retval;
                                        }
                                        int nvaeMark = input.mark();
                                        try {
                                            for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
                                                input.consume();
                                            }
                                            NoViableAltException nvae
                                                    = new NoViableAltException("", 37, 12, input);
                                            throw nvae;
                                        } finally {
                                            input.rewind(nvaeMark);
                                        }
                                }
                            }
                            break;
                            case NUMBER:
                            case 67:
                            case 68: {
                                alt37 = 9;
                            }
                            break;
                            case VARIABLE: {
                                alt37 = 10;
                            }
                            break;
                            default:
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                int nvaeMark = input.mark();
                                try {
                                    for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                        input.consume();
                                    }
                                    NoViableAltException nvae
                                            = new NoViableAltException("", 37, 9, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                        }
                    }
                    break;
                    case NAME: {
                        alt37 = 1;
                    }
                    break;
                    case 97:
                    case 98:
                    case 100:
                    case 101: {
                        alt37 = 9;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 37, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 37, 0, input);
                throw nvae;
            }

            switch (alt37) {
                case 1: // Pddl.g:279:4: atomicTermFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicTermFormula_in_goalDesc1533);
                    atomicTermFormula133 = atomicTermFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, atomicTermFormula133.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:280:4: '(' 'and' ( goalDesc )* ')'
                {
                    char_literal134 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1538);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal134);
                    }

                    string_literal135 = (Token) match(input, 108, FOLLOW_108_in_goalDesc1540);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_108.add(string_literal135);
                    }

                    // Pddl.g:280:14: ( goalDesc )*
                    loop34:
                    while (true) {
                        int alt34 = 2;
                        int LA34_0 = input.LA(1);
                        if ((LA34_0 == 68)) {
                            alt34 = 1;
                        }

                        switch (alt34) {
                            case 1: // Pddl.g:280:14: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_goalDesc1542);
                                goalDesc136 = goalDesc();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_goalDesc.add(goalDesc136.getTree());
                                }
                            }
                            break;

                            default:
                                break loop34;
                        }
                    }

                    char_literal137 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1545);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal137);
                    }

                    // AST REWRITE
                    // elements: goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 281:12: -> ^( AND_GD ( goalDesc )* )
                        {
                            // Pddl.g:281:15: ^( AND_GD ( goalDesc )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_GD, "AND_GD"), root_1);
                                // Pddl.g:281:24: ( goalDesc )*
                                while (stream_goalDesc.hasNext()) {
                                    adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                }
                                stream_goalDesc.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:282:4: '(' 'or' ( goalDesc )* ')'
                {
                    char_literal138 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1570);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal138);
                    }

                    string_literal139 = (Token) match(input, 130, FOLLOW_130_in_goalDesc1572);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_130.add(string_literal139);
                    }

                    // Pddl.g:282:13: ( goalDesc )*
                    loop35:
                    while (true) {
                        int alt35 = 2;
                        int LA35_0 = input.LA(1);
                        if ((LA35_0 == 68)) {
                            alt35 = 1;
                        }

                        switch (alt35) {
                            case 1: // Pddl.g:282:13: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_goalDesc1574);
                                goalDesc140 = goalDesc();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_goalDesc.add(goalDesc140.getTree());
                                }
                            }
                            break;

                            default:
                                break loop35;
                        }
                    }

                    char_literal141 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1577);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal141);
                    }

                    // AST REWRITE
                    // elements: goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 283:12: -> ^( OR_GD ( goalDesc )* )
                        {
                            // Pddl.g:283:15: ^( OR_GD ( goalDesc )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OR_GD, "OR_GD"), root_1);
                                // Pddl.g:283:23: ( goalDesc )*
                                while (stream_goalDesc.hasNext()) {
                                    adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                }
                                stream_goalDesc.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 4: // Pddl.g:284:4: '(' 'not' goalDesc ')'
                {
                    char_literal142 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1602);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal142);
                    }

                    string_literal143 = (Token) match(input, 127, FOLLOW_127_in_goalDesc1604);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_127.add(string_literal143);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1606);
                    goalDesc144 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc144.getTree());
                    }
                    char_literal145 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1608);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal145);
                    }

                    // AST REWRITE
                    // elements: goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 285:12: -> ^( NOT_GD goalDesc )
                        {
                            // Pddl.g:285:15: ^( NOT_GD goalDesc )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(NOT_GD, "NOT_GD"), root_1);
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 5: // Pddl.g:286:4: '(' 'oneof' ( goalDesc )* ')'
                {
                    char_literal146 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1632);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal146);
                    }

                    string_literal147 = (Token) match(input, 129, FOLLOW_129_in_goalDesc1634);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_129.add(string_literal147);
                    }

                    // Pddl.g:286:17: ( goalDesc )*
                    loop36:
                    while (true) {
                        int alt36 = 2;
                        int LA36_0 = input.LA(1);
                        if ((LA36_0 == 68)) {
                            alt36 = 1;
                        }

                        switch (alt36) {
                            case 1: // Pddl.g:286:17: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_goalDesc1637);
                                goalDesc148 = goalDesc();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_goalDesc.add(goalDesc148.getTree());
                                }
                            }
                            break;

                            default:
                                break loop36;
                        }
                    }

                    char_literal149 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1640);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal149);
                    }

                    // AST REWRITE
                    // elements: goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 286:32: -> ^( ONEOF ( goalDesc )* )
                        {
                            // Pddl.g:286:35: ^( ONEOF ( goalDesc )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ONEOF, "ONEOF"), root_1);
                                // Pddl.g:286:43: ( goalDesc )*
                                while (stream_goalDesc.hasNext()) {
                                    adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                }
                                stream_goalDesc.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 6: // Pddl.g:287:4: '(' 'imply' goalDesc goalDesc ')'
                {
                    char_literal150 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1655);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal150);
                    }

                    string_literal151 = (Token) match(input, 122, FOLLOW_122_in_goalDesc1657);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_122.add(string_literal151);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1659);
                    goalDesc152 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc152.getTree());
                    }
                    pushFollow(FOLLOW_goalDesc_in_goalDesc1661);
                    goalDesc153 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc153.getTree());
                    }
                    char_literal154 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1663);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal154);
                    }

                    // AST REWRITE
                    // elements: goalDesc, goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 288:12: -> ^( IMPLY_GD goalDesc goalDesc )
                        {
                            // Pddl.g:288:15: ^( IMPLY_GD goalDesc goalDesc )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(IMPLY_GD, "IMPLY_GD"), root_1);
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 7: // Pddl.g:289:4: '(' 'exists' '(' typedVariableList ')' goalDesc ')'
                {
                    char_literal155 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1689);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal155);
                    }

                    string_literal156 = (Token) match(input, 118, FOLLOW_118_in_goalDesc1691);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_118.add(string_literal156);
                    }

                    char_literal157 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1693);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal157);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1695);
                    typedVariableList158 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList158.getTree());
                    }
                    char_literal159 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1697);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal159);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1699);
                    goalDesc160 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc160.getTree());
                    }
                    char_literal161 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1701);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal161);
                    }

                    // AST REWRITE
                    // elements: typedVariableList, goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 290:12: -> ^( EXISTS_GD typedVariableList goalDesc )
                        {
                            // Pddl.g:290:15: ^( EXISTS_GD typedVariableList goalDesc )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EXISTS_GD, "EXISTS_GD"), root_1);
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 8: // Pddl.g:291:4: '(' 'forall' '(' typedVariableList ')' goalDesc ')'
                {
                    char_literal162 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1727);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal162);
                    }

                    string_literal163 = (Token) match(input, 119, FOLLOW_119_in_goalDesc1729);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_119.add(string_literal163);
                    }

                    char_literal164 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1731);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal164);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1733);
                    typedVariableList165 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList165.getTree());
                    }
                    char_literal166 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1735);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal166);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1737);
                    goalDesc167 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc167.getTree());
                    }
                    char_literal168 = (Token) match(input, 69, FOLLOW_69_in_goalDesc1739);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal168);
                    }

                    // AST REWRITE
                    // elements: typedVariableList, goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 292:12: -> ^( FORALL_GD typedVariableList goalDesc )
                        {
                            // Pddl.g:292:15: ^( FORALL_GD typedVariableList goalDesc )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FORALL_GD, "FORALL_GD"), root_1);
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 9: // Pddl.g:293:7: fComp
                {
                    pushFollow(FOLLOW_fComp_in_goalDesc1768);
                    fComp169 = fComp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fComp.add(fComp169.getTree());
                    }
                    // AST REWRITE
                    // elements: fComp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 294:15: -> ^( COMPARISON_GD fComp )
                        {
                            // Pddl.g:294:18: ^( COMPARISON_GD fComp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COMPARISON_GD, "COMPARISON_GD"), root_1);
                                adaptor.addChild(root_1, stream_fComp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 10: // Pddl.g:295:4: equality
                {
                    pushFollow(FOLLOW_equality_in_goalDesc1795);
                    equality170 = equality();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_equality.add(equality170.getTree());
                    }
                    // AST REWRITE
                    // elements: equality
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 296:6: -> ^( EQUALITY_CON equality )
                        {
                            // Pddl.g:296:9: ^( EQUALITY_CON equality )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EQUALITY_CON, "EQUALITY_CON"), root_1);
                                adaptor.addChild(root_1, stream_equality.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 32, goalDesc_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "equality"
    // Pddl.g:299:1: equality : '(' ! '=' term term ')' !;
    public final PddlParser.equality_return equality ( ) throws RecognitionException {
        PddlParser.equality_return retval = new PddlParser.equality_return();
        retval.start = input.LT(1);
        int equality_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal171 = null;
        Token char_literal172 = null;
        Token char_literal175 = null;
        ParserRuleReturnScope term173 = null;
        ParserRuleReturnScope term174 = null;

        Object char_literal171_tree = null;
        Object char_literal172_tree = null;
        Object char_literal175_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 33)) {
                return retval;
            }

            // Pddl.g:300:2: ( '(' ! '=' term term ')' !)
            // Pddl.g:300:4: '(' ! '=' term term ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal171 = (Token) match(input, 68, FOLLOW_68_in_equality1823);
                if (state.failed) {
                    return retval;
                }
                char_literal172 = (Token) match(input, 99, FOLLOW_99_in_equality1826);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal172_tree = (Object) adaptor.create(char_literal172);
                    adaptor.addChild(root_0, char_literal172_tree);
                }

                pushFollow(FOLLOW_term_in_equality1828);
                term173 = term();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, term173.getTree());
                }

                pushFollow(FOLLOW_term_in_equality1830);
                term174 = term();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, term174.getTree());
                }

                char_literal175 = (Token) match(input, 69, FOLLOW_69_in_equality1832);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 33, equality_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fComp"
    // Pddl.g:302:1: fComp : '(' ! binaryComp fExp fExp ')' !;
    public final PddlParser.fComp_return fComp ( ) throws RecognitionException {
        PddlParser.fComp_return retval = new PddlParser.fComp_return();
        retval.start = input.LT(1);
        int fComp_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal176 = null;
        Token char_literal180 = null;
        ParserRuleReturnScope binaryComp177 = null;
        ParserRuleReturnScope fExp178 = null;
        ParserRuleReturnScope fExp179 = null;

        Object char_literal176_tree = null;
        Object char_literal180_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 34)) {
                return retval;
            }

            // Pddl.g:303:2: ( '(' ! binaryComp fExp fExp ')' !)
            // Pddl.g:303:4: '(' ! binaryComp fExp fExp ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal176 = (Token) match(input, 68, FOLLOW_68_in_fComp1843);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_binaryComp_in_fComp1846);
                binaryComp177 = binaryComp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, binaryComp177.getTree());
                }

                pushFollow(FOLLOW_fExp_in_fComp1848);
                fExp178 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp178.getTree());
                }

                pushFollow(FOLLOW_fExp_in_fComp1850);
                fExp179 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp179.getTree());
                }

                char_literal180 = (Token) match(input, 69, FOLLOW_69_in_fComp1852);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 34, fComp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "atomicTermFormula"
    // Pddl.g:306:1: atomicTermFormula : '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) ;
    public final PddlParser.atomicTermFormula_return atomicTermFormula ( ) throws RecognitionException {
        PddlParser.atomicTermFormula_return retval = new PddlParser.atomicTermFormula_return();
        retval.start = input.LT(1);
        int atomicTermFormula_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal181 = null;
        Token char_literal184 = null;
        ParserRuleReturnScope predicate182 = null;
        ParserRuleReturnScope term183 = null;

        Object char_literal181_tree = null;
        Object char_literal184_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleSubtreeStream stream_predicate = new RewriteRuleSubtreeStream(adaptor, "rule predicate");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(adaptor, "rule term");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 35)) {
                return retval;
            }

            // Pddl.g:307:2: ( '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) )
            // Pddl.g:307:4: '(' predicate ( term )* ')'
            {
                char_literal181 = (Token) match(input, 68, FOLLOW_68_in_atomicTermFormula1864);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal181);
                }

                pushFollow(FOLLOW_predicate_in_atomicTermFormula1866);
                predicate182 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_predicate.add(predicate182.getTree());
                }
                // Pddl.g:307:18: ( term )*
                loop38:
                while (true) {
                    int alt38 = 2;
                    int LA38_0 = input.LA(1);
                    if ((LA38_0 == NAME || LA38_0 == VARIABLE)) {
                        alt38 = 1;
                    }

                    switch (alt38) {
                        case 1: // Pddl.g:307:18: term
                        {
                            pushFollow(FOLLOW_term_in_atomicTermFormula1868);
                            term183 = term();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_term.add(term183.getTree());
                            }
                        }
                        break;

                        default:
                            break loop38;
                    }
                }

                char_literal184 = (Token) match(input, 69, FOLLOW_69_in_atomicTermFormula1871);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal184);
                }

                // AST REWRITE
                // elements: term, predicate
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 307:28: -> ^( PRED_HEAD predicate ( term )* )
                    {
                        // Pddl.g:307:31: ^( PRED_HEAD predicate ( term )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRED_HEAD, "PRED_HEAD"), root_1);
                            adaptor.addChild(root_1, stream_predicate.nextTree());
                            // Pddl.g:307:53: ( term )*
                            while (stream_term.hasNext()) {
                                adaptor.addChild(root_1, stream_term.nextTree());
                            }
                            stream_term.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 35, atomicTermFormula_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "term"
    // Pddl.g:310:1: term : ( NAME | VARIABLE );
    public final PddlParser.term_return term ( ) throws RecognitionException {
        PddlParser.term_return retval = new PddlParser.term_return();
        retval.start = input.LT(1);
        int term_StartIndex = input.index();

        Object root_0 = null;

        Token set185 = null;

        Object set185_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 36)) {
                return retval;
            }

            // Pddl.g:310:6: ( NAME | VARIABLE )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set185 = input.LT(1);
                if (input.LA(1) == NAME || input.LA(1) == VARIABLE) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set185));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 36, term_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "durativeActionDef"
    // Pddl.g:314:1: durativeActionDef : '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) ;
    public final PddlParser.durativeActionDef_return durativeActionDef ( ) throws RecognitionException {
        PddlParser.durativeActionDef_return retval = new PddlParser.durativeActionDef_return();
        retval.start = input.LT(1);
        int durativeActionDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal186 = null;
        Token string_literal187 = null;
        Token string_literal189 = null;
        Token char_literal190 = null;
        Token char_literal192 = null;
        Token char_literal194 = null;
        ParserRuleReturnScope actionSymbol188 = null;
        ParserRuleReturnScope typedVariableList191 = null;
        ParserRuleReturnScope daDefBody193 = null;

        Object char_literal186_tree = null;
        Object string_literal187_tree = null;
        Object string_literal189_tree = null;
        Object char_literal190_tree = null;
        Object char_literal192_tree = null;
        Object char_literal194_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleTokenStream stream_82 = new RewriteRuleTokenStream(adaptor, "token 82");
        RewriteRuleSubtreeStream stream_daDefBody = new RewriteRuleSubtreeStream(adaptor, "rule daDefBody");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 37)) {
                return retval;
            }

            // Pddl.g:315:2: ( '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) )
            // Pddl.g:315:4: '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')'
            {
                char_literal186 = (Token) match(input, 68, FOLLOW_68_in_durativeActionDef1908);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal186);
                }

                string_literal187 = (Token) match(input, 82, FOLLOW_82_in_durativeActionDef1910);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_82.add(string_literal187);
                }

                pushFollow(FOLLOW_actionSymbol_in_durativeActionDef1912);
                actionSymbol188 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol188.getTree());
                }
                string_literal189 = (Token) match(input, 91, FOLLOW_91_in_durativeActionDef1921);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_91.add(string_literal189);
                }

                char_literal190 = (Token) match(input, 68, FOLLOW_68_in_durativeActionDef1924);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal190);
                }

                // Pddl.g:316:27: ( typedVariableList )?
                int alt39 = 2;
                int LA39_0 = input.LA(1);
                if ((LA39_0 == VARIABLE)) {
                    alt39 = 1;
                } else if ((LA39_0 == 69)) {
                    int LA39_2 = input.LA(2);
                    if ((synpred53_Pddl())) {
                        alt39 = 1;
                    }
                }
                switch (alt39) {
                    case 1: // Pddl.g:316:28: typedVariableList
                    {
                        pushFollow(FOLLOW_typedVariableList_in_durativeActionDef1927);
                        typedVariableList191 = typedVariableList();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_typedVariableList.add(typedVariableList191.getTree());
                        }
                    }
                    break;

                }

                char_literal192 = (Token) match(input, 69, FOLLOW_69_in_durativeActionDef1931);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal192);
                }

                pushFollow(FOLLOW_daDefBody_in_durativeActionDef1944);
                daDefBody193 = daDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_daDefBody.add(daDefBody193.getTree());
                }
                char_literal194 = (Token) match(input, 69, FOLLOW_69_in_durativeActionDef1946);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal194);
                }

                // AST REWRITE
                // elements: actionSymbol, daDefBody, typedVariableList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 318:8: -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
                    {
                        // Pddl.g:318:11: ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DURATIVE_ACTION, "DURATIVE_ACTION"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                            adaptor.addChild(root_1, stream_daDefBody.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 37, durativeActionDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "daDefBody"
    // Pddl.g:321:1: daDefBody : ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) );
    public final PddlParser.daDefBody_return daDefBody ( ) throws RecognitionException {
        PddlParser.daDefBody_return retval = new PddlParser.daDefBody_return();
        retval.start = input.LT(1);
        int daDefBody_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal195 = null;
        Token string_literal197 = null;
        Token char_literal198 = null;
        Token char_literal199 = null;
        Token string_literal201 = null;
        Token char_literal202 = null;
        Token char_literal203 = null;
        ParserRuleReturnScope durationConstraint196 = null;
        ParserRuleReturnScope daGD200 = null;
        ParserRuleReturnScope daEffect204 = null;

        Object string_literal195_tree = null;
        Object string_literal197_tree = null;
        Object char_literal198_tree = null;
        Object char_literal199_tree = null;
        Object string_literal201_tree = null;
        Object char_literal202_tree = null;
        Object char_literal203_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 38)) {
                return retval;
            }

            // Pddl.g:322:2: ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) )
            int alt42 = 3;
            switch (input.LA(1)) {
                case 81: {
                    alt42 = 1;
                }
                break;
                case 75: {
                    alt42 = 2;
                }
                break;
                case 83: {
                    alt42 = 3;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 42, 0, input);
                    throw nvae;
            }
            switch (alt42) {
                case 1: // Pddl.g:322:4: ':duration' durationConstraint
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal195 = (Token) match(input, 81, FOLLOW_81_in_daDefBody1979);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal195_tree = (Object) adaptor.create(string_literal195);
                        adaptor.addChild(root_0, string_literal195_tree);
                    }

                    pushFollow(FOLLOW_durationConstraint_in_daDefBody1981);
                    durationConstraint196 = durationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durationConstraint196.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:323:4: ':condition' ( ( '(' ')' ) | daGD )
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal197 = (Token) match(input, 75, FOLLOW_75_in_daDefBody1986);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal197_tree = (Object) adaptor.create(string_literal197);
                        adaptor.addChild(root_0, string_literal197_tree);
                    }

                    // Pddl.g:323:17: ( ( '(' ')' ) | daGD )
                    int alt40 = 2;
                    int LA40_0 = input.LA(1);
                    if ((LA40_0 == 68)) {
                        int LA40_1 = input.LA(2);
                        if ((LA40_1 == 69)) {
                            alt40 = 1;
                        } else if ((LA40_1 == 108 || LA40_1 == 110 || LA40_1 == 119 || (LA40_1 >= 131 && LA40_1 <= 132))) {
                            alt40 = 2;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae
                                        = new NoViableAltException("", 40, 1, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        NoViableAltException nvae
                                = new NoViableAltException("", 40, 0, input);
                        throw nvae;
                    }

                    switch (alt40) {
                        case 1: // Pddl.g:323:18: ( '(' ')' )
                        {
                            // Pddl.g:323:18: ( '(' ')' )
                            // Pddl.g:323:19: '(' ')'
                            {
                                char_literal198 = (Token) match(input, 68, FOLLOW_68_in_daDefBody1990);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal198_tree = (Object) adaptor.create(char_literal198);
                                    adaptor.addChild(root_0, char_literal198_tree);
                                }

                                char_literal199 = (Token) match(input, 69, FOLLOW_69_in_daDefBody1992);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal199_tree = (Object) adaptor.create(char_literal199);
                                    adaptor.addChild(root_0, char_literal199_tree);
                                }

                            }

                        }
                        break;
                        case 2: // Pddl.g:323:30: daGD
                        {
                            pushFollow(FOLLOW_daGD_in_daDefBody1997);
                            daGD200 = daGD();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                adaptor.addChild(root_0, daGD200.getTree());
                            }

                        }
                        break;

                    }

                }
                break;
                case 3: // Pddl.g:324:7: ':effect' ( ( '(' ')' ) | daEffect )
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal201 = (Token) match(input, 83, FOLLOW_83_in_daDefBody2006);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal201_tree = (Object) adaptor.create(string_literal201);
                        adaptor.addChild(root_0, string_literal201_tree);
                    }

                    // Pddl.g:324:17: ( ( '(' ')' ) | daEffect )
                    int alt41 = 2;
                    int LA41_0 = input.LA(1);
                    if ((LA41_0 == 68)) {
                        int LA41_1 = input.LA(2);
                        if ((LA41_1 == 69)) {
                            alt41 = 1;
                        } else if (((LA41_1 >= 108 && LA41_1 <= 110) || LA41_1 == 113 || LA41_1 == 119 || LA41_1 == 123 || (LA41_1 >= 134 && LA41_1 <= 135) || LA41_1 == 142)) {
                            alt41 = 2;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae
                                        = new NoViableAltException("", 41, 1, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }

                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        NoViableAltException nvae
                                = new NoViableAltException("", 41, 0, input);
                        throw nvae;
                    }

                    switch (alt41) {
                        case 1: // Pddl.g:324:18: ( '(' ')' )
                        {
                            // Pddl.g:324:18: ( '(' ')' )
                            // Pddl.g:324:19: '(' ')'
                            {
                                char_literal202 = (Token) match(input, 68, FOLLOW_68_in_daDefBody2010);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal202_tree = (Object) adaptor.create(char_literal202);
                                    adaptor.addChild(root_0, char_literal202_tree);
                                }

                                char_literal203 = (Token) match(input, 69, FOLLOW_69_in_daDefBody2012);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal203_tree = (Object) adaptor.create(char_literal203);
                                    adaptor.addChild(root_0, char_literal203_tree);
                                }

                            }

                        }
                        break;
                        case 2: // Pddl.g:324:30: daEffect
                        {
                            pushFollow(FOLLOW_daEffect_in_daDefBody2017);
                            daEffect204 = daEffect();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                adaptor.addChild(root_0, daEffect204.getTree());
                            }

                        }
                        break;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 38, daDefBody_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "daGD"
    // Pddl.g:327:1: daGD : ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' );
    public final PddlParser.daGD_return daGD ( ) throws RecognitionException {
        PddlParser.daGD_return retval = new PddlParser.daGD_return();
        retval.start = input.LT(1);
        int daGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal206 = null;
        Token string_literal207 = null;
        Token char_literal209 = null;
        Token char_literal210 = null;
        Token string_literal211 = null;
        Token char_literal212 = null;
        Token char_literal214 = null;
        Token char_literal216 = null;
        ParserRuleReturnScope prefTimedGD205 = null;
        ParserRuleReturnScope daGD208 = null;
        ParserRuleReturnScope typedVariableList213 = null;
        ParserRuleReturnScope daGD215 = null;

        Object char_literal206_tree = null;
        Object string_literal207_tree = null;
        Object char_literal209_tree = null;
        Object char_literal210_tree = null;
        Object string_literal211_tree = null;
        Object char_literal212_tree = null;
        Object char_literal214_tree = null;
        Object char_literal216_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 39)) {
                return retval;
            }

            // Pddl.g:328:2: ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' )
            int alt44 = 3;
            int LA44_0 = input.LA(1);
            if ((LA44_0 == 68)) {
                switch (input.LA(2)) {
                    case 110:
                    case 131:
                    case 132: {
                        alt44 = 1;
                    }
                    break;
                    case 108: {
                        alt44 = 2;
                    }
                    break;
                    case 119: {
                        alt44 = 3;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 44, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 44, 0, input);
                throw nvae;
            }

            switch (alt44) {
                case 1: // Pddl.g:328:4: prefTimedGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_prefTimedGD_in_daGD2032);
                    prefTimedGD205 = prefTimedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, prefTimedGD205.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:329:4: '(' 'and' ( daGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal206 = (Token) match(input, 68, FOLLOW_68_in_daGD2037);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal206_tree = (Object) adaptor.create(char_literal206);
                        adaptor.addChild(root_0, char_literal206_tree);
                    }

                    string_literal207 = (Token) match(input, 108, FOLLOW_108_in_daGD2039);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal207_tree = (Object) adaptor.create(string_literal207);
                        adaptor.addChild(root_0, string_literal207_tree);
                    }

                    // Pddl.g:329:14: ( daGD )*
                    loop43:
                    while (true) {
                        int alt43 = 2;
                        int LA43_0 = input.LA(1);
                        if ((LA43_0 == 68)) {
                            alt43 = 1;
                        }

                        switch (alt43) {
                            case 1: // Pddl.g:329:14: daGD
                            {
                                pushFollow(FOLLOW_daGD_in_daGD2041);
                                daGD208 = daGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, daGD208.getTree());
                                }

                            }
                            break;

                            default:
                                break loop43;
                        }
                    }

                    char_literal209 = (Token) match(input, 69, FOLLOW_69_in_daGD2044);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal209_tree = (Object) adaptor.create(char_literal209);
                        adaptor.addChild(root_0, char_literal209_tree);
                    }

                }
                break;
                case 3: // Pddl.g:330:4: '(' 'forall' '(' typedVariableList ')' daGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal210 = (Token) match(input, 68, FOLLOW_68_in_daGD2049);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal210_tree = (Object) adaptor.create(char_literal210);
                        adaptor.addChild(root_0, char_literal210_tree);
                    }

                    string_literal211 = (Token) match(input, 119, FOLLOW_119_in_daGD2051);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal211_tree = (Object) adaptor.create(string_literal211);
                        adaptor.addChild(root_0, string_literal211_tree);
                    }

                    char_literal212 = (Token) match(input, 68, FOLLOW_68_in_daGD2053);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal212_tree = (Object) adaptor.create(char_literal212);
                        adaptor.addChild(root_0, char_literal212_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daGD2055);
                    typedVariableList213 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList213.getTree());
                    }

                    char_literal214 = (Token) match(input, 69, FOLLOW_69_in_daGD2057);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal214_tree = (Object) adaptor.create(char_literal214);
                        adaptor.addChild(root_0, char_literal214_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daGD2059);
                    daGD215 = daGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daGD215.getTree());
                    }

                    char_literal216 = (Token) match(input, 69, FOLLOW_69_in_daGD2061);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal216_tree = (Object) adaptor.create(char_literal216);
                        adaptor.addChild(root_0, char_literal216_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 39, daGD_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "prefTimedGD"
    // Pddl.g:333:1: prefTimedGD : ( timedGD | '(' 'preference' ( NAME )? timedGD ')' );
    public final PddlParser.prefTimedGD_return prefTimedGD ( ) throws RecognitionException {
        PddlParser.prefTimedGD_return retval = new PddlParser.prefTimedGD_return();
        retval.start = input.LT(1);
        int prefTimedGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal218 = null;
        Token string_literal219 = null;
        Token NAME220 = null;
        Token char_literal222 = null;
        ParserRuleReturnScope timedGD217 = null;
        ParserRuleReturnScope timedGD221 = null;

        Object char_literal218_tree = null;
        Object string_literal219_tree = null;
        Object NAME220_tree = null;
        Object char_literal222_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 40)) {
                return retval;
            }

            // Pddl.g:334:2: ( timedGD | '(' 'preference' ( NAME )? timedGD ')' )
            int alt46 = 2;
            int LA46_0 = input.LA(1);
            if ((LA46_0 == 68)) {
                int LA46_1 = input.LA(2);
                if ((LA46_1 == 110 || LA46_1 == 131)) {
                    alt46 = 1;
                } else if ((LA46_1 == 132)) {
                    alt46 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 46, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 46, 0, input);
                throw nvae;
            }

            switch (alt46) {
                case 1: // Pddl.g:334:4: timedGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2072);
                    timedGD217 = timedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedGD217.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:335:4: '(' 'preference' ( NAME )? timedGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal218 = (Token) match(input, 68, FOLLOW_68_in_prefTimedGD2077);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal218_tree = (Object) adaptor.create(char_literal218);
                        adaptor.addChild(root_0, char_literal218_tree);
                    }

                    string_literal219 = (Token) match(input, 132, FOLLOW_132_in_prefTimedGD2079);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal219_tree = (Object) adaptor.create(string_literal219);
                        adaptor.addChild(root_0, string_literal219_tree);
                    }

                    // Pddl.g:335:21: ( NAME )?
                    int alt45 = 2;
                    int LA45_0 = input.LA(1);
                    if ((LA45_0 == NAME)) {
                        alt45 = 1;
                    }
                    switch (alt45) {
                        case 1: // Pddl.g:335:21: NAME
                        {
                            NAME220 = (Token) match(input, NAME, FOLLOW_NAME_in_prefTimedGD2081);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                NAME220_tree = (Object) adaptor.create(NAME220);
                                adaptor.addChild(root_0, NAME220_tree);
                            }

                        }
                        break;

                    }

                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2084);
                    timedGD221 = timedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedGD221.getTree());
                    }

                    char_literal222 = (Token) match(input, 69, FOLLOW_69_in_prefTimedGD2086);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal222_tree = (Object) adaptor.create(char_literal222);
                        adaptor.addChild(root_0, char_literal222_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 40, prefTimedGD_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "timedGD"
    // Pddl.g:338:1: timedGD : ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' );
    public final PddlParser.timedGD_return timedGD ( ) throws RecognitionException {
        PddlParser.timedGD_return retval = new PddlParser.timedGD_return();
        retval.start = input.LT(1);
        int timedGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal223 = null;
        Token string_literal224 = null;
        Token char_literal227 = null;
        Token char_literal228 = null;
        Token string_literal229 = null;
        Token char_literal232 = null;
        ParserRuleReturnScope timeSpecifier225 = null;
        ParserRuleReturnScope goalDesc226 = null;
        ParserRuleReturnScope interval230 = null;
        ParserRuleReturnScope goalDesc231 = null;

        Object char_literal223_tree = null;
        Object string_literal224_tree = null;
        Object char_literal227_tree = null;
        Object char_literal228_tree = null;
        Object string_literal229_tree = null;
        Object char_literal232_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 41)) {
                return retval;
            }

            // Pddl.g:339:2: ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' )
            int alt47 = 2;
            int LA47_0 = input.LA(1);
            if ((LA47_0 == 68)) {
                int LA47_1 = input.LA(2);
                if ((LA47_1 == 110)) {
                    alt47 = 1;
                } else if ((LA47_1 == 131)) {
                    alt47 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 47, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 47, 0, input);
                throw nvae;
            }

            switch (alt47) {
                case 1: // Pddl.g:339:4: '(' 'at' timeSpecifier goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal223 = (Token) match(input, 68, FOLLOW_68_in_timedGD2097);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal223_tree = (Object) adaptor.create(char_literal223);
                        adaptor.addChild(root_0, char_literal223_tree);
                    }

                    string_literal224 = (Token) match(input, 110, FOLLOW_110_in_timedGD2099);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal224_tree = (Object) adaptor.create(string_literal224);
                        adaptor.addChild(root_0, string_literal224_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedGD2101);
                    timeSpecifier225 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier225.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_timedGD2103);
                    goalDesc226 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc226.getTree());
                    }

                    char_literal227 = (Token) match(input, 69, FOLLOW_69_in_timedGD2105);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal227_tree = (Object) adaptor.create(char_literal227);
                        adaptor.addChild(root_0, char_literal227_tree);
                    }

                }
                break;
                case 2: // Pddl.g:340:4: '(' 'over' interval goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal228 = (Token) match(input, 68, FOLLOW_68_in_timedGD2110);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal228_tree = (Object) adaptor.create(char_literal228);
                        adaptor.addChild(root_0, char_literal228_tree);
                    }

                    string_literal229 = (Token) match(input, 131, FOLLOW_131_in_timedGD2112);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal229_tree = (Object) adaptor.create(string_literal229);
                        adaptor.addChild(root_0, string_literal229_tree);
                    }

                    pushFollow(FOLLOW_interval_in_timedGD2114);
                    interval230 = interval();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, interval230.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_timedGD2116);
                    goalDesc231 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc231.getTree());
                    }

                    char_literal232 = (Token) match(input, 69, FOLLOW_69_in_timedGD2118);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal232_tree = (Object) adaptor.create(char_literal232);
                        adaptor.addChild(root_0, char_literal232_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 41, timedGD_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "timeSpecifier"
    // Pddl.g:343:1: timeSpecifier : ( 'start' | 'end' );
    public final PddlParser.timeSpecifier_return timeSpecifier ( ) throws RecognitionException {
        PddlParser.timeSpecifier_return retval = new PddlParser.timeSpecifier_return();
        retval.start = input.LT(1);
        int timeSpecifier_StartIndex = input.index();

        Object root_0 = null;

        Token set233 = null;

        Object set233_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 42)) {
                return retval;
            }

            // Pddl.g:343:15: ( 'start' | 'end' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set233 = input.LT(1);
                if (input.LA(1) == 117 || input.LA(1) == 140) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set233));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 42, timeSpecifier_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "interval"
    // Pddl.g:344:1: interval : 'all' ;
    public final PddlParser.interval_return interval ( ) throws RecognitionException {
        PddlParser.interval_return retval = new PddlParser.interval_return();
        retval.start = input.LT(1);
        int interval_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal234 = null;

        Object string_literal234_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 43)) {
                return retval;
            }

            // Pddl.g:344:10: ( 'all' )
            // Pddl.g:344:12: 'all'
            {
                root_0 = (Object) adaptor.nil();

                string_literal234 = (Token) match(input, 105, FOLLOW_105_in_interval2140);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal234_tree = (Object) adaptor.create(string_literal234);
                    adaptor.addChild(root_0, string_literal234_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 43, interval_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "derivedDef"
    // Pddl.g:348:1: derivedDef : '(' ! ':derived' ^ typedVariableList goalDesc ')' !;
    public final PddlParser.derivedDef_return derivedDef ( ) throws RecognitionException {
        PddlParser.derivedDef_return retval = new PddlParser.derivedDef_return();
        retval.start = input.LT(1);
        int derivedDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal235 = null;
        Token string_literal236 = null;
        Token char_literal239 = null;
        ParserRuleReturnScope typedVariableList237 = null;
        ParserRuleReturnScope goalDesc238 = null;

        Object char_literal235_tree = null;
        Object string_literal236_tree = null;
        Object char_literal239_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 44)) {
                return retval;
            }

            // Pddl.g:349:2: ( '(' ! ':derived' ^ typedVariableList goalDesc ')' !)
            // Pddl.g:349:4: '(' ! ':derived' ^ typedVariableList goalDesc ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal235 = (Token) match(input, 68, FOLLOW_68_in_derivedDef2153);
                if (state.failed) {
                    return retval;
                }
                string_literal236 = (Token) match(input, 79, FOLLOW_79_in_derivedDef2156);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal236_tree = (Object) adaptor.create(string_literal236);
                    root_0 = (Object) adaptor.becomeRoot(string_literal236_tree, root_0);
                }

                pushFollow(FOLLOW_typedVariableList_in_derivedDef2159);
                typedVariableList237 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList237.getTree());
                }

                pushFollow(FOLLOW_goalDesc_in_derivedDef2161);
                goalDesc238 = goalDesc();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, goalDesc238.getTree());
                }

                char_literal239 = (Token) match(input, 69, FOLLOW_69_in_derivedDef2163);
                if (state.failed) {
                    return retval;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 44, derivedDef_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fExp"
    // Pddl.g:354:1: fExp : ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead );
    public final PddlParser.fExp_return fExp ( ) throws RecognitionException {
        PddlParser.fExp_return retval = new PddlParser.fExp_return();
        retval.start = input.LT(1);
        int fExp_StartIndex = input.index();

        Object root_0 = null;

        Token NUMBER240 = null;
        Token char_literal241 = null;
        Token char_literal245 = null;
        Token char_literal246 = null;
        Token char_literal247 = null;
        Token char_literal249 = null;
        Token char_literal250 = null;
        Token string_literal251 = null;
        Token char_literal253 = null;
        Token char_literal254 = null;
        Token string_literal255 = null;
        Token char_literal257 = null;
        Token char_literal258 = null;
        Token string_literal259 = null;
        Token char_literal261 = null;
        ParserRuleReturnScope binaryOp242 = null;
        ParserRuleReturnScope fExp243 = null;
        ParserRuleReturnScope fExp2244 = null;
        ParserRuleReturnScope fExp248 = null;
        ParserRuleReturnScope fExp252 = null;
        ParserRuleReturnScope fExp256 = null;
        ParserRuleReturnScope fExp260 = null;
        ParserRuleReturnScope fHead262 = null;

        Object NUMBER240_tree = null;
        Object char_literal241_tree = null;
        Object char_literal245_tree = null;
        Object char_literal246_tree = null;
        Object char_literal247_tree = null;
        Object char_literal249_tree = null;
        Object char_literal250_tree = null;
        Object string_literal251_tree = null;
        Object char_literal253_tree = null;
        Object char_literal254_tree = null;
        Object string_literal255_tree = null;
        Object char_literal257_tree = null;
        Object char_literal258_tree = null;
        Object string_literal259_tree = null;
        Object char_literal261_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_112 = new RewriteRuleTokenStream(adaptor, "token 112");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_136 = new RewriteRuleTokenStream(adaptor, "token 136");
        RewriteRuleTokenStream stream_104 = new RewriteRuleTokenStream(adaptor, "token 104");
        RewriteRuleTokenStream stream_72 = new RewriteRuleTokenStream(adaptor, "token 72");
        RewriteRuleSubtreeStream stream_binaryOp = new RewriteRuleSubtreeStream(adaptor, "rule binaryOp");
        RewriteRuleSubtreeStream stream_fExp2 = new RewriteRuleSubtreeStream(adaptor, "rule fExp2");
        RewriteRuleSubtreeStream stream_fExp = new RewriteRuleSubtreeStream(adaptor, "rule fExp");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 45)) {
                return retval;
            }

            // Pddl.g:355:2: ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead )
            int alt48 = 7;
            switch (input.LA(1)) {
                case NUMBER: {
                    alt48 = 1;
                }
                break;
                case 68: {
                    int LA48_2 = input.LA(2);
                    if ((synpred66_Pddl())) {
                        alt48 = 2;
                    } else if ((synpred67_Pddl())) {
                        alt48 = 3;
                    } else if ((synpred68_Pddl())) {
                        alt48 = 4;
                    } else if ((synpred69_Pddl())) {
                        alt48 = 5;
                    } else if ((synpred70_Pddl())) {
                        alt48 = 6;
                    } else if ((true)) {
                        alt48 = 7;
                    }

                }
                break;
                case NAME:
                case 67: {
                    alt48 = 7;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 48, 0, input);
                    throw nvae;
            }
            switch (alt48) {
                case 1: // Pddl.g:355:4: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER240 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_fExp2178);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER240_tree = (Object) adaptor.create(NUMBER240);
                        adaptor.addChild(root_0, NUMBER240_tree);
                    }

                }
                break;
                case 2: // Pddl.g:356:4: '(' binaryOp fExp fExp2 ')'
                {
                    char_literal241 = (Token) match(input, 68, FOLLOW_68_in_fExp2183);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal241);
                    }

                    pushFollow(FOLLOW_binaryOp_in_fExp2185);
                    binaryOp242 = binaryOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_binaryOp.add(binaryOp242.getTree());
                    }
                    pushFollow(FOLLOW_fExp_in_fExp2187);
                    fExp243 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp243.getTree());
                    }
                    pushFollow(FOLLOW_fExp2_in_fExp2189);
                    fExp2244 = fExp2();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp2.add(fExp2244.getTree());
                    }
                    char_literal245 = (Token) match(input, 69, FOLLOW_69_in_fExp2191);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal245);
                    }

                    // AST REWRITE
                    // elements: binaryOp, fExp2, fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 356:32: -> ^( BINARY_OP binaryOp fExp fExp2 )
                        {
                            // Pddl.g:356:35: ^( BINARY_OP binaryOp fExp fExp2 )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(BINARY_OP, "BINARY_OP"), root_1);
                                adaptor.addChild(root_1, stream_binaryOp.nextTree());
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_1, stream_fExp2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:357:4: '(' '-' fExp ')'
                {
                    char_literal246 = (Token) match(input, 68, FOLLOW_68_in_fExp2208);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal246);
                    }

                    char_literal247 = (Token) match(input, 72, FOLLOW_72_in_fExp2210);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_72.add(char_literal247);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2212);
                    fExp248 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp248.getTree());
                    }
                    char_literal249 = (Token) match(input, 69, FOLLOW_69_in_fExp2214);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal249);
                    }

                    // AST REWRITE
                    // elements: fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 357:21: -> ^( UNARY_MINUS fExp )
                        {
                            // Pddl.g:357:24: ^( UNARY_MINUS fExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(UNARY_MINUS, "UNARY_MINUS"), root_1);
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 4: // Pddl.g:358:4: '(' 'sin' fExp ')'
                {
                    char_literal250 = (Token) match(input, 68, FOLLOW_68_in_fExp2227);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal250);
                    }

                    string_literal251 = (Token) match(input, 136, FOLLOW_136_in_fExp2229);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_136.add(string_literal251);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2231);
                    fExp252 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp252.getTree());
                    }
                    char_literal253 = (Token) match(input, 69, FOLLOW_69_in_fExp2233);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal253);
                    }

                    // AST REWRITE
                    // elements: fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 358:23: -> ^( SIN fExp )
                        {
                            // Pddl.g:358:26: ^( SIN fExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SIN, "SIN"), root_1);
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 5: // Pddl.g:359:4: '(' 'cos' fExp ')'
                {
                    char_literal254 = (Token) match(input, 68, FOLLOW_68_in_fExp2246);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal254);
                    }

                    string_literal255 = (Token) match(input, 112, FOLLOW_112_in_fExp2248);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_112.add(string_literal255);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2250);
                    fExp256 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp256.getTree());
                    }
                    char_literal257 = (Token) match(input, 69, FOLLOW_69_in_fExp2252);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal257);
                    }

                    // AST REWRITE
                    // elements: fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 359:23: -> ^( COS fExp )
                        {
                            // Pddl.g:359:26: ^( COS fExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COS, "COS"), root_1);
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 6: // Pddl.g:360:4: '(' 'abs' fExp ')'
                {
                    char_literal258 = (Token) match(input, 68, FOLLOW_68_in_fExp2265);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal258);
                    }

                    string_literal259 = (Token) match(input, 104, FOLLOW_104_in_fExp2267);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_104.add(string_literal259);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2269);
                    fExp260 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp260.getTree());
                    }
                    char_literal261 = (Token) match(input, 69, FOLLOW_69_in_fExp2271);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal261);
                    }

                    // AST REWRITE
                    // elements: fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 360:23: -> ^( ABS fExp )
                        {
                            // Pddl.g:360:26: ^( ABS fExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ABS, "ABS"), root_1);
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 7: // Pddl.g:361:4: fHead
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fHead_in_fExp2284);
                    fHead262 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead262.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 45, fExp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fExp2"
    // Pddl.g:366:1: fExp2 : fExp ;
    public final PddlParser.fExp2_return fExp2 ( ) throws RecognitionException {
        PddlParser.fExp2_return retval = new PddlParser.fExp2_return();
        retval.start = input.LT(1);
        int fExp2_StartIndex = input.index();

        Object root_0 = null;

        ParserRuleReturnScope fExp263 = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 46)) {
                return retval;
            }

            // Pddl.g:366:7: ( fExp )
            // Pddl.g:366:9: fExp
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_fExp_in_fExp22296);
                fExp263 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp263.getTree());
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 46, fExp2_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fHead"
    // Pddl.g:368:1: fHead : ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) );
    public final PddlParser.fHead_return fHead ( ) throws RecognitionException {
        PddlParser.fHead_return retval = new PddlParser.fHead_return();
        retval.start = input.LT(1);
        int fHead_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal264 = null;
        Token char_literal267 = null;
        ParserRuleReturnScope functionSymbol265 = null;
        ParserRuleReturnScope term266 = null;
        ParserRuleReturnScope functionSymbol268 = null;

        Object char_literal264_tree = null;
        Object char_literal267_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(adaptor, "rule term");
        RewriteRuleSubtreeStream stream_functionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule functionSymbol");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 47)) {
                return retval;
            }

            // Pddl.g:369:2: ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) )
            int alt50 = 2;
            int LA50_0 = input.LA(1);
            if ((LA50_0 == 68)) {
                alt50 = 1;
            } else if ((LA50_0 == NAME || LA50_0 == 67)) {
                alt50 = 2;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 50, 0, input);
                throw nvae;
            }

            switch (alt50) {
                case 1: // Pddl.g:369:4: '(' functionSymbol ( term )* ')'
                {
                    char_literal264 = (Token) match(input, 68, FOLLOW_68_in_fHead2306);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal264);
                    }

                    pushFollow(FOLLOW_functionSymbol_in_fHead2308);
                    functionSymbol265 = functionSymbol();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_functionSymbol.add(functionSymbol265.getTree());
                    }
                    // Pddl.g:369:23: ( term )*
                    loop49:
                    while (true) {
                        int alt49 = 2;
                        int LA49_0 = input.LA(1);
                        if ((LA49_0 == NAME || LA49_0 == VARIABLE)) {
                            alt49 = 1;
                        }

                        switch (alt49) {
                            case 1: // Pddl.g:369:23: term
                            {
                                pushFollow(FOLLOW_term_in_fHead2310);
                                term266 = term();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_term.add(term266.getTree());
                                }
                            }
                            break;

                            default:
                                break loop49;
                        }
                    }

                    char_literal267 = (Token) match(input, 69, FOLLOW_69_in_fHead2313);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal267);
                    }

                    // AST REWRITE
                    // elements: functionSymbol, term
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 369:33: -> ^( FUNC_HEAD functionSymbol ( term )* )
                        {
                            // Pddl.g:369:36: ^( FUNC_HEAD functionSymbol ( term )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FUNC_HEAD, "FUNC_HEAD"), root_1);
                                adaptor.addChild(root_1, stream_functionSymbol.nextTree());
                                // Pddl.g:369:63: ( term )*
                                while (stream_term.hasNext()) {
                                    adaptor.addChild(root_1, stream_term.nextTree());
                                }
                                stream_term.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:370:4: functionSymbol
                {
                    pushFollow(FOLLOW_functionSymbol_in_fHead2329);
                    functionSymbol268 = functionSymbol();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_functionSymbol.add(functionSymbol268.getTree());
                    }
                    // AST REWRITE
                    // elements: functionSymbol
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 370:19: -> ^( FUNC_HEAD functionSymbol )
                        {
                            // Pddl.g:370:22: ^( FUNC_HEAD functionSymbol )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FUNC_HEAD, "FUNC_HEAD"), root_1);
                                adaptor.addChild(root_1, stream_functionSymbol.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 47, fHead_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "effect"
    // Pddl.g:373:1: effect : ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect );
    public final PddlParser.effect_return effect ( ) throws RecognitionException {
        PddlParser.effect_return retval = new PddlParser.effect_return();
        retval.start = input.LT(1);
        int effect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal269 = null;
        Token string_literal270 = null;
        Token char_literal272 = null;
        ParserRuleReturnScope cEffect271 = null;
        ParserRuleReturnScope cEffect273 = null;

        Object char_literal269_tree = null;
        Object string_literal270_tree = null;
        Object char_literal272_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(adaptor, "token 108");
        RewriteRuleSubtreeStream stream_cEffect = new RewriteRuleSubtreeStream(adaptor, "rule cEffect");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 48)) {
                return retval;
            }

            // Pddl.g:374:2: ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect )
            int alt52 = 2;
            int LA52_0 = input.LA(1);
            if ((LA52_0 == 68)) {
                int LA52_1 = input.LA(2);
                if ((LA52_1 == 108)) {
                    alt52 = 1;
                } else if ((LA52_1 == NAME || LA52_1 == 109 || LA52_1 == 113 || LA52_1 == 119 || LA52_1 == 123 || LA52_1 == 127 || (LA52_1 >= 134 && LA52_1 <= 135) || LA52_1 == 142)) {
                    alt52 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 52, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 52, 0, input);
                throw nvae;
            }

            switch (alt52) {
                case 1: // Pddl.g:374:4: '(' 'and' ( cEffect )* ')'
                {
                    char_literal269 = (Token) match(input, 68, FOLLOW_68_in_effect2348);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal269);
                    }

                    string_literal270 = (Token) match(input, 108, FOLLOW_108_in_effect2350);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_108.add(string_literal270);
                    }

                    // Pddl.g:374:14: ( cEffect )*
                    loop51:
                    while (true) {
                        int alt51 = 2;
                        int LA51_0 = input.LA(1);
                        if ((LA51_0 == 68)) {
                            alt51 = 1;
                        }

                        switch (alt51) {
                            case 1: // Pddl.g:374:14: cEffect
                            {
                                pushFollow(FOLLOW_cEffect_in_effect2352);
                                cEffect271 = cEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_cEffect.add(cEffect271.getTree());
                                }
                            }
                            break;

                            default:
                                break loop51;
                        }
                    }

                    char_literal272 = (Token) match(input, 69, FOLLOW_69_in_effect2355);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal272);
                    }

                    // AST REWRITE
                    // elements: cEffect
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 374:27: -> ^( AND_EFFECT ( cEffect )* )
                        {
                            // Pddl.g:374:30: ^( AND_EFFECT ( cEffect )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
                                // Pddl.g:374:43: ( cEffect )*
                                while (stream_cEffect.hasNext()) {
                                    adaptor.addChild(root_1, stream_cEffect.nextTree());
                                }
                                stream_cEffect.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:375:4: cEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_cEffect_in_effect2369);
                    cEffect273 = cEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, cEffect273.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 48, effect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "cEffect"
    // Pddl.g:378:1: cEffect : ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect );
    public final PddlParser.cEffect_return cEffect ( ) throws RecognitionException {
        PddlParser.cEffect_return retval = new PddlParser.cEffect_return();
        retval.start = input.LT(1);
        int cEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal274 = null;
        Token string_literal275 = null;
        Token char_literal276 = null;
        Token char_literal278 = null;
        Token char_literal280 = null;
        Token char_literal281 = null;
        Token string_literal282 = null;
        Token char_literal285 = null;
        ParserRuleReturnScope typedVariableList277 = null;
        ParserRuleReturnScope effect279 = null;
        ParserRuleReturnScope goalDesc283 = null;
        ParserRuleReturnScope condEffect284 = null;
        ParserRuleReturnScope pEffect286 = null;

        Object char_literal274_tree = null;
        Object string_literal275_tree = null;
        Object char_literal276_tree = null;
        Object char_literal278_tree = null;
        Object char_literal280_tree = null;
        Object char_literal281_tree = null;
        Object string_literal282_tree = null;
        Object char_literal285_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_119 = new RewriteRuleTokenStream(adaptor, "token 119");
        RewriteRuleTokenStream stream_142 = new RewriteRuleTokenStream(adaptor, "token 142");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_effect = new RewriteRuleSubtreeStream(adaptor, "rule effect");
        RewriteRuleSubtreeStream stream_condEffect = new RewriteRuleSubtreeStream(adaptor, "rule condEffect");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 49)) {
                return retval;
            }

            // Pddl.g:379:2: ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect )
            int alt53 = 3;
            int LA53_0 = input.LA(1);
            if ((LA53_0 == 68)) {
                switch (input.LA(2)) {
                    case 119: {
                        alt53 = 1;
                    }
                    break;
                    case 142: {
                        alt53 = 2;
                    }
                    break;
                    case NAME:
                    case 109:
                    case 113:
                    case 123:
                    case 127:
                    case 134:
                    case 135: {
                        alt53 = 3;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 53, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 53, 0, input);
                throw nvae;
            }

            switch (alt53) {
                case 1: // Pddl.g:379:4: '(' 'forall' '(' typedVariableList ')' effect ')'
                {
                    char_literal274 = (Token) match(input, 68, FOLLOW_68_in_cEffect2380);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal274);
                    }

                    string_literal275 = (Token) match(input, 119, FOLLOW_119_in_cEffect2382);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_119.add(string_literal275);
                    }

                    char_literal276 = (Token) match(input, 68, FOLLOW_68_in_cEffect2384);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal276);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_cEffect2386);
                    typedVariableList277 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList277.getTree());
                    }
                    char_literal278 = (Token) match(input, 69, FOLLOW_69_in_cEffect2388);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal278);
                    }

                    pushFollow(FOLLOW_effect_in_cEffect2390);
                    effect279 = effect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_effect.add(effect279.getTree());
                    }
                    char_literal280 = (Token) match(input, 69, FOLLOW_69_in_cEffect2392);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal280);
                    }

                    // AST REWRITE
                    // elements: typedVariableList, effect
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 380:4: -> ^( FORALL_EFFECT typedVariableList effect )
                        {
                            // Pddl.g:380:7: ^( FORALL_EFFECT typedVariableList effect )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FORALL_EFFECT, "FORALL_EFFECT"), root_1);
                                adaptor.addChild(root_1, stream_typedVariableList.nextTree());
                                adaptor.addChild(root_1, stream_effect.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:381:4: '(' 'when' goalDesc condEffect ')'
                {
                    char_literal281 = (Token) match(input, 68, FOLLOW_68_in_cEffect2410);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal281);
                    }

                    string_literal282 = (Token) match(input, 142, FOLLOW_142_in_cEffect2412);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_142.add(string_literal282);
                    }

                    pushFollow(FOLLOW_goalDesc_in_cEffect2414);
                    goalDesc283 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc283.getTree());
                    }
                    pushFollow(FOLLOW_condEffect_in_cEffect2416);
                    condEffect284 = condEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_condEffect.add(condEffect284.getTree());
                    }
                    char_literal285 = (Token) match(input, 69, FOLLOW_69_in_cEffect2418);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal285);
                    }

                    // AST REWRITE
                    // elements: condEffect, goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 382:4: -> ^( WHEN_EFFECT goalDesc condEffect )
                        {
                            // Pddl.g:382:7: ^( WHEN_EFFECT goalDesc condEffect )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(WHEN_EFFECT, "WHEN_EFFECT"), root_1);
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_1, stream_condEffect.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:383:4: pEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_pEffect_in_cEffect2436);
                    pEffect286 = pEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, pEffect286.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 49, cEffect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "pEffect"
    // Pddl.g:386:1: pEffect : ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula );
    public final PddlParser.pEffect_return pEffect ( ) throws RecognitionException {
        PddlParser.pEffect_return retval = new PddlParser.pEffect_return();
        retval.start = input.LT(1);
        int pEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal287 = null;
        Token char_literal291 = null;
        Token char_literal292 = null;
        Token string_literal293 = null;
        Token char_literal295 = null;
        ParserRuleReturnScope assignOp288 = null;
        ParserRuleReturnScope fHead289 = null;
        ParserRuleReturnScope fExp290 = null;
        ParserRuleReturnScope atomicTermFormula294 = null;
        ParserRuleReturnScope atomicTermFormula296 = null;

        Object char_literal287_tree = null;
        Object char_literal291_tree = null;
        Object char_literal292_tree = null;
        Object string_literal293_tree = null;
        Object char_literal295_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_127 = new RewriteRuleTokenStream(adaptor, "token 127");
        RewriteRuleSubtreeStream stream_atomicTermFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicTermFormula");
        RewriteRuleSubtreeStream stream_assignOp = new RewriteRuleSubtreeStream(adaptor, "rule assignOp");
        RewriteRuleSubtreeStream stream_fExp = new RewriteRuleSubtreeStream(adaptor, "rule fExp");
        RewriteRuleSubtreeStream stream_fHead = new RewriteRuleSubtreeStream(adaptor, "rule fHead");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 50)) {
                return retval;
            }

            // Pddl.g:387:2: ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula )
            int alt54 = 3;
            int LA54_0 = input.LA(1);
            if ((LA54_0 == 68)) {
                switch (input.LA(2)) {
                    case 127: {
                        alt54 = 2;
                    }
                    break;
                    case 109:
                    case 113:
                    case 123:
                    case 134:
                    case 135: {
                        alt54 = 1;
                    }
                    break;
                    case NAME: {
                        alt54 = 3;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 54, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 54, 0, input);
                throw nvae;
            }

            switch (alt54) {
                case 1: // Pddl.g:387:4: '(' assignOp fHead fExp ')'
                {
                    char_literal287 = (Token) match(input, 68, FOLLOW_68_in_pEffect2447);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal287);
                    }

                    pushFollow(FOLLOW_assignOp_in_pEffect2449);
                    assignOp288 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_assignOp.add(assignOp288.getTree());
                    }
                    pushFollow(FOLLOW_fHead_in_pEffect2451);
                    fHead289 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fHead.add(fHead289.getTree());
                    }
                    pushFollow(FOLLOW_fExp_in_pEffect2453);
                    fExp290 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp290.getTree());
                    }
                    char_literal291 = (Token) match(input, 69, FOLLOW_69_in_pEffect2455);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal291);
                    }

                    // AST REWRITE
                    // elements: fHead, assignOp, fExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 388:4: -> ^( ASSIGN_EFFECT assignOp fHead fExp )
                        {
                            // Pddl.g:388:7: ^( ASSIGN_EFFECT assignOp fHead fExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ASSIGN_EFFECT, "ASSIGN_EFFECT"), root_1);
                                adaptor.addChild(root_1, stream_assignOp.nextTree());
                                adaptor.addChild(root_1, stream_fHead.nextTree());
                                adaptor.addChild(root_1, stream_fExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:389:4: '(' 'not' atomicTermFormula ')'
                {
                    char_literal292 = (Token) match(input, 68, FOLLOW_68_in_pEffect2475);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal292);
                    }

                    string_literal293 = (Token) match(input, 127, FOLLOW_127_in_pEffect2477);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_127.add(string_literal293);
                    }

                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2479);
                    atomicTermFormula294 = atomicTermFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicTermFormula.add(atomicTermFormula294.getTree());
                    }
                    char_literal295 = (Token) match(input, 69, FOLLOW_69_in_pEffect2481);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal295);
                    }

                    // AST REWRITE
                    // elements: atomicTermFormula
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 390:4: -> ^( NOT_EFFECT atomicTermFormula )
                        {
                            // Pddl.g:390:7: ^( NOT_EFFECT atomicTermFormula )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(NOT_EFFECT, "NOT_EFFECT"), root_1);
                                adaptor.addChild(root_1, stream_atomicTermFormula.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:391:4: atomicTermFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2497);
                    atomicTermFormula296 = atomicTermFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, atomicTermFormula296.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 50, pEffect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "condEffect"
    // Pddl.g:396:1: condEffect : ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect );
    public final PddlParser.condEffect_return condEffect ( ) throws RecognitionException {
        PddlParser.condEffect_return retval = new PddlParser.condEffect_return();
        retval.start = input.LT(1);
        int condEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal297 = null;
        Token string_literal298 = null;
        Token char_literal300 = null;
        ParserRuleReturnScope pEffect299 = null;
        ParserRuleReturnScope pEffect301 = null;

        Object char_literal297_tree = null;
        Object string_literal298_tree = null;
        Object char_literal300_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(adaptor, "token 108");
        RewriteRuleSubtreeStream stream_pEffect = new RewriteRuleSubtreeStream(adaptor, "rule pEffect");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 51)) {
                return retval;
            }

            // Pddl.g:397:2: ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect )
            int alt56 = 2;
            int LA56_0 = input.LA(1);
            if ((LA56_0 == 68)) {
                int LA56_1 = input.LA(2);
                if ((LA56_1 == 108)) {
                    alt56 = 1;
                } else if ((LA56_1 == NAME || LA56_1 == 109 || LA56_1 == 113 || LA56_1 == 123 || LA56_1 == 127 || (LA56_1 >= 134 && LA56_1 <= 135))) {
                    alt56 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 56, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 56, 0, input);
                throw nvae;
            }

            switch (alt56) {
                case 1: // Pddl.g:397:4: '(' 'and' ( pEffect )* ')'
                {
                    char_literal297 = (Token) match(input, 68, FOLLOW_68_in_condEffect2510);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal297);
                    }

                    string_literal298 = (Token) match(input, 108, FOLLOW_108_in_condEffect2512);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_108.add(string_literal298);
                    }

                    // Pddl.g:397:14: ( pEffect )*
                    loop55:
                    while (true) {
                        int alt55 = 2;
                        int LA55_0 = input.LA(1);
                        if ((LA55_0 == 68)) {
                            alt55 = 1;
                        }

                        switch (alt55) {
                            case 1: // Pddl.g:397:14: pEffect
                            {
                                pushFollow(FOLLOW_pEffect_in_condEffect2514);
                                pEffect299 = pEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_pEffect.add(pEffect299.getTree());
                                }
                            }
                            break;

                            default:
                                break loop55;
                        }
                    }

                    char_literal300 = (Token) match(input, 69, FOLLOW_69_in_condEffect2517);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal300);
                    }

                    // AST REWRITE
                    // elements: pEffect
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 397:27: -> ^( AND_EFFECT ( pEffect )* )
                        {
                            // Pddl.g:397:30: ^( AND_EFFECT ( pEffect )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
                                // Pddl.g:397:43: ( pEffect )*
                                while (stream_pEffect.hasNext()) {
                                    adaptor.addChild(root_1, stream_pEffect.nextTree());
                                }
                                stream_pEffect.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:398:4: pEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_pEffect_in_condEffect2531);
                    pEffect301 = pEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, pEffect301.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 51, condEffect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "binaryOp"
    // Pddl.g:403:1: binaryOp : ( '*' | '+' | '-' | '/' | '^' );
    public final PddlParser.binaryOp_return binaryOp ( ) throws RecognitionException {
        PddlParser.binaryOp_return retval = new PddlParser.binaryOp_return();
        retval.start = input.LT(1);
        int binaryOp_StartIndex = input.index();

        Object root_0 = null;

        Token set302 = null;

        Object set302_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 52)) {
                return retval;
            }

            // Pddl.g:403:10: ( '*' | '+' | '-' | '/' | '^' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set302 = input.LT(1);
                if ((input.LA(1) >= 70 && input.LA(1) <= 73) || input.LA(1) == 103) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set302));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 52, binaryOp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "multiOp"
    // Pddl.g:405:1: multiOp : ( '*' | '+' );
    public final PddlParser.multiOp_return multiOp ( ) throws RecognitionException {
        PddlParser.multiOp_return retval = new PddlParser.multiOp_return();
        retval.start = input.LT(1);
        int multiOp_StartIndex = input.index();

        Object root_0 = null;

        Token set303 = null;

        Object set303_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 53)) {
                return retval;
            }

            // Pddl.g:405:9: ( '*' | '+' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set303 = input.LT(1);
                if ((input.LA(1) >= 70 && input.LA(1) <= 71)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set303));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 53, multiOp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "binaryComp"
    // Pddl.g:407:1: binaryComp : ( '>' | '<' | '=' | '>=' | '<=' );
    public final PddlParser.binaryComp_return binaryComp ( ) throws RecognitionException {
        PddlParser.binaryComp_return retval = new PddlParser.binaryComp_return();
        retval.start = input.LT(1);
        int binaryComp_StartIndex = input.index();

        Object root_0 = null;

        Token set304 = null;

        Object set304_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 54)) {
                return retval;
            }

            // Pddl.g:407:12: ( '>' | '<' | '=' | '>=' | '<=' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set304 = input.LT(1);
                if ((input.LA(1) >= 97 && input.LA(1) <= 101)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set304));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 54, binaryComp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "assignOp"
    // Pddl.g:409:1: assignOp : ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' );
    public final PddlParser.assignOp_return assignOp ( ) throws RecognitionException {
        PddlParser.assignOp_return retval = new PddlParser.assignOp_return();
        retval.start = input.LT(1);
        int assignOp_StartIndex = input.index();

        Object root_0 = null;

        Token set305 = null;

        Object set305_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 55)) {
                return retval;
            }

            // Pddl.g:409:10: ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set305 = input.LT(1);
                if (input.LA(1) == 109 || input.LA(1) == 113 || input.LA(1) == 123 || (input.LA(1) >= 134 && input.LA(1) <= 135)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set305));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 55, assignOp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "durationConstraint"
    // Pddl.g:414:1: durationConstraint : ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint );
    public final PddlParser.durationConstraint_return durationConstraint ( ) throws RecognitionException {
        PddlParser.durationConstraint_return retval = new PddlParser.durationConstraint_return();
        retval.start = input.LT(1);
        int durationConstraint_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal306 = null;
        Token string_literal307 = null;
        Token char_literal309 = null;
        Token char_literal310 = null;
        Token char_literal311 = null;
        ParserRuleReturnScope simpleDurationConstraint308 = null;
        ParserRuleReturnScope simpleDurationConstraint312 = null;

        Object char_literal306_tree = null;
        Object string_literal307_tree = null;
        Object char_literal309_tree = null;
        Object char_literal310_tree = null;
        Object char_literal311_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 56)) {
                return retval;
            }

            // Pddl.g:415:2: ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint )
            int alt58 = 3;
            int LA58_0 = input.LA(1);
            if ((LA58_0 == 68)) {
                switch (input.LA(2)) {
                    case 108: {
                        alt58 = 1;
                    }
                    break;
                    case 69: {
                        alt58 = 2;
                    }
                    break;
                    case 98:
                    case 99:
                    case 101:
                    case 110: {
                        alt58 = 3;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 58, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 58, 0, input);
                throw nvae;
            }

            switch (alt58) {
                case 1: // Pddl.g:415:4: '(' 'and' ( simpleDurationConstraint )+ ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal306 = (Token) match(input, 68, FOLLOW_68_in_durationConstraint2636);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal306_tree = (Object) adaptor.create(char_literal306);
                        adaptor.addChild(root_0, char_literal306_tree);
                    }

                    string_literal307 = (Token) match(input, 108, FOLLOW_108_in_durationConstraint2638);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal307_tree = (Object) adaptor.create(string_literal307);
                        adaptor.addChild(root_0, string_literal307_tree);
                    }

                    // Pddl.g:415:14: ( simpleDurationConstraint )+
                    int cnt57 = 0;
                    loop57:
                    while (true) {
                        int alt57 = 2;
                        int LA57_0 = input.LA(1);
                        if ((LA57_0 == 68)) {
                            alt57 = 1;
                        }

                        switch (alt57) {
                            case 1: // Pddl.g:415:14: simpleDurationConstraint
                            {
                                pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2640);
                                simpleDurationConstraint308 = simpleDurationConstraint();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, simpleDurationConstraint308.getTree());
                                }

                            }
                            break;

                            default:
                                if (cnt57 >= 1) {
                                    break loop57;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(57, input);
                                throw eee;
                        }
                        cnt57++;
                    }

                    char_literal309 = (Token) match(input, 69, FOLLOW_69_in_durationConstraint2643);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal309_tree = (Object) adaptor.create(char_literal309);
                        adaptor.addChild(root_0, char_literal309_tree);
                    }

                }
                break;
                case 2: // Pddl.g:416:4: '(' ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal310 = (Token) match(input, 68, FOLLOW_68_in_durationConstraint2648);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal310_tree = (Object) adaptor.create(char_literal310);
                        adaptor.addChild(root_0, char_literal310_tree);
                    }

                    char_literal311 = (Token) match(input, 69, FOLLOW_69_in_durationConstraint2650);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal311_tree = (Object) adaptor.create(char_literal311);
                        adaptor.addChild(root_0, char_literal311_tree);
                    }

                }
                break;
                case 3: // Pddl.g:417:4: simpleDurationConstraint
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2655);
                    simpleDurationConstraint312 = simpleDurationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, simpleDurationConstraint312.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 56, durationConstraint_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "simpleDurationConstraint"
    // Pddl.g:420:1: simpleDurationConstraint : ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' );
    public final PddlParser.simpleDurationConstraint_return simpleDurationConstraint ( ) throws RecognitionException {
        PddlParser.simpleDurationConstraint_return retval = new PddlParser.simpleDurationConstraint_return();
        retval.start = input.LT(1);
        int simpleDurationConstraint_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal313 = null;
        Token string_literal315 = null;
        Token char_literal317 = null;
        Token char_literal318 = null;
        Token string_literal319 = null;
        Token char_literal322 = null;
        ParserRuleReturnScope durOp314 = null;
        ParserRuleReturnScope durValue316 = null;
        ParserRuleReturnScope timeSpecifier320 = null;
        ParserRuleReturnScope simpleDurationConstraint321 = null;

        Object char_literal313_tree = null;
        Object string_literal315_tree = null;
        Object char_literal317_tree = null;
        Object char_literal318_tree = null;
        Object string_literal319_tree = null;
        Object char_literal322_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 57)) {
                return retval;
            }

            // Pddl.g:421:2: ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' )
            int alt59 = 2;
            int LA59_0 = input.LA(1);
            if ((LA59_0 == 68)) {
                int LA59_1 = input.LA(2);
                if ((LA59_1 == 110)) {
                    alt59 = 2;
                } else if (((LA59_1 >= 98 && LA59_1 <= 99) || LA59_1 == 101)) {
                    alt59 = 1;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 59, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 59, 0, input);
                throw nvae;
            }

            switch (alt59) {
                case 1: // Pddl.g:421:4: '(' durOp '?duration' durValue ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal313 = (Token) match(input, 68, FOLLOW_68_in_simpleDurationConstraint2666);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal313_tree = (Object) adaptor.create(char_literal313);
                        adaptor.addChild(root_0, char_literal313_tree);
                    }

                    pushFollow(FOLLOW_durOp_in_simpleDurationConstraint2668);
                    durOp314 = durOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durOp314.getTree());
                    }

                    string_literal315 = (Token) match(input, 102, FOLLOW_102_in_simpleDurationConstraint2670);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal315_tree = (Object) adaptor.create(string_literal315);
                        adaptor.addChild(root_0, string_literal315_tree);
                    }

                    pushFollow(FOLLOW_durValue_in_simpleDurationConstraint2672);
                    durValue316 = durValue();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durValue316.getTree());
                    }

                    char_literal317 = (Token) match(input, 69, FOLLOW_69_in_simpleDurationConstraint2674);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal317_tree = (Object) adaptor.create(char_literal317);
                        adaptor.addChild(root_0, char_literal317_tree);
                    }

                }
                break;
                case 2: // Pddl.g:422:4: '(' 'at' timeSpecifier simpleDurationConstraint ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal318 = (Token) match(input, 68, FOLLOW_68_in_simpleDurationConstraint2679);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal318_tree = (Object) adaptor.create(char_literal318);
                        adaptor.addChild(root_0, char_literal318_tree);
                    }

                    string_literal319 = (Token) match(input, 110, FOLLOW_110_in_simpleDurationConstraint2681);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal319_tree = (Object) adaptor.create(string_literal319);
                        adaptor.addChild(root_0, string_literal319_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_simpleDurationConstraint2683);
                    timeSpecifier320 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier320.getTree());
                    }

                    pushFollow(FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2685);
                    simpleDurationConstraint321 = simpleDurationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, simpleDurationConstraint321.getTree());
                    }

                    char_literal322 = (Token) match(input, 69, FOLLOW_69_in_simpleDurationConstraint2687);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal322_tree = (Object) adaptor.create(char_literal322);
                        adaptor.addChild(root_0, char_literal322_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 57, simpleDurationConstraint_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "durOp"
    // Pddl.g:425:1: durOp : ( '<=' | '>=' | '=' );
    public final PddlParser.durOp_return durOp ( ) throws RecognitionException {
        PddlParser.durOp_return retval = new PddlParser.durOp_return();
        retval.start = input.LT(1);
        int durOp_StartIndex = input.index();

        Object root_0 = null;

        Token set323 = null;

        Object set323_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 58)) {
                return retval;
            }

            // Pddl.g:425:7: ( '<=' | '>=' | '=' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set323 = input.LT(1);
                if ((input.LA(1) >= 98 && input.LA(1) <= 99) || input.LA(1) == 101) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set323));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 58, durOp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "durValue"
    // Pddl.g:427:1: durValue : ( NUMBER | fExp );
    public final PddlParser.durValue_return durValue ( ) throws RecognitionException {
        PddlParser.durValue_return retval = new PddlParser.durValue_return();
        retval.start = input.LT(1);
        int durValue_StartIndex = input.index();

        Object root_0 = null;

        Token NUMBER324 = null;
        ParserRuleReturnScope fExp325 = null;

        Object NUMBER324_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 59)) {
                return retval;
            }

            // Pddl.g:427:10: ( NUMBER | fExp )
            int alt60 = 2;
            int LA60_0 = input.LA(1);
            if ((LA60_0 == NUMBER)) {
                int LA60_1 = input.LA(2);
                if ((synpred100_Pddl())) {
                    alt60 = 1;
                } else if ((true)) {
                    alt60 = 2;
                }

            } else if ((LA60_0 == NAME || (LA60_0 >= 67 && LA60_0 <= 68))) {
                alt60 = 2;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 60, 0, input);
                throw nvae;
            }

            switch (alt60) {
                case 1: // Pddl.g:427:12: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER324 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_durValue2714);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER324_tree = (Object) adaptor.create(NUMBER324);
                        adaptor.addChild(root_0, NUMBER324_tree);
                    }

                }
                break;
                case 2: // Pddl.g:427:21: fExp
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fExp_in_durValue2718);
                    fExp325 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp325.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 59, durValue_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "daEffect"
    // Pddl.g:429:1: daEffect : ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' );
    public final PddlParser.daEffect_return daEffect ( ) throws RecognitionException {
        PddlParser.daEffect_return retval = new PddlParser.daEffect_return();
        retval.start = input.LT(1);
        int daEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal326 = null;
        Token string_literal327 = null;
        Token char_literal329 = null;
        Token char_literal331 = null;
        Token string_literal332 = null;
        Token char_literal333 = null;
        Token char_literal335 = null;
        Token char_literal337 = null;
        Token char_literal338 = null;
        Token string_literal339 = null;
        Token char_literal342 = null;
        Token char_literal343 = null;
        Token char_literal347 = null;
        ParserRuleReturnScope daEffect328 = null;
        ParserRuleReturnScope timedEffect330 = null;
        ParserRuleReturnScope typedVariableList334 = null;
        ParserRuleReturnScope daEffect336 = null;
        ParserRuleReturnScope daGD340 = null;
        ParserRuleReturnScope timedEffect341 = null;
        ParserRuleReturnScope assignOp344 = null;
        ParserRuleReturnScope fHead345 = null;
        ParserRuleReturnScope fExpDA346 = null;

        Object char_literal326_tree = null;
        Object string_literal327_tree = null;
        Object char_literal329_tree = null;
        Object char_literal331_tree = null;
        Object string_literal332_tree = null;
        Object char_literal333_tree = null;
        Object char_literal335_tree = null;
        Object char_literal337_tree = null;
        Object char_literal338_tree = null;
        Object string_literal339_tree = null;
        Object char_literal342_tree = null;
        Object char_literal343_tree = null;
        Object char_literal347_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 60)) {
                return retval;
            }

            // Pddl.g:430:2: ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' )
            int alt62 = 5;
            int LA62_0 = input.LA(1);
            if ((LA62_0 == 68)) {
                int LA62_1 = input.LA(2);
                if ((synpred102_Pddl())) {
                    alt62 = 1;
                } else if ((synpred103_Pddl())) {
                    alt62 = 2;
                } else if ((synpred104_Pddl())) {
                    alt62 = 3;
                } else if ((synpred105_Pddl())) {
                    alt62 = 4;
                } else if ((true)) {
                    alt62 = 5;
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 62, 0, input);
                throw nvae;
            }

            switch (alt62) {
                case 1: // Pddl.g:430:4: '(' 'and' ( daEffect )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal326 = (Token) match(input, 68, FOLLOW_68_in_daEffect2728);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal326_tree = (Object) adaptor.create(char_literal326);
                        adaptor.addChild(root_0, char_literal326_tree);
                    }

                    string_literal327 = (Token) match(input, 108, FOLLOW_108_in_daEffect2730);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal327_tree = (Object) adaptor.create(string_literal327);
                        adaptor.addChild(root_0, string_literal327_tree);
                    }

                    // Pddl.g:430:14: ( daEffect )*
                    loop61:
                    while (true) {
                        int alt61 = 2;
                        int LA61_0 = input.LA(1);
                        if ((LA61_0 == 68)) {
                            alt61 = 1;
                        }

                        switch (alt61) {
                            case 1: // Pddl.g:430:14: daEffect
                            {
                                pushFollow(FOLLOW_daEffect_in_daEffect2732);
                                daEffect328 = daEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, daEffect328.getTree());
                                }

                            }
                            break;

                            default:
                                break loop61;
                        }
                    }

                    char_literal329 = (Token) match(input, 69, FOLLOW_69_in_daEffect2735);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal329_tree = (Object) adaptor.create(char_literal329);
                        adaptor.addChild(root_0, char_literal329_tree);
                    }

                }
                break;
                case 2: // Pddl.g:431:4: timedEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_timedEffect_in_daEffect2740);
                    timedEffect330 = timedEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedEffect330.getTree());
                    }

                }
                break;
                case 3: // Pddl.g:432:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal331 = (Token) match(input, 68, FOLLOW_68_in_daEffect2745);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal331_tree = (Object) adaptor.create(char_literal331);
                        adaptor.addChild(root_0, char_literal331_tree);
                    }

                    string_literal332 = (Token) match(input, 119, FOLLOW_119_in_daEffect2747);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal332_tree = (Object) adaptor.create(string_literal332);
                        adaptor.addChild(root_0, string_literal332_tree);
                    }

                    char_literal333 = (Token) match(input, 68, FOLLOW_68_in_daEffect2749);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal333_tree = (Object) adaptor.create(char_literal333);
                        adaptor.addChild(root_0, char_literal333_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daEffect2751);
                    typedVariableList334 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList334.getTree());
                    }

                    char_literal335 = (Token) match(input, 69, FOLLOW_69_in_daEffect2753);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal335_tree = (Object) adaptor.create(char_literal335);
                        adaptor.addChild(root_0, char_literal335_tree);
                    }

                    pushFollow(FOLLOW_daEffect_in_daEffect2755);
                    daEffect336 = daEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daEffect336.getTree());
                    }

                    char_literal337 = (Token) match(input, 69, FOLLOW_69_in_daEffect2757);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal337_tree = (Object) adaptor.create(char_literal337);
                        adaptor.addChild(root_0, char_literal337_tree);
                    }

                }
                break;
                case 4: // Pddl.g:433:4: '(' 'when' daGD timedEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal338 = (Token) match(input, 68, FOLLOW_68_in_daEffect2762);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal338_tree = (Object) adaptor.create(char_literal338);
                        adaptor.addChild(root_0, char_literal338_tree);
                    }

                    string_literal339 = (Token) match(input, 142, FOLLOW_142_in_daEffect2764);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal339_tree = (Object) adaptor.create(string_literal339);
                        adaptor.addChild(root_0, string_literal339_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daEffect2766);
                    daGD340 = daGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daGD340.getTree());
                    }

                    pushFollow(FOLLOW_timedEffect_in_daEffect2768);
                    timedEffect341 = timedEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedEffect341.getTree());
                    }

                    char_literal342 = (Token) match(input, 69, FOLLOW_69_in_daEffect2770);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal342_tree = (Object) adaptor.create(char_literal342);
                        adaptor.addChild(root_0, char_literal342_tree);
                    }

                }
                break;
                case 5: // Pddl.g:434:4: '(' assignOp fHead fExpDA ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal343 = (Token) match(input, 68, FOLLOW_68_in_daEffect2775);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal343_tree = (Object) adaptor.create(char_literal343);
                        adaptor.addChild(root_0, char_literal343_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_daEffect2777);
                    assignOp344 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, assignOp344.getTree());
                    }

                    pushFollow(FOLLOW_fHead_in_daEffect2779);
                    fHead345 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead345.getTree());
                    }

                    pushFollow(FOLLOW_fExpDA_in_daEffect2781);
                    fExpDA346 = fExpDA();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExpDA346.getTree());
                    }

                    char_literal347 = (Token) match(input, 69, FOLLOW_69_in_daEffect2783);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal347_tree = (Object) adaptor.create(char_literal347);
                        adaptor.addChild(root_0, char_literal347_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 60, daEffect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "timedEffect"
    // Pddl.g:437:1: timedEffect : ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' );
    public final PddlParser.timedEffect_return timedEffect ( ) throws RecognitionException {
        PddlParser.timedEffect_return retval = new PddlParser.timedEffect_return();
        retval.start = input.LT(1);
        int timedEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal348 = null;
        Token string_literal349 = null;
        Token char_literal352 = null;
        Token char_literal353 = null;
        Token string_literal354 = null;
        Token char_literal357 = null;
        Token char_literal358 = null;
        Token char_literal362 = null;
        ParserRuleReturnScope timeSpecifier350 = null;
        ParserRuleReturnScope daEffect351 = null;
        ParserRuleReturnScope timeSpecifier355 = null;
        ParserRuleReturnScope fAssignDA356 = null;
        ParserRuleReturnScope assignOp359 = null;
        ParserRuleReturnScope fHead360 = null;
        ParserRuleReturnScope fExp361 = null;

        Object char_literal348_tree = null;
        Object string_literal349_tree = null;
        Object char_literal352_tree = null;
        Object char_literal353_tree = null;
        Object string_literal354_tree = null;
        Object char_literal357_tree = null;
        Object char_literal358_tree = null;
        Object char_literal362_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 61)) {
                return retval;
            }

            // Pddl.g:438:2: ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' )
            int alt63 = 3;
            int LA63_0 = input.LA(1);
            if ((LA63_0 == 68)) {
                int LA63_1 = input.LA(2);
                if ((synpred106_Pddl())) {
                    alt63 = 1;
                } else if ((synpred107_Pddl())) {
                    alt63 = 2;
                } else if ((true)) {
                    alt63 = 3;
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 63, 0, input);
                throw nvae;
            }

            switch (alt63) {
                case 1: // Pddl.g:438:4: '(' 'at' timeSpecifier daEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal348 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2794);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal348_tree = (Object) adaptor.create(char_literal348);
                        adaptor.addChild(root_0, char_literal348_tree);
                    }

                    string_literal349 = (Token) match(input, 110, FOLLOW_110_in_timedEffect2796);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal349_tree = (Object) adaptor.create(string_literal349);
                        adaptor.addChild(root_0, string_literal349_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2798);
                    timeSpecifier350 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier350.getTree());
                    }

                    pushFollow(FOLLOW_daEffect_in_timedEffect2800);
                    daEffect351 = daEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daEffect351.getTree());
                    }

                    char_literal352 = (Token) match(input, 69, FOLLOW_69_in_timedEffect2802);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal352_tree = (Object) adaptor.create(char_literal352);
                        adaptor.addChild(root_0, char_literal352_tree);
                    }

                }
                break;
                case 2: // Pddl.g:439:4: '(' 'at' timeSpecifier fAssignDA ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal353 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2812);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal353_tree = (Object) adaptor.create(char_literal353);
                        adaptor.addChild(root_0, char_literal353_tree);
                    }

                    string_literal354 = (Token) match(input, 110, FOLLOW_110_in_timedEffect2814);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal354_tree = (Object) adaptor.create(string_literal354);
                        adaptor.addChild(root_0, string_literal354_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2816);
                    timeSpecifier355 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier355.getTree());
                    }

                    pushFollow(FOLLOW_fAssignDA_in_timedEffect2818);
                    fAssignDA356 = fAssignDA();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fAssignDA356.getTree());
                    }

                    char_literal357 = (Token) match(input, 69, FOLLOW_69_in_timedEffect2820);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal357_tree = (Object) adaptor.create(char_literal357);
                        adaptor.addChild(root_0, char_literal357_tree);
                    }

                }
                break;
                case 3: // Pddl.g:440:4: '(' assignOp fHead fExp ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal358 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2825);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal358_tree = (Object) adaptor.create(char_literal358);
                        adaptor.addChild(root_0, char_literal358_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_timedEffect2827);
                    assignOp359 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, assignOp359.getTree());
                    }

                    pushFollow(FOLLOW_fHead_in_timedEffect2829);
                    fHead360 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead360.getTree());
                    }

                    pushFollow(FOLLOW_fExp_in_timedEffect2831);
                    fExp361 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp361.getTree());
                    }

                    char_literal362 = (Token) match(input, 69, FOLLOW_69_in_timedEffect2833);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal362_tree = (Object) adaptor.create(char_literal362);
                        adaptor.addChild(root_0, char_literal362_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 61, timedEffect_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fAssignDA"
    // Pddl.g:443:1: fAssignDA : '(' assignOp fHead fExpDA ')' ;
    public final PddlParser.fAssignDA_return fAssignDA ( ) throws RecognitionException {
        PddlParser.fAssignDA_return retval = new PddlParser.fAssignDA_return();
        retval.start = input.LT(1);
        int fAssignDA_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal363 = null;
        Token char_literal367 = null;
        ParserRuleReturnScope assignOp364 = null;
        ParserRuleReturnScope fHead365 = null;
        ParserRuleReturnScope fExpDA366 = null;

        Object char_literal363_tree = null;
        Object char_literal367_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 62)) {
                return retval;
            }

            // Pddl.g:444:2: ( '(' assignOp fHead fExpDA ')' )
            // Pddl.g:444:4: '(' assignOp fHead fExpDA ')'
            {
                root_0 = (Object) adaptor.nil();

                char_literal363 = (Token) match(input, 68, FOLLOW_68_in_fAssignDA2853);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal363_tree = (Object) adaptor.create(char_literal363);
                    adaptor.addChild(root_0, char_literal363_tree);
                }

                pushFollow(FOLLOW_assignOp_in_fAssignDA2855);
                assignOp364 = assignOp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, assignOp364.getTree());
                }

                pushFollow(FOLLOW_fHead_in_fAssignDA2857);
                fHead365 = fHead();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fHead365.getTree());
                }

                pushFollow(FOLLOW_fExpDA_in_fAssignDA2859);
                fExpDA366 = fExpDA();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExpDA366.getTree());
                }

                char_literal367 = (Token) match(input, 69, FOLLOW_69_in_fAssignDA2861);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal367_tree = (Object) adaptor.create(char_literal367);
                    adaptor.addChild(root_0, char_literal367_tree);
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 62, fAssignDA_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "fExpDA"
    // Pddl.g:447:1: fExpDA : ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp );
    public final PddlParser.fExpDA_return fExpDA ( ) throws RecognitionException {
        PddlParser.fExpDA_return retval = new PddlParser.fExpDA_return();
        retval.start = input.LT(1);
        int fExpDA_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal368 = null;
        Token char_literal372 = null;
        Token char_literal374 = null;
        Token string_literal375 = null;
        ParserRuleReturnScope binaryOp369 = null;
        ParserRuleReturnScope fExpDA370 = null;
        ParserRuleReturnScope fExpDA371 = null;
        ParserRuleReturnScope fExpDA373 = null;
        ParserRuleReturnScope fExp376 = null;

        Object char_literal368_tree = null;
        Object char_literal372_tree = null;
        Object char_literal374_tree = null;
        Object string_literal375_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 63)) {
                return retval;
            }

            // Pddl.g:448:2: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp )
            int alt65 = 3;
            switch (input.LA(1)) {
                case 68: {
                    int LA65_1 = input.LA(2);
                    if ((synpred109_Pddl())) {
                        alt65 = 1;
                    } else if ((true)) {
                        alt65 = 3;
                    }

                }
                break;
                case 102: {
                    alt65 = 2;
                }
                break;
                case NAME:
                case NUMBER:
                case 67: {
                    alt65 = 3;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 65, 0, input);
                    throw nvae;
            }
            switch (alt65) {
                case 1: // Pddl.g:448:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal368 = (Token) match(input, 68, FOLLOW_68_in_fExpDA2872);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal368_tree = (Object) adaptor.create(char_literal368);
                        adaptor.addChild(root_0, char_literal368_tree);
                    }

                    // Pddl.g:448:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
                    int alt64 = 2;
                    int LA64_0 = input.LA(1);
                    if ((LA64_0 == 72)) {
                        int LA64_1 = input.LA(2);
                        if ((synpred108_Pddl())) {
                            alt64 = 1;
                        } else if ((true)) {
                            alt64 = 2;
                        }

                    } else if (((LA64_0 >= 70 && LA64_0 <= 71) || LA64_0 == 73 || LA64_0 == 103)) {
                        alt64 = 1;
                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        NoViableAltException nvae
                                = new NoViableAltException("", 64, 0, input);
                        throw nvae;
                    }

                    switch (alt64) {
                        case 1: // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
                        {
                            // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
                            // Pddl.g:448:10: binaryOp fExpDA fExpDA
                            {
                                pushFollow(FOLLOW_binaryOp_in_fExpDA2876);
                                binaryOp369 = binaryOp();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, binaryOp369.getTree());
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2878);
                                fExpDA370 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA370.getTree());
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2880);
                                fExpDA371 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA371.getTree());
                                }

                            }

                        }
                        break;
                        case 2: // Pddl.g:448:36: ( '-' fExpDA )
                        {
                            // Pddl.g:448:36: ( '-' fExpDA )
                            // Pddl.g:448:37: '-' fExpDA
                            {
                                char_literal372 = (Token) match(input, 72, FOLLOW_72_in_fExpDA2886);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal372_tree = (Object) adaptor.create(char_literal372);
                                    adaptor.addChild(root_0, char_literal372_tree);
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2888);
                                fExpDA373 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA373.getTree());
                                }

                            }

                        }
                        break;

                    }

                    char_literal374 = (Token) match(input, 69, FOLLOW_69_in_fExpDA2892);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal374_tree = (Object) adaptor.create(char_literal374);
                        adaptor.addChild(root_0, char_literal374_tree);
                    }

                }
                break;
                case 2: // Pddl.g:449:4: '?duration'
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal375 = (Token) match(input, 102, FOLLOW_102_in_fExpDA2897);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal375_tree = (Object) adaptor.create(string_literal375);
                        adaptor.addChild(root_0, string_literal375_tree);
                    }

                }
                break;
                case 3: // Pddl.g:450:4: fExp
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fExp_in_fExpDA2902);
                    fExp376 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp376.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 63, fExpDA_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "problem"
    // Pddl.g:455:1: problem : '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) ;
    public final PddlParser.problem_return problem ( ) throws RecognitionException {
        PddlParser.problem_return retval = new PddlParser.problem_return();
        retval.start = input.LT(1);
        int problem_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal377 = null;
        Token string_literal378 = null;
        Token char_literal387 = null;
        ParserRuleReturnScope problemDecl379 = null;
        ParserRuleReturnScope problemDomain380 = null;
        ParserRuleReturnScope requireDef381 = null;
        ParserRuleReturnScope objectDecl382 = null;
        ParserRuleReturnScope init383 = null;
        ParserRuleReturnScope goal384 = null;
        ParserRuleReturnScope probConstraints385 = null;
        ParserRuleReturnScope metricSpec386 = null;

        Object char_literal377_tree = null;
        Object string_literal378_tree = null;
        Object char_literal387_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_114 = new RewriteRuleTokenStream(adaptor, "token 114");
        RewriteRuleSubtreeStream stream_metricSpec = new RewriteRuleSubtreeStream(adaptor, "rule metricSpec");
        RewriteRuleSubtreeStream stream_init = new RewriteRuleSubtreeStream(adaptor, "rule init");
        RewriteRuleSubtreeStream stream_goal = new RewriteRuleSubtreeStream(adaptor, "rule goal");
        RewriteRuleSubtreeStream stream_requireDef = new RewriteRuleSubtreeStream(adaptor, "rule requireDef");
        RewriteRuleSubtreeStream stream_problemDecl = new RewriteRuleSubtreeStream(adaptor, "rule problemDecl");
        RewriteRuleSubtreeStream stream_objectDecl = new RewriteRuleSubtreeStream(adaptor, "rule objectDecl");
        RewriteRuleSubtreeStream stream_problemDomain = new RewriteRuleSubtreeStream(adaptor, "rule problemDomain");
        RewriteRuleSubtreeStream stream_probConstraints = new RewriteRuleSubtreeStream(adaptor, "rule probConstraints");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 64)) {
                return retval;
            }

            // Pddl.g:456:2: ( '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) )
            // Pddl.g:456:4: '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')'
            {
                char_literal377 = (Token) match(input, 68, FOLLOW_68_in_problem2916);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal377);
                }

                string_literal378 = (Token) match(input, 114, FOLLOW_114_in_problem2918);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_114.add(string_literal378);
                }

                pushFollow(FOLLOW_problemDecl_in_problem2920);
                problemDecl379 = problemDecl();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_problemDecl.add(problemDecl379.getTree());
                }
                pushFollow(FOLLOW_problemDomain_in_problem2925);
                problemDomain380 = problemDomain();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_problemDomain.add(problemDomain380.getTree());
                }
                // Pddl.g:458:7: ( requireDef )?
                int alt66 = 2;
                int LA66_0 = input.LA(1);
                if ((LA66_0 == 68)) {
                    int LA66_1 = input.LA(2);
                    if ((LA66_1 == 95)) {
                        alt66 = 1;
                    }
                }
                switch (alt66) {
                    case 1: // Pddl.g:458:7: requireDef
                    {
                        pushFollow(FOLLOW_requireDef_in_problem2933);
                        requireDef381 = requireDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_requireDef.add(requireDef381.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:459:7: ( objectDecl )?
                int alt67 = 2;
                int LA67_0 = input.LA(1);
                if ((LA67_0 == 68)) {
                    int LA67_1 = input.LA(2);
                    if ((LA67_1 == 90)) {
                        alt67 = 1;
                    }
                }
                switch (alt67) {
                    case 1: // Pddl.g:459:7: objectDecl
                    {
                        pushFollow(FOLLOW_objectDecl_in_problem2942);
                        objectDecl382 = objectDecl();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_objectDecl.add(objectDecl382.getTree());
                        }
                    }
                    break;

                }

                pushFollow(FOLLOW_init_in_problem2951);
                init383 = init();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_init.add(init383.getTree());
                }
                pushFollow(FOLLOW_goal_in_problem2959);
                goal384 = goal();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_goal.add(goal384.getTree());
                }
                // Pddl.g:462:7: ( probConstraints )?
                int alt68 = 2;
                int LA68_0 = input.LA(1);
                if ((LA68_0 == 68)) {
                    int LA68_1 = input.LA(2);
                    if ((LA68_1 == 78)) {
                        alt68 = 1;
                    }
                }
                switch (alt68) {
                    case 1: // Pddl.g:462:7: probConstraints
                    {
                        pushFollow(FOLLOW_probConstraints_in_problem2967);
                        probConstraints385 = probConstraints();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_probConstraints.add(probConstraints385.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:463:7: ( metricSpec )?
                int alt69 = 2;
                int LA69_0 = input.LA(1);
                if ((LA69_0 == 68)) {
                    alt69 = 1;
                }
                switch (alt69) {
                    case 1: // Pddl.g:463:7: metricSpec
                    {
                        pushFollow(FOLLOW_metricSpec_in_problem2976);
                        metricSpec386 = metricSpec();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_metricSpec.add(metricSpec386.getTree());
                        }
                    }
                    break;

                }

                char_literal387 = (Token) match(input, 69, FOLLOW_69_in_problem2992);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal387);
                }

                // AST REWRITE
                // elements: init, problemDecl, goal, objectDecl, problemDomain, requireDef, probConstraints, metricSpec
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 466:7: -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
                    {
                        // Pddl.g:466:10: ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM, "PROBLEM"), root_1);
                            adaptor.addChild(root_1, stream_problemDecl.nextTree());
                            adaptor.addChild(root_1, stream_problemDomain.nextTree());
                            // Pddl.g:466:46: ( requireDef )?
                            if (stream_requireDef.hasNext()) {
                                adaptor.addChild(root_1, stream_requireDef.nextTree());
                            }
                            stream_requireDef.reset();

                            // Pddl.g:466:58: ( objectDecl )?
                            if (stream_objectDecl.hasNext()) {
                                adaptor.addChild(root_1, stream_objectDecl.nextTree());
                            }
                            stream_objectDecl.reset();

                            adaptor.addChild(root_1, stream_init.nextTree());
                            adaptor.addChild(root_1, stream_goal.nextTree());
                            // Pddl.g:467:19: ( probConstraints )?
                            if (stream_probConstraints.hasNext()) {
                                adaptor.addChild(root_1, stream_probConstraints.nextTree());
                            }
                            stream_probConstraints.reset();

                            // Pddl.g:467:36: ( metricSpec )?
                            if (stream_metricSpec.hasNext()) {
                                adaptor.addChild(root_1, stream_metricSpec.nextTree());
                            }
                            stream_metricSpec.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 64, problem_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "problemDecl"
    // Pddl.g:470:1: problemDecl : '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) ;
    public final PddlParser.problemDecl_return problemDecl ( ) throws RecognitionException {
        PddlParser.problemDecl_return retval = new PddlParser.problemDecl_return();
        retval.start = input.LT(1);
        int problemDecl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal388 = null;
        Token string_literal389 = null;
        Token NAME390 = null;
        Token char_literal391 = null;

        Object char_literal388_tree = null;
        Object string_literal389_tree = null;
        Object NAME390_tree = null;
        Object char_literal391_tree = null;
        RewriteRuleTokenStream stream_133 = new RewriteRuleTokenStream(adaptor, "token 133");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 65)) {
                return retval;
            }

            // Pddl.g:471:5: ( '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) )
            // Pddl.g:471:7: '(' 'problem' NAME ')'
            {
                char_literal388 = (Token) match(input, 68, FOLLOW_68_in_problemDecl3049);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal388);
                }

                string_literal389 = (Token) match(input, 133, FOLLOW_133_in_problemDecl3051);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_133.add(string_literal389);
                }

                NAME390 = (Token) match(input, NAME, FOLLOW_NAME_in_problemDecl3053);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME390);
                }

                char_literal391 = (Token) match(input, 69, FOLLOW_69_in_problemDecl3055);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal391);
                }

                // AST REWRITE
                // elements: NAME
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 472:5: -> ^( PROBLEM_NAME NAME )
                    {
                        // Pddl.g:472:8: ^( PROBLEM_NAME NAME )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM_NAME, "PROBLEM_NAME"), root_1);
                            adaptor.addChild(root_1, stream_NAME.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 65, problemDecl_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "problemDomain"
    // Pddl.g:475:1: problemDomain : '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) ;
    public final PddlParser.problemDomain_return problemDomain ( ) throws RecognitionException {
        PddlParser.problemDomain_return retval = new PddlParser.problemDomain_return();
        retval.start = input.LT(1);
        int problemDomain_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal392 = null;
        Token string_literal393 = null;
        Token NAME394 = null;
        Token char_literal395 = null;

        Object char_literal392_tree = null;
        Object string_literal393_tree = null;
        Object NAME394_tree = null;
        Object char_literal395_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_80 = new RewriteRuleTokenStream(adaptor, "token 80");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 66)) {
                return retval;
            }

            // Pddl.g:476:2: ( '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) )
            // Pddl.g:476:4: '(' ':domain' NAME ')'
            {
                char_literal392 = (Token) match(input, 68, FOLLOW_68_in_problemDomain3081);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal392);
                }

                string_literal393 = (Token) match(input, 80, FOLLOW_80_in_problemDomain3083);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_80.add(string_literal393);
                }

                NAME394 = (Token) match(input, NAME, FOLLOW_NAME_in_problemDomain3085);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME394);
                }

                char_literal395 = (Token) match(input, 69, FOLLOW_69_in_problemDomain3087);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal395);
                }

                // AST REWRITE
                // elements: NAME
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 477:2: -> ^( PROBLEM_DOMAIN NAME )
                    {
                        // Pddl.g:477:5: ^( PROBLEM_DOMAIN NAME )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM_DOMAIN, "PROBLEM_DOMAIN"), root_1);
                            adaptor.addChild(root_1, stream_NAME.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 66, problemDomain_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "objectDecl"
    // Pddl.g:480:1: objectDecl : '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) ;
    public final PddlParser.objectDecl_return objectDecl ( ) throws RecognitionException {
        PddlParser.objectDecl_return retval = new PddlParser.objectDecl_return();
        retval.start = input.LT(1);
        int objectDecl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal396 = null;
        Token string_literal397 = null;
        Token char_literal399 = null;
        ParserRuleReturnScope typedNameList398 = null;

        Object char_literal396_tree = null;
        Object string_literal397_tree = null;
        Object char_literal399_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 67)) {
                return retval;
            }

            // Pddl.g:481:2: ( '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) )
            // Pddl.g:481:4: '(' ':objects' typedNameList ')'
            {
                char_literal396 = (Token) match(input, 68, FOLLOW_68_in_objectDecl3107);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal396);
                }

                string_literal397 = (Token) match(input, 90, FOLLOW_90_in_objectDecl3109);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal397);
                }

                pushFollow(FOLLOW_typedNameList_in_objectDecl3111);
                typedNameList398 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList398.getTree());
                }
                char_literal399 = (Token) match(input, 69, FOLLOW_69_in_objectDecl3113);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal399);
                }

                // AST REWRITE
                // elements: typedNameList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 482:2: -> ^( OBJECTS typedNameList )
                    {
                        // Pddl.g:482:5: ^( OBJECTS typedNameList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OBJECTS, "OBJECTS"), root_1);
                            adaptor.addChild(root_1, stream_typedNameList.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 67, objectDecl_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "init"
    // Pddl.g:485:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) );
    public final PddlParser.init_return init ( ) throws RecognitionException {
        PddlParser.init_return retval = new PddlParser.init_return();
        retval.start = input.LT(1);
        int init_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal400 = null;
        Token string_literal401 = null;
        Token char_literal403 = null;
        Token char_literal404 = null;
        Token string_literal405 = null;
        Token char_literal407 = null;
        ParserRuleReturnScope initEl402 = null;
        ParserRuleReturnScope goalDesc406 = null;

        Object char_literal400_tree = null;
        Object string_literal401_tree = null;
        Object char_literal403_tree = null;
        Object char_literal404_tree = null;
        Object string_literal405_tree = null;
        Object char_literal407_tree = null;
        RewriteRuleTokenStream stream_88 = new RewriteRuleTokenStream(adaptor, "token 88");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_initEl = new RewriteRuleSubtreeStream(adaptor, "rule initEl");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 68)) {
                return retval;
            }

            // Pddl.g:486:2: ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) )
            int alt71 = 2;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1: // Pddl.g:486:4: '(' ':init' ( initEl )* ')'
                {
                    char_literal400 = (Token) match(input, 68, FOLLOW_68_in_init3133);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal400);
                    }

                    string_literal401 = (Token) match(input, 88, FOLLOW_88_in_init3135);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_88.add(string_literal401);
                    }

                    // Pddl.g:486:16: ( initEl )*
                    loop70:
                    while (true) {
                        int alt70 = 2;
                        int LA70_0 = input.LA(1);
                        if ((LA70_0 == 68)) {
                            alt70 = 1;
                        }

                        switch (alt70) {
                            case 1: // Pddl.g:486:16: initEl
                            {
                                pushFollow(FOLLOW_initEl_in_init3137);
                                initEl402 = initEl();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_initEl.add(initEl402.getTree());
                                }
                            }
                            break;

                            default:
                                break loop70;
                        }
                    }

                    char_literal403 = (Token) match(input, 69, FOLLOW_69_in_init3140);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal403);
                    }

                    // AST REWRITE
                    // elements: initEl
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 487:2: -> ^( INIT ( initEl )* )
                        {
                            // Pddl.g:487:5: ^( INIT ( initEl )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INIT, "INIT"), root_1);
                                // Pddl.g:487:12: ( initEl )*
                                while (stream_initEl.hasNext()) {
                                    adaptor.addChild(root_1, stream_initEl.nextTree());
                                }
                                stream_initEl.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:488:4: '(' ':init' goalDesc ')'
                {
                    char_literal404 = (Token) match(input, 68, FOLLOW_68_in_init3155);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal404);
                    }

                    string_literal405 = (Token) match(input, 88, FOLLOW_88_in_init3157);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_88.add(string_literal405);
                    }

                    pushFollow(FOLLOW_goalDesc_in_init3159);
                    goalDesc406 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc406.getTree());
                    }
                    char_literal407 = (Token) match(input, 69, FOLLOW_69_in_init3161);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal407);
                    }

                    // AST REWRITE
                    // elements: goalDesc
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 488:29: -> ^( FORMULAINIT goalDesc )
                        {
                            // Pddl.g:488:32: ^( FORMULAINIT goalDesc )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FORMULAINIT, "FORMULAINIT"), root_1);
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 68, init_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "initEl"
    // Pddl.g:491:1: initEl : ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) );
    public final PddlParser.initEl_return initEl ( ) throws RecognitionException {
        PddlParser.initEl_return retval = new PddlParser.initEl_return();
        retval.start = input.LT(1);
        int initEl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal409 = null;
        Token char_literal410 = null;
        Token NUMBER412 = null;
        Token char_literal413 = null;
        Token char_literal414 = null;
        Token string_literal415 = null;
        Token NUMBER416 = null;
        Token char_literal418 = null;
        Token char_literal419 = null;
        Token string_literal420 = null;
        Token char_literal422 = null;
        Token char_literal423 = null;
        Token string_literal424 = null;
        Token char_literal426 = null;
        Token char_literal427 = null;
        Token string_literal428 = null;
        Token char_literal430 = null;
        ParserRuleReturnScope nameLiteral408 = null;
        ParserRuleReturnScope fHead411 = null;
        ParserRuleReturnScope nameLiteral417 = null;
        ParserRuleReturnScope atomicNameFormula421 = null;
        ParserRuleReturnScope atomicNameFormula425 = null;
        ParserRuleReturnScope nameLiteral429 = null;

        Object char_literal409_tree = null;
        Object char_literal410_tree = null;
        Object NUMBER412_tree = null;
        Object char_literal413_tree = null;
        Object char_literal414_tree = null;
        Object string_literal415_tree = null;
        Object NUMBER416_tree = null;
        Object char_literal418_tree = null;
        Object char_literal419_tree = null;
        Object string_literal420_tree = null;
        Object char_literal422_tree = null;
        Object char_literal423_tree = null;
        Object string_literal424_tree = null;
        Object char_literal426_tree = null;
        Object char_literal427_tree = null;
        Object string_literal428_tree = null;
        Object char_literal430_tree = null;
        RewriteRuleTokenStream stream_99 = new RewriteRuleTokenStream(adaptor, "token 99");
        RewriteRuleTokenStream stream_110 = new RewriteRuleTokenStream(adaptor, "token 110");
        RewriteRuleTokenStream stream_NUMBER = new RewriteRuleTokenStream(adaptor, "token NUMBER");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_129 = new RewriteRuleTokenStream(adaptor, "token 129");
        RewriteRuleTokenStream stream_141 = new RewriteRuleTokenStream(adaptor, "token 141");
        RewriteRuleTokenStream stream_130 = new RewriteRuleTokenStream(adaptor, "token 130");
        RewriteRuleSubtreeStream stream_fHead = new RewriteRuleSubtreeStream(adaptor, "rule fHead");
        RewriteRuleSubtreeStream stream_atomicNameFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicNameFormula");
        RewriteRuleSubtreeStream stream_nameLiteral = new RewriteRuleSubtreeStream(adaptor, "rule nameLiteral");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 69)) {
                return retval;
            }

            // Pddl.g:492:2: ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) )
            int alt74 = 6;
            int LA74_0 = input.LA(1);
            if ((LA74_0 == 68)) {
                switch (input.LA(2)) {
                    case NAME:
                    case 127: {
                        alt74 = 1;
                    }
                    break;
                    case 99: {
                        alt74 = 2;
                    }
                    break;
                    case 110: {
                        alt74 = 3;
                    }
                    break;
                    case 141: {
                        alt74 = 4;
                    }
                    break;
                    case 129: {
                        alt74 = 5;
                    }
                    break;
                    case 130: {
                        alt74 = 6;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 74, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 74, 0, input);
                throw nvae;
            }

            switch (alt74) {
                case 1: // Pddl.g:492:4: nameLiteral
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_nameLiteral_in_initEl3180);
                    nameLiteral408 = nameLiteral();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, nameLiteral408.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:493:4: '(' '=' fHead NUMBER ')'
                {
                    char_literal409 = (Token) match(input, 68, FOLLOW_68_in_initEl3185);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal409);
                    }

                    char_literal410 = (Token) match(input, 99, FOLLOW_99_in_initEl3187);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_99.add(char_literal410);
                    }

                    pushFollow(FOLLOW_fHead_in_initEl3189);
                    fHead411 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fHead.add(fHead411.getTree());
                    }
                    NUMBER412 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_initEl3191);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_NUMBER.add(NUMBER412);
                    }

                    char_literal413 = (Token) match(input, 69, FOLLOW_69_in_initEl3193);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal413);
                    }

                    // AST REWRITE
                    // elements: NUMBER, fHead
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 493:37: -> ^( INIT_EQ fHead NUMBER )
                        {
                            // Pddl.g:493:40: ^( INIT_EQ fHead NUMBER )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INIT_EQ, "INIT_EQ"), root_1);
                                adaptor.addChild(root_1, stream_fHead.nextTree());
                                adaptor.addChild(root_1, stream_NUMBER.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:494:4: '(' 'at' NUMBER nameLiteral ')'
                {
                    char_literal414 = (Token) match(input, 68, FOLLOW_68_in_initEl3216);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal414);
                    }

                    string_literal415 = (Token) match(input, 110, FOLLOW_110_in_initEl3218);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_110.add(string_literal415);
                    }

                    NUMBER416 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_initEl3220);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_NUMBER.add(NUMBER416);
                    }

                    pushFollow(FOLLOW_nameLiteral_in_initEl3222);
                    nameLiteral417 = nameLiteral();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_nameLiteral.add(nameLiteral417.getTree());
                    }
                    char_literal418 = (Token) match(input, 69, FOLLOW_69_in_initEl3224);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal418);
                    }

                    // AST REWRITE
                    // elements: nameLiteral, NUMBER
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 494:37: -> ^( INIT_AT NUMBER nameLiteral )
                        {
                            // Pddl.g:494:40: ^( INIT_AT NUMBER nameLiteral )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INIT_AT, "INIT_AT"), root_1);
                                adaptor.addChild(root_1, stream_NUMBER.nextNode());
                                adaptor.addChild(root_1, stream_nameLiteral.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 4: // Pddl.g:495:4: '(' 'unknown' atomicNameFormula ')'
                {
                    char_literal419 = (Token) match(input, 68, FOLLOW_68_in_initEl3240);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal419);
                    }

                    string_literal420 = (Token) match(input, 141, FOLLOW_141_in_initEl3242);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_141.add(string_literal420);
                    }

                    pushFollow(FOLLOW_atomicNameFormula_in_initEl3245);
                    atomicNameFormula421 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicNameFormula.add(atomicNameFormula421.getTree());
                    }
                    char_literal422 = (Token) match(input, 69, FOLLOW_69_in_initEl3247);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal422);
                    }

                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 495:42: -> ^( UNKNOWN atomicNameFormula )
                        {
                            // Pddl.g:495:45: ^( UNKNOWN atomicNameFormula )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(UNKNOWN, "UNKNOWN"), root_1);
                                adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 5: // Pddl.g:496:4: '(' 'oneof' ( atomicNameFormula )* ')'
                {
                    char_literal423 = (Token) match(input, 68, FOLLOW_68_in_initEl3261);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal423);
                    }

                    string_literal424 = (Token) match(input, 129, FOLLOW_129_in_initEl3263);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_129.add(string_literal424);
                    }

                    // Pddl.g:496:17: ( atomicNameFormula )*
                    loop72:
                    while (true) {
                        int alt72 = 2;
                        int LA72_0 = input.LA(1);
                        if ((LA72_0 == 68)) {
                            alt72 = 1;
                        }

                        switch (alt72) {
                            case 1: // Pddl.g:496:17: atomicNameFormula
                            {
                                pushFollow(FOLLOW_atomicNameFormula_in_initEl3266);
                                atomicNameFormula425 = atomicNameFormula();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_atomicNameFormula.add(atomicNameFormula425.getTree());
                                }
                            }
                            break;

                            default:
                                break loop72;
                        }
                    }

                    char_literal426 = (Token) match(input, 69, FOLLOW_69_in_initEl3269);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal426);
                    }

                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 496:41: -> ^( ONEOF ( atomicNameFormula )* )
                        {
                            // Pddl.g:496:44: ^( ONEOF ( atomicNameFormula )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ONEOF, "ONEOF"), root_1);
                                // Pddl.g:496:52: ( atomicNameFormula )*
                                while (stream_atomicNameFormula.hasNext()) {
                                    adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
                                }
                                stream_atomicNameFormula.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 6: // Pddl.g:497:4: '(' 'or' ( nameLiteral )* ')'
                {
                    char_literal427 = (Token) match(input, 68, FOLLOW_68_in_initEl3284);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal427);
                    }

                    string_literal428 = (Token) match(input, 130, FOLLOW_130_in_initEl3286);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_130.add(string_literal428);
                    }

                    // Pddl.g:497:14: ( nameLiteral )*
                    loop73:
                    while (true) {
                        int alt73 = 2;
                        int LA73_0 = input.LA(1);
                        if ((LA73_0 == 68)) {
                            alt73 = 1;
                        }

                        switch (alt73) {
                            case 1: // Pddl.g:497:14: nameLiteral
                            {
                                pushFollow(FOLLOW_nameLiteral_in_initEl3289);
                                nameLiteral429 = nameLiteral();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_nameLiteral.add(nameLiteral429.getTree());
                                }
                            }
                            break;

                            default:
                                break loop73;
                        }
                    }

                    char_literal430 = (Token) match(input, 69, FOLLOW_69_in_initEl3292);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal430);
                    }

                    // AST REWRITE
                    // elements: nameLiteral
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 497:32: -> ^( OR_GD ( nameLiteral )* )
                        {
                            // Pddl.g:497:35: ^( OR_GD ( nameLiteral )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OR_GD, "OR_GD"), root_1);
                                // Pddl.g:497:43: ( nameLiteral )*
                                while (stream_nameLiteral.hasNext()) {
                                    adaptor.addChild(root_1, stream_nameLiteral.nextTree());
                                }
                                stream_nameLiteral.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 69, initEl_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "nameLiteral"
    // Pddl.g:500:1: nameLiteral : ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) );
    public final PddlParser.nameLiteral_return nameLiteral ( ) throws RecognitionException {
        PddlParser.nameLiteral_return retval = new PddlParser.nameLiteral_return();
        retval.start = input.LT(1);
        int nameLiteral_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal432 = null;
        Token string_literal433 = null;
        Token char_literal435 = null;
        ParserRuleReturnScope atomicNameFormula431 = null;
        ParserRuleReturnScope atomicNameFormula434 = null;

        Object char_literal432_tree = null;
        Object string_literal433_tree = null;
        Object char_literal435_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_127 = new RewriteRuleTokenStream(adaptor, "token 127");
        RewriteRuleSubtreeStream stream_atomicNameFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicNameFormula");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 70)) {
                return retval;
            }

            // Pddl.g:501:2: ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) )
            int alt75 = 2;
            int LA75_0 = input.LA(1);
            if ((LA75_0 == 68)) {
                int LA75_1 = input.LA(2);
                if ((LA75_1 == 127)) {
                    alt75 = 2;
                } else if ((LA75_1 == NAME)) {
                    alt75 = 1;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 75, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 75, 0, input);
                throw nvae;
            }

            switch (alt75) {
                case 1: // Pddl.g:501:4: atomicNameFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3313);
                    atomicNameFormula431 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, atomicNameFormula431.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:502:4: '(' 'not' atomicNameFormula ')'
                {
                    char_literal432 = (Token) match(input, 68, FOLLOW_68_in_nameLiteral3318);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal432);
                    }

                    string_literal433 = (Token) match(input, 127, FOLLOW_127_in_nameLiteral3320);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_127.add(string_literal433);
                    }

                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3322);
                    atomicNameFormula434 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicNameFormula.add(atomicNameFormula434.getTree());
                    }
                    char_literal435 = (Token) match(input, 69, FOLLOW_69_in_nameLiteral3324);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal435);
                    }

                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 502:36: -> ^( NOT_PRED_INIT atomicNameFormula )
                        {
                            // Pddl.g:502:39: ^( NOT_PRED_INIT atomicNameFormula )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(NOT_PRED_INIT, "NOT_PRED_INIT"), root_1);
                                adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 70, nameLiteral_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "atomicNameFormula"
    // Pddl.g:505:1: atomicNameFormula : '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) ;
    public final PddlParser.atomicNameFormula_return atomicNameFormula ( ) throws RecognitionException {
        PddlParser.atomicNameFormula_return retval = new PddlParser.atomicNameFormula_return();
        retval.start = input.LT(1);
        int atomicNameFormula_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal436 = null;
        Token NAME438 = null;
        Token char_literal439 = null;
        ParserRuleReturnScope predicate437 = null;

        Object char_literal436_tree = null;
        Object NAME438_tree = null;
        Object char_literal439_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");
        RewriteRuleSubtreeStream stream_predicate = new RewriteRuleSubtreeStream(adaptor, "rule predicate");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 71)) {
                return retval;
            }

            // Pddl.g:506:2: ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) )
            // Pddl.g:506:4: '(' predicate ( NAME )* ')'
            {
                char_literal436 = (Token) match(input, 68, FOLLOW_68_in_atomicNameFormula3343);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal436);
                }

                pushFollow(FOLLOW_predicate_in_atomicNameFormula3345);
                predicate437 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_predicate.add(predicate437.getTree());
                }
                // Pddl.g:506:18: ( NAME )*
                loop76:
                while (true) {
                    int alt76 = 2;
                    int LA76_0 = input.LA(1);
                    if ((LA76_0 == NAME)) {
                        alt76 = 1;
                    }

                    switch (alt76) {
                        case 1: // Pddl.g:506:18: NAME
                        {
                            NAME438 = (Token) match(input, NAME, FOLLOW_NAME_in_atomicNameFormula3347);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_NAME.add(NAME438);
                            }

                        }
                        break;

                        default:
                            break loop76;
                    }
                }

                char_literal439 = (Token) match(input, 69, FOLLOW_69_in_atomicNameFormula3350);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal439);
                }

                // AST REWRITE
                // elements: NAME, predicate
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 506:28: -> ^( PRED_INST predicate ( NAME )* )
                    {
                        // Pddl.g:506:31: ^( PRED_INST predicate ( NAME )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRED_INST, "PRED_INST"), root_1);
                            adaptor.addChild(root_1, stream_predicate.nextTree());
                            // Pddl.g:506:53: ( NAME )*
                            while (stream_NAME.hasNext()) {
                                adaptor.addChild(root_1, stream_NAME.nextNode());
                            }
                            stream_NAME.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 71, atomicNameFormula_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "goal"
    // Pddl.g:513:1: goal : '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) ;
    public final PddlParser.goal_return goal ( ) throws RecognitionException {
        PddlParser.goal_return retval = new PddlParser.goal_return();
        retval.start = input.LT(1);
        int goal_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal440 = null;
        Token string_literal441 = null;
        Token char_literal443 = null;
        ParserRuleReturnScope goalDesc442 = null;

        Object char_literal440_tree = null;
        Object string_literal441_tree = null;
        Object char_literal443_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_87 = new RewriteRuleTokenStream(adaptor, "token 87");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 72)) {
                return retval;
            }

            // Pddl.g:513:6: ( '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) )
            // Pddl.g:513:8: '(' ':goal' goalDesc ')'
            {
                char_literal440 = (Token) match(input, 68, FOLLOW_68_in_goal3375);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal440);
                }

                string_literal441 = (Token) match(input, 87, FOLLOW_87_in_goal3377);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_87.add(string_literal441);
                }

                pushFollow(FOLLOW_goalDesc_in_goal3379);
                goalDesc442 = goalDesc();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_goalDesc.add(goalDesc442.getTree());
                }
                char_literal443 = (Token) match(input, 69, FOLLOW_69_in_goal3382);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal443);
                }

                // AST REWRITE
                // elements: goalDesc
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 513:34: -> ^( GOAL goalDesc )
                    {
                        // Pddl.g:513:37: ^( GOAL goalDesc )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(GOAL, "GOAL"), root_1);
                            adaptor.addChild(root_1, stream_goalDesc.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 72, goal_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "probConstraints"
    // Pddl.g:515:1: probConstraints : '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) ;
    public final PddlParser.probConstraints_return probConstraints ( ) throws RecognitionException {
        PddlParser.probConstraints_return retval = new PddlParser.probConstraints_return();
        retval.start = input.LT(1);
        int probConstraints_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal444 = null;
        Token string_literal445 = null;
        Token char_literal447 = null;
        ParserRuleReturnScope prefConGD446 = null;

        Object char_literal444_tree = null;
        Object string_literal445_tree = null;
        Object char_literal447_tree = null;
        RewriteRuleTokenStream stream_78 = new RewriteRuleTokenStream(adaptor, "token 78");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleSubtreeStream stream_prefConGD = new RewriteRuleSubtreeStream(adaptor, "rule prefConGD");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 73)) {
                return retval;
            }

            // Pddl.g:516:2: ( '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) )
            // Pddl.g:516:4: '(' ':constraints' prefConGD ')'
            {
                char_literal444 = (Token) match(input, 68, FOLLOW_68_in_probConstraints3400);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal444);
                }

                string_literal445 = (Token) match(input, 78, FOLLOW_78_in_probConstraints3402);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_78.add(string_literal445);
                }

                pushFollow(FOLLOW_prefConGD_in_probConstraints3405);
                prefConGD446 = prefConGD();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_prefConGD.add(prefConGD446.getTree());
                }
                char_literal447 = (Token) match(input, 69, FOLLOW_69_in_probConstraints3407);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal447);
                }

                // AST REWRITE
                // elements: prefConGD
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 517:4: -> ^( PROBLEM_CONSTRAINT prefConGD )
                    {
                        // Pddl.g:517:7: ^( PROBLEM_CONSTRAINT prefConGD )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM_CONSTRAINT, "PROBLEM_CONSTRAINT"), root_1);
                            adaptor.addChild(root_1, stream_prefConGD.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 73, probConstraints_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "prefConGD"
    // Pddl.g:520:1: prefConGD : ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD );
    public final PddlParser.prefConGD_return prefConGD ( ) throws RecognitionException {
        PddlParser.prefConGD_return retval = new PddlParser.prefConGD_return();
        retval.start = input.LT(1);
        int prefConGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal448 = null;
        Token string_literal449 = null;
        Token char_literal451 = null;
        Token char_literal452 = null;
        Token string_literal453 = null;
        Token char_literal454 = null;
        Token char_literal456 = null;
        Token char_literal458 = null;
        Token char_literal459 = null;
        Token string_literal460 = null;
        Token NAME461 = null;
        Token char_literal463 = null;
        ParserRuleReturnScope prefConGD450 = null;
        ParserRuleReturnScope typedVariableList455 = null;
        ParserRuleReturnScope prefConGD457 = null;
        ParserRuleReturnScope conGD462 = null;
        ParserRuleReturnScope conGD464 = null;

        Object char_literal448_tree = null;
        Object string_literal449_tree = null;
        Object char_literal451_tree = null;
        Object char_literal452_tree = null;
        Object string_literal453_tree = null;
        Object char_literal454_tree = null;
        Object char_literal456_tree = null;
        Object char_literal458_tree = null;
        Object char_literal459_tree = null;
        Object string_literal460_tree = null;
        Object NAME461_tree = null;
        Object char_literal463_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 74)) {
                return retval;
            }

            // Pddl.g:521:2: ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD )
            int alt79 = 4;
            int LA79_0 = input.LA(1);
            if ((LA79_0 == 68)) {
                int LA79_1 = input.LA(2);
                if ((synpred127_Pddl())) {
                    alt79 = 1;
                } else if ((synpred128_Pddl())) {
                    alt79 = 2;
                } else if ((synpred130_Pddl())) {
                    alt79 = 3;
                } else if ((true)) {
                    alt79 = 4;
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 79, 0, input);
                throw nvae;
            }

            switch (alt79) {
                case 1: // Pddl.g:521:4: '(' 'and' ( prefConGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal448 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3429);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal448_tree = (Object) adaptor.create(char_literal448);
                        adaptor.addChild(root_0, char_literal448_tree);
                    }

                    string_literal449 = (Token) match(input, 108, FOLLOW_108_in_prefConGD3431);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal449_tree = (Object) adaptor.create(string_literal449);
                        adaptor.addChild(root_0, string_literal449_tree);
                    }

                    // Pddl.g:521:14: ( prefConGD )*
                    loop77:
                    while (true) {
                        int alt77 = 2;
                        int LA77_0 = input.LA(1);
                        if ((LA77_0 == 68)) {
                            alt77 = 1;
                        }

                        switch (alt77) {
                            case 1: // Pddl.g:521:14: prefConGD
                            {
                                pushFollow(FOLLOW_prefConGD_in_prefConGD3433);
                                prefConGD450 = prefConGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, prefConGD450.getTree());
                                }

                            }
                            break;

                            default:
                                break loop77;
                        }
                    }

                    char_literal451 = (Token) match(input, 69, FOLLOW_69_in_prefConGD3436);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal451_tree = (Object) adaptor.create(char_literal451);
                        adaptor.addChild(root_0, char_literal451_tree);
                    }

                }
                break;
                case 2: // Pddl.g:522:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal452 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3441);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal452_tree = (Object) adaptor.create(char_literal452);
                        adaptor.addChild(root_0, char_literal452_tree);
                    }

                    string_literal453 = (Token) match(input, 119, FOLLOW_119_in_prefConGD3443);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal453_tree = (Object) adaptor.create(string_literal453);
                        adaptor.addChild(root_0, string_literal453_tree);
                    }

                    char_literal454 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3445);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal454_tree = (Object) adaptor.create(char_literal454);
                        adaptor.addChild(root_0, char_literal454_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_prefConGD3447);
                    typedVariableList455 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList455.getTree());
                    }

                    char_literal456 = (Token) match(input, 69, FOLLOW_69_in_prefConGD3449);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal456_tree = (Object) adaptor.create(char_literal456);
                        adaptor.addChild(root_0, char_literal456_tree);
                    }

                    pushFollow(FOLLOW_prefConGD_in_prefConGD3451);
                    prefConGD457 = prefConGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, prefConGD457.getTree());
                    }

                    char_literal458 = (Token) match(input, 69, FOLLOW_69_in_prefConGD3453);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal458_tree = (Object) adaptor.create(char_literal458);
                        adaptor.addChild(root_0, char_literal458_tree);
                    }

                }
                break;
                case 3: // Pddl.g:523:4: '(' 'preference' ( NAME )? conGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal459 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3458);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal459_tree = (Object) adaptor.create(char_literal459);
                        adaptor.addChild(root_0, char_literal459_tree);
                    }

                    string_literal460 = (Token) match(input, 132, FOLLOW_132_in_prefConGD3460);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal460_tree = (Object) adaptor.create(string_literal460);
                        adaptor.addChild(root_0, string_literal460_tree);
                    }

                    // Pddl.g:523:21: ( NAME )?
                    int alt78 = 2;
                    int LA78_0 = input.LA(1);
                    if ((LA78_0 == NAME)) {
                        alt78 = 1;
                    }
                    switch (alt78) {
                        case 1: // Pddl.g:523:21: NAME
                        {
                            NAME461 = (Token) match(input, NAME, FOLLOW_NAME_in_prefConGD3462);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                NAME461_tree = (Object) adaptor.create(NAME461);
                                adaptor.addChild(root_0, NAME461_tree);
                            }

                        }
                        break;

                    }

                    pushFollow(FOLLOW_conGD_in_prefConGD3465);
                    conGD462 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD462.getTree());
                    }

                    char_literal463 = (Token) match(input, 69, FOLLOW_69_in_prefConGD3467);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal463_tree = (Object) adaptor.create(char_literal463);
                        adaptor.addChild(root_0, char_literal463_tree);
                    }

                }
                break;
                case 4: // Pddl.g:524:4: conGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_conGD_in_prefConGD3472);
                    conGD464 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD464.getTree());
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 74, prefConGD_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "metricSpec"
    // Pddl.g:527:1: metricSpec : '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) ;
    public final PddlParser.metricSpec_return metricSpec ( ) throws RecognitionException {
        PddlParser.metricSpec_return retval = new PddlParser.metricSpec_return();
        retval.start = input.LT(1);
        int metricSpec_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal465 = null;
        Token string_literal466 = null;
        Token char_literal469 = null;
        ParserRuleReturnScope optimization467 = null;
        ParserRuleReturnScope metricFExp468 = null;

        Object char_literal465_tree = null;
        Object string_literal466_tree = null;
        Object char_literal469_tree = null;
        RewriteRuleTokenStream stream_89 = new RewriteRuleTokenStream(adaptor, "token 89");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleSubtreeStream stream_metricFExp = new RewriteRuleSubtreeStream(adaptor, "rule metricFExp");
        RewriteRuleSubtreeStream stream_optimization = new RewriteRuleSubtreeStream(adaptor, "rule optimization");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 75)) {
                return retval;
            }

            // Pddl.g:528:2: ( '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) )
            // Pddl.g:528:4: '(' ':metric' optimization metricFExp ')'
            {
                char_literal465 = (Token) match(input, 68, FOLLOW_68_in_metricSpec3483);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal465);
                }

                string_literal466 = (Token) match(input, 89, FOLLOW_89_in_metricSpec3485);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_89.add(string_literal466);
                }

                pushFollow(FOLLOW_optimization_in_metricSpec3487);
                optimization467 = optimization();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_optimization.add(optimization467.getTree());
                }
                pushFollow(FOLLOW_metricFExp_in_metricSpec3489);
                metricFExp468 = metricFExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_metricFExp.add(metricFExp468.getTree());
                }
                char_literal469 = (Token) match(input, 69, FOLLOW_69_in_metricSpec3491);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_69.add(char_literal469);
                }

                // AST REWRITE
                // elements: optimization, metricFExp
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 529:4: -> ^( PROBLEM_METRIC optimization metricFExp )
                    {
                        // Pddl.g:529:7: ^( PROBLEM_METRIC optimization metricFExp )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM_METRIC, "PROBLEM_METRIC"), root_1);
                            adaptor.addChild(root_1, stream_optimization.nextTree());
                            adaptor.addChild(root_1, stream_metricFExp.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 75, metricSpec_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "optimization"
    // Pddl.g:532:1: optimization : ( 'minimize' | 'maximize' );
    public final PddlParser.optimization_return optimization ( ) throws RecognitionException {
        PddlParser.optimization_return retval = new PddlParser.optimization_return();
        retval.start = input.LT(1);
        int optimization_StartIndex = input.index();

        Object root_0 = null;

        Token set470 = null;

        Object set470_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 76)) {
                return retval;
            }

            // Pddl.g:532:14: ( 'minimize' | 'maximize' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set470 = input.LT(1);
                if ((input.LA(1) >= 125 && input.LA(1) <= 126)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set470));
                    }
                    state.errorRecovery = false;
                    state.failed = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 76, optimization_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "metricFExp"
    // Pddl.g:534:1: metricFExp : ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' );
    public final PddlParser.metricFExp_return metricFExp ( ) throws RecognitionException {
        PddlParser.metricFExp_return retval = new PddlParser.metricFExp_return();
        retval.start = input.LT(1);
        int metricFExp_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal471 = null;
        Token char_literal475 = null;
        Token char_literal476 = null;
        Token char_literal480 = null;
        Token char_literal481 = null;
        Token char_literal482 = null;
        Token char_literal484 = null;
        Token NUMBER485 = null;
        Token char_literal487 = null;
        Token string_literal488 = null;
        Token NAME489 = null;
        Token char_literal490 = null;
        ParserRuleReturnScope binaryOp472 = null;
        ParserRuleReturnScope metricFExp473 = null;
        ParserRuleReturnScope metricFExp474 = null;
        ParserRuleReturnScope multiOp477 = null;
        ParserRuleReturnScope metricFExp478 = null;
        ParserRuleReturnScope metricFExp479 = null;
        ParserRuleReturnScope metricFExp483 = null;
        ParserRuleReturnScope fHead486 = null;

        Object char_literal471_tree = null;
        Object char_literal475_tree = null;
        Object char_literal476_tree = null;
        Object char_literal480_tree = null;
        Object char_literal481_tree = null;
        Object char_literal482_tree = null;
        Object char_literal484_tree = null;
        Object NUMBER485_tree = null;
        Object char_literal487_tree = null;
        Object string_literal488_tree = null;
        Object NAME489_tree = null;
        Object char_literal490_tree = null;
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_69 = new RewriteRuleTokenStream(adaptor, "token 69");
        RewriteRuleTokenStream stream_72 = new RewriteRuleTokenStream(adaptor, "token 72");
        RewriteRuleSubtreeStream stream_binaryOp = new RewriteRuleSubtreeStream(adaptor, "rule binaryOp");
        RewriteRuleSubtreeStream stream_metricFExp = new RewriteRuleSubtreeStream(adaptor, "rule metricFExp");
        RewriteRuleSubtreeStream stream_multiOp = new RewriteRuleSubtreeStream(adaptor, "rule multiOp");

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 77)) {
                return retval;
            }

            // Pddl.g:535:2: ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' )
            int alt81 = 6;
            switch (input.LA(1)) {
                case 68: {
                    int LA81_1 = input.LA(2);
                    if ((synpred132_Pddl())) {
                        alt81 = 1;
                    } else if ((synpred134_Pddl())) {
                        alt81 = 2;
                    } else if ((synpred135_Pddl())) {
                        alt81 = 3;
                    } else if ((synpred137_Pddl())) {
                        alt81 = 5;
                    } else if ((true)) {
                        alt81 = 6;
                    }

                }
                break;
                case NUMBER: {
                    alt81 = 4;
                }
                break;
                case NAME:
                case 67: {
                    alt81 = 5;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 81, 0, input);
                    throw nvae;
            }
            switch (alt81) {
                case 1: // Pddl.g:535:4: '(' binaryOp metricFExp metricFExp ')'
                {
                    char_literal471 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3528);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal471);
                    }

                    pushFollow(FOLLOW_binaryOp_in_metricFExp3530);
                    binaryOp472 = binaryOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_binaryOp.add(binaryOp472.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3532);
                    metricFExp473 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp473.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3534);
                    metricFExp474 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp474.getTree());
                    }
                    char_literal475 = (Token) match(input, 69, FOLLOW_69_in_metricFExp3536);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal475);
                    }

                    // AST REWRITE
                    // elements: binaryOp, metricFExp, metricFExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 536:6: -> ^( BINARY_OP binaryOp metricFExp metricFExp )
                        {
                            // Pddl.g:536:9: ^( BINARY_OP binaryOp metricFExp metricFExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(BINARY_OP, "BINARY_OP"), root_1);
                                adaptor.addChild(root_1, stream_binaryOp.nextTree());
                                adaptor.addChild(root_1, stream_metricFExp.nextTree());
                                adaptor.addChild(root_1, stream_metricFExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 2: // Pddl.g:537:4: '(' multiOp metricFExp ( metricFExp )+ ')'
                {
                    char_literal476 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3558);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal476);
                    }

                    pushFollow(FOLLOW_multiOp_in_metricFExp3560);
                    multiOp477 = multiOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_multiOp.add(multiOp477.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3562);
                    metricFExp478 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp478.getTree());
                    }
                    // Pddl.g:537:27: ( metricFExp )+
                    int cnt80 = 0;
                    loop80:
                    while (true) {
                        int alt80 = 2;
                        int LA80_0 = input.LA(1);
                        if ((LA80_0 == NAME || LA80_0 == NUMBER || (LA80_0 >= 67 && LA80_0 <= 68))) {
                            alt80 = 1;
                        }

                        switch (alt80) {
                            case 1: // Pddl.g:537:27: metricFExp
                            {
                                pushFollow(FOLLOW_metricFExp_in_metricFExp3564);
                                metricFExp479 = metricFExp();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_metricFExp.add(metricFExp479.getTree());
                                }
                            }
                            break;

                            default:
                                if (cnt80 >= 1) {
                                    break loop80;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(80, input);
                                throw eee;
                        }
                        cnt80++;
                    }

                    char_literal480 = (Token) match(input, 69, FOLLOW_69_in_metricFExp3567);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal480);
                    }

                    // AST REWRITE
                    // elements: metricFExp, multiOp, metricFExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 538:7: -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
                        {
                            // Pddl.g:538:10: ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(MULTI_OP, "MULTI_OP"), root_1);
                                adaptor.addChild(root_1, stream_multiOp.nextTree());
                                adaptor.addChild(root_1, stream_metricFExp.nextTree());
                                if (!(stream_metricFExp.hasNext())) {
                                    throw new RewriteEarlyExitException();
                                }
                                while (stream_metricFExp.hasNext()) {
                                    adaptor.addChild(root_1, stream_metricFExp.nextTree());
                                }
                                stream_metricFExp.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 3: // Pddl.g:539:4: '(' '-' metricFExp ')'
                {
                    char_literal481 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3591);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal481);
                    }

                    char_literal482 = (Token) match(input, 72, FOLLOW_72_in_metricFExp3593);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_72.add(char_literal482);
                    }

                    pushFollow(FOLLOW_metricFExp_in_metricFExp3595);
                    metricFExp483 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp483.getTree());
                    }
                    char_literal484 = (Token) match(input, 69, FOLLOW_69_in_metricFExp3597);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_69.add(char_literal484);
                    }

                    // AST REWRITE
                    // elements: metricFExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 540:4: -> ^( MINUS_OP metricFExp )
                        {
                            // Pddl.g:540:7: ^( MINUS_OP metricFExp )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(MINUS_OP, "MINUS_OP"), root_1);
                                adaptor.addChild(root_1, stream_metricFExp.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }

                        retval.tree = root_0;
                    }

                }
                break;
                case 4: // Pddl.g:541:4: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER485 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_metricFExp3614);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER485_tree = (Object) adaptor.create(NUMBER485);
                        adaptor.addChild(root_0, NUMBER485_tree);
                    }

                }
                break;
                case 5: // Pddl.g:542:4: fHead
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fHead_in_metricFExp3619);
                    fHead486 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead486.getTree());
                    }

                }
                break;
                case 6: // Pddl.g:544:4: '(' 'is-violated' NAME ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal487 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3634);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal487_tree = (Object) adaptor.create(char_literal487);
                        adaptor.addChild(root_0, char_literal487_tree);
                    }

                    string_literal488 = (Token) match(input, 124, FOLLOW_124_in_metricFExp3636);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal488_tree = (Object) adaptor.create(string_literal488);
                        adaptor.addChild(root_0, string_literal488_tree);
                    }

                    NAME489 = (Token) match(input, NAME, FOLLOW_NAME_in_metricFExp3638);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NAME489_tree = (Object) adaptor.create(NAME489);
                        adaptor.addChild(root_0, NAME489_tree);
                    }

                    char_literal490 = (Token) match(input, 69, FOLLOW_69_in_metricFExp3640);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal490_tree = (Object) adaptor.create(char_literal490);
                        adaptor.addChild(root_0, char_literal490_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 77, metricFExp_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start "conGD"
    // Pddl.g:549:1: conGD : ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' );
    public final PddlParser.conGD_return conGD ( ) throws RecognitionException {
        PddlParser.conGD_return retval = new PddlParser.conGD_return();
        retval.start = input.LT(1);
        int conGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal491 = null;
        Token string_literal492 = null;
        Token char_literal494 = null;
        Token char_literal495 = null;
        Token string_literal496 = null;
        Token char_literal497 = null;
        Token char_literal499 = null;
        Token char_literal501 = null;
        Token char_literal502 = null;
        Token string_literal503 = null;
        Token string_literal504 = null;
        Token char_literal506 = null;
        Token char_literal507 = null;
        Token string_literal508 = null;
        Token char_literal510 = null;
        Token char_literal511 = null;
        Token string_literal512 = null;
        Token char_literal514 = null;
        Token char_literal515 = null;
        Token string_literal516 = null;
        Token NUMBER517 = null;
        Token char_literal519 = null;
        Token char_literal520 = null;
        Token string_literal521 = null;
        Token char_literal523 = null;
        Token char_literal524 = null;
        Token string_literal525 = null;
        Token char_literal528 = null;
        Token char_literal529 = null;
        Token string_literal530 = null;
        Token char_literal533 = null;
        Token char_literal534 = null;
        Token string_literal535 = null;
        Token NUMBER536 = null;
        Token char_literal539 = null;
        Token char_literal540 = null;
        Token string_literal541 = null;
        Token NUMBER542 = null;
        Token NUMBER543 = null;
        Token char_literal545 = null;
        Token char_literal546 = null;
        Token string_literal547 = null;
        Token NUMBER548 = null;
        Token char_literal550 = null;
        ParserRuleReturnScope conGD493 = null;
        ParserRuleReturnScope typedVariableList498 = null;
        ParserRuleReturnScope conGD500 = null;
        ParserRuleReturnScope goalDesc505 = null;
        ParserRuleReturnScope goalDesc509 = null;
        ParserRuleReturnScope goalDesc513 = null;
        ParserRuleReturnScope goalDesc518 = null;
        ParserRuleReturnScope goalDesc522 = null;
        ParserRuleReturnScope goalDesc526 = null;
        ParserRuleReturnScope goalDesc527 = null;
        ParserRuleReturnScope goalDesc531 = null;
        ParserRuleReturnScope goalDesc532 = null;
        ParserRuleReturnScope goalDesc537 = null;
        ParserRuleReturnScope goalDesc538 = null;
        ParserRuleReturnScope goalDesc544 = null;
        ParserRuleReturnScope goalDesc549 = null;

        Object char_literal491_tree = null;
        Object string_literal492_tree = null;
        Object char_literal494_tree = null;
        Object char_literal495_tree = null;
        Object string_literal496_tree = null;
        Object char_literal497_tree = null;
        Object char_literal499_tree = null;
        Object char_literal501_tree = null;
        Object char_literal502_tree = null;
        Object string_literal503_tree = null;
        Object string_literal504_tree = null;
        Object char_literal506_tree = null;
        Object char_literal507_tree = null;
        Object string_literal508_tree = null;
        Object char_literal510_tree = null;
        Object char_literal511_tree = null;
        Object string_literal512_tree = null;
        Object char_literal514_tree = null;
        Object char_literal515_tree = null;
        Object string_literal516_tree = null;
        Object NUMBER517_tree = null;
        Object char_literal519_tree = null;
        Object char_literal520_tree = null;
        Object string_literal521_tree = null;
        Object char_literal523_tree = null;
        Object char_literal524_tree = null;
        Object string_literal525_tree = null;
        Object char_literal528_tree = null;
        Object char_literal529_tree = null;
        Object string_literal530_tree = null;
        Object char_literal533_tree = null;
        Object char_literal534_tree = null;
        Object string_literal535_tree = null;
        Object NUMBER536_tree = null;
        Object char_literal539_tree = null;
        Object char_literal540_tree = null;
        Object string_literal541_tree = null;
        Object NUMBER542_tree = null;
        Object NUMBER543_tree = null;
        Object char_literal545_tree = null;
        Object char_literal546_tree = null;
        Object string_literal547_tree = null;
        Object NUMBER548_tree = null;
        Object char_literal550_tree = null;

        try {
            if (state.backtracking > 0 && alreadyParsedRule(input, 78)) {
                return retval;
            }

            // Pddl.g:550:2: ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' )
            int alt83 = 12;
            int LA83_0 = input.LA(1);
            if ((LA83_0 == 68)) {
                switch (input.LA(2)) {
                    case 108: {
                        alt83 = 1;
                    }
                    break;
                    case 119: {
                        alt83 = 2;
                    }
                    break;
                    case 110: {
                        alt83 = 3;
                    }
                    break;
                    case 106: {
                        alt83 = 4;
                    }
                    break;
                    case 137: {
                        alt83 = 5;
                    }
                    break;
                    case 143: {
                        alt83 = 6;
                    }
                    break;
                    case 111: {
                        alt83 = 7;
                    }
                    break;
                    case 138: {
                        alt83 = 8;
                    }
                    break;
                    case 139: {
                        alt83 = 9;
                    }
                    break;
                    case 107: {
                        alt83 = 10;
                    }
                    break;
                    case 121: {
                        alt83 = 11;
                    }
                    break;
                    case 120: {
                        alt83 = 12;
                    }
                    break;
                    default:
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae
                                    = new NoViableAltException("", 83, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                }
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 83, 0, input);
                throw nvae;
            }

            switch (alt83) {
                case 1: // Pddl.g:550:4: '(' 'and' ( conGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal491 = (Token) match(input, 68, FOLLOW_68_in_conGD3654);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal491_tree = (Object) adaptor.create(char_literal491);
                        adaptor.addChild(root_0, char_literal491_tree);
                    }

                    string_literal492 = (Token) match(input, 108, FOLLOW_108_in_conGD3656);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal492_tree = (Object) adaptor.create(string_literal492);
                        adaptor.addChild(root_0, string_literal492_tree);
                    }

                    // Pddl.g:550:14: ( conGD )*
                    loop82:
                    while (true) {
                        int alt82 = 2;
                        int LA82_0 = input.LA(1);
                        if ((LA82_0 == 68)) {
                            alt82 = 1;
                        }

                        switch (alt82) {
                            case 1: // Pddl.g:550:14: conGD
                            {
                                pushFollow(FOLLOW_conGD_in_conGD3658);
                                conGD493 = conGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, conGD493.getTree());
                                }

                            }
                            break;

                            default:
                                break loop82;
                        }
                    }

                    char_literal494 = (Token) match(input, 69, FOLLOW_69_in_conGD3661);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal494_tree = (Object) adaptor.create(char_literal494);
                        adaptor.addChild(root_0, char_literal494_tree);
                    }

                }
                break;
                case 2: // Pddl.g:551:4: '(' 'forall' '(' typedVariableList ')' conGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal495 = (Token) match(input, 68, FOLLOW_68_in_conGD3666);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal495_tree = (Object) adaptor.create(char_literal495);
                        adaptor.addChild(root_0, char_literal495_tree);
                    }

                    string_literal496 = (Token) match(input, 119, FOLLOW_119_in_conGD3668);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal496_tree = (Object) adaptor.create(string_literal496);
                        adaptor.addChild(root_0, string_literal496_tree);
                    }

                    char_literal497 = (Token) match(input, 68, FOLLOW_68_in_conGD3670);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal497_tree = (Object) adaptor.create(char_literal497);
                        adaptor.addChild(root_0, char_literal497_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_conGD3672);
                    typedVariableList498 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList498.getTree());
                    }

                    char_literal499 = (Token) match(input, 69, FOLLOW_69_in_conGD3674);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal499_tree = (Object) adaptor.create(char_literal499);
                        adaptor.addChild(root_0, char_literal499_tree);
                    }

                    pushFollow(FOLLOW_conGD_in_conGD3676);
                    conGD500 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD500.getTree());
                    }

                    char_literal501 = (Token) match(input, 69, FOLLOW_69_in_conGD3678);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal501_tree = (Object) adaptor.create(char_literal501);
                        adaptor.addChild(root_0, char_literal501_tree);
                    }

                }
                break;
                case 3: // Pddl.g:552:4: '(' 'at' 'end' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal502 = (Token) match(input, 68, FOLLOW_68_in_conGD3683);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal502_tree = (Object) adaptor.create(char_literal502);
                        adaptor.addChild(root_0, char_literal502_tree);
                    }

                    string_literal503 = (Token) match(input, 110, FOLLOW_110_in_conGD3685);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal503_tree = (Object) adaptor.create(string_literal503);
                        adaptor.addChild(root_0, string_literal503_tree);
                    }

                    string_literal504 = (Token) match(input, 117, FOLLOW_117_in_conGD3687);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal504_tree = (Object) adaptor.create(string_literal504);
                        adaptor.addChild(root_0, string_literal504_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3689);
                    goalDesc505 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc505.getTree());
                    }

                    char_literal506 = (Token) match(input, 69, FOLLOW_69_in_conGD3691);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal506_tree = (Object) adaptor.create(char_literal506);
                        adaptor.addChild(root_0, char_literal506_tree);
                    }

                }
                break;
                case 4: // Pddl.g:553:25: '(' 'always' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal507 = (Token) match(input, 68, FOLLOW_68_in_conGD3717);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal507_tree = (Object) adaptor.create(char_literal507);
                        adaptor.addChild(root_0, char_literal507_tree);
                    }

                    string_literal508 = (Token) match(input, 106, FOLLOW_106_in_conGD3719);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal508_tree = (Object) adaptor.create(string_literal508);
                        adaptor.addChild(root_0, string_literal508_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3721);
                    goalDesc509 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc509.getTree());
                    }

                    char_literal510 = (Token) match(input, 69, FOLLOW_69_in_conGD3723);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal510_tree = (Object) adaptor.create(char_literal510);
                        adaptor.addChild(root_0, char_literal510_tree);
                    }

                }
                break;
                case 5: // Pddl.g:554:4: '(' 'sometime' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal511 = (Token) match(input, 68, FOLLOW_68_in_conGD3728);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal511_tree = (Object) adaptor.create(char_literal511);
                        adaptor.addChild(root_0, char_literal511_tree);
                    }

                    string_literal512 = (Token) match(input, 137, FOLLOW_137_in_conGD3730);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal512_tree = (Object) adaptor.create(string_literal512);
                        adaptor.addChild(root_0, string_literal512_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3732);
                    goalDesc513 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc513.getTree());
                    }

                    char_literal514 = (Token) match(input, 69, FOLLOW_69_in_conGD3734);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal514_tree = (Object) adaptor.create(char_literal514);
                        adaptor.addChild(root_0, char_literal514_tree);
                    }

                }
                break;
                case 6: // Pddl.g:555:5: '(' 'within' NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal515 = (Token) match(input, 68, FOLLOW_68_in_conGD3740);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal515_tree = (Object) adaptor.create(char_literal515);
                        adaptor.addChild(root_0, char_literal515_tree);
                    }

                    string_literal516 = (Token) match(input, 143, FOLLOW_143_in_conGD3742);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal516_tree = (Object) adaptor.create(string_literal516);
                        adaptor.addChild(root_0, string_literal516_tree);
                    }

                    NUMBER517 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3744);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER517_tree = (Object) adaptor.create(NUMBER517);
                        adaptor.addChild(root_0, NUMBER517_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3746);
                    goalDesc518 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc518.getTree());
                    }

                    char_literal519 = (Token) match(input, 69, FOLLOW_69_in_conGD3748);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal519_tree = (Object) adaptor.create(char_literal519);
                        adaptor.addChild(root_0, char_literal519_tree);
                    }

                }
                break;
                case 7: // Pddl.g:556:4: '(' 'at-most-once' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal520 = (Token) match(input, 68, FOLLOW_68_in_conGD3753);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal520_tree = (Object) adaptor.create(char_literal520);
                        adaptor.addChild(root_0, char_literal520_tree);
                    }

                    string_literal521 = (Token) match(input, 111, FOLLOW_111_in_conGD3755);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal521_tree = (Object) adaptor.create(string_literal521);
                        adaptor.addChild(root_0, string_literal521_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3757);
                    goalDesc522 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc522.getTree());
                    }

                    char_literal523 = (Token) match(input, 69, FOLLOW_69_in_conGD3759);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal523_tree = (Object) adaptor.create(char_literal523);
                        adaptor.addChild(root_0, char_literal523_tree);
                    }

                }
                break;
                case 8: // Pddl.g:557:4: '(' 'sometime-after' goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal524 = (Token) match(input, 68, FOLLOW_68_in_conGD3764);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal524_tree = (Object) adaptor.create(char_literal524);
                        adaptor.addChild(root_0, char_literal524_tree);
                    }

                    string_literal525 = (Token) match(input, 138, FOLLOW_138_in_conGD3766);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal525_tree = (Object) adaptor.create(string_literal525);
                        adaptor.addChild(root_0, string_literal525_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3768);
                    goalDesc526 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc526.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3770);
                    goalDesc527 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc527.getTree());
                    }

                    char_literal528 = (Token) match(input, 69, FOLLOW_69_in_conGD3772);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal528_tree = (Object) adaptor.create(char_literal528);
                        adaptor.addChild(root_0, char_literal528_tree);
                    }

                }
                break;
                case 9: // Pddl.g:558:4: '(' 'sometime-before' goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal529 = (Token) match(input, 68, FOLLOW_68_in_conGD3777);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal529_tree = (Object) adaptor.create(char_literal529);
                        adaptor.addChild(root_0, char_literal529_tree);
                    }

                    string_literal530 = (Token) match(input, 139, FOLLOW_139_in_conGD3779);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal530_tree = (Object) adaptor.create(string_literal530);
                        adaptor.addChild(root_0, string_literal530_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3781);
                    goalDesc531 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc531.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3783);
                    goalDesc532 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc532.getTree());
                    }

                    char_literal533 = (Token) match(input, 69, FOLLOW_69_in_conGD3785);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal533_tree = (Object) adaptor.create(char_literal533);
                        adaptor.addChild(root_0, char_literal533_tree);
                    }

                }
                break;
                case 10: // Pddl.g:559:4: '(' 'always-within' NUMBER goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal534 = (Token) match(input, 68, FOLLOW_68_in_conGD3790);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal534_tree = (Object) adaptor.create(char_literal534);
                        adaptor.addChild(root_0, char_literal534_tree);
                    }

                    string_literal535 = (Token) match(input, 107, FOLLOW_107_in_conGD3792);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal535_tree = (Object) adaptor.create(string_literal535);
                        adaptor.addChild(root_0, string_literal535_tree);
                    }

                    NUMBER536 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3794);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER536_tree = (Object) adaptor.create(NUMBER536);
                        adaptor.addChild(root_0, NUMBER536_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3796);
                    goalDesc537 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc537.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3798);
                    goalDesc538 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc538.getTree());
                    }

                    char_literal539 = (Token) match(input, 69, FOLLOW_69_in_conGD3800);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal539_tree = (Object) adaptor.create(char_literal539);
                        adaptor.addChild(root_0, char_literal539_tree);
                    }

                }
                break;
                case 11: // Pddl.g:560:4: '(' 'hold-during' NUMBER NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal540 = (Token) match(input, 68, FOLLOW_68_in_conGD3805);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal540_tree = (Object) adaptor.create(char_literal540);
                        adaptor.addChild(root_0, char_literal540_tree);
                    }

                    string_literal541 = (Token) match(input, 121, FOLLOW_121_in_conGD3807);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal541_tree = (Object) adaptor.create(string_literal541);
                        adaptor.addChild(root_0, string_literal541_tree);
                    }

                    NUMBER542 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3809);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER542_tree = (Object) adaptor.create(NUMBER542);
                        adaptor.addChild(root_0, NUMBER542_tree);
                    }

                    NUMBER543 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3811);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER543_tree = (Object) adaptor.create(NUMBER543);
                        adaptor.addChild(root_0, NUMBER543_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3813);
                    goalDesc544 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc544.getTree());
                    }

                    char_literal545 = (Token) match(input, 69, FOLLOW_69_in_conGD3815);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal545_tree = (Object) adaptor.create(char_literal545);
                        adaptor.addChild(root_0, char_literal545_tree);
                    }

                }
                break;
                case 12: // Pddl.g:561:4: '(' 'hold-after' NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal546 = (Token) match(input, 68, FOLLOW_68_in_conGD3820);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal546_tree = (Object) adaptor.create(char_literal546);
                        adaptor.addChild(root_0, char_literal546_tree);
                    }

                    string_literal547 = (Token) match(input, 120, FOLLOW_120_in_conGD3822);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal547_tree = (Object) adaptor.create(string_literal547);
                        adaptor.addChild(root_0, string_literal547_tree);
                    }

                    NUMBER548 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3824);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER548_tree = (Object) adaptor.create(NUMBER548);
                        adaptor.addChild(root_0, NUMBER548_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3826);
                    goalDesc549 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc549.getTree());
                    }

                    char_literal550 = (Token) match(input, 69, FOLLOW_69_in_conGD3828);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal550_tree = (Object) adaptor.create(char_literal550);
                        adaptor.addChild(root_0, char_literal550_tree);
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {
                retval.tree = (Object) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
            if (state.backtracking > 0) {
                memoize(input, 78, conGD_StartIndex);
            }

        }
        return retval;
    }

    // $ANTLR start synpred18_Pddl
    public final void synpred18_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:168:5: ( atomicFunctionSkeleton )
        // Pddl.g:168:5: atomicFunctionSkeleton
        {
            pushFollow(FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl841);
            atomicFunctionSkeleton();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred50_Pddl
    public final void synpred50_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:293:7: ( fComp )
        // Pddl.g:293:7: fComp
        {
            pushFollow(FOLLOW_fComp_in_synpred50_Pddl1768);
            fComp();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred53_Pddl
    public final void synpred53_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:316:28: ( typedVariableList )
        // Pddl.g:316:28: typedVariableList
        {
            pushFollow(FOLLOW_typedVariableList_in_synpred53_Pddl1927);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred66_Pddl
    public final void synpred66_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:356:4: ( '(' binaryOp fExp fExp2 ')' )
        // Pddl.g:356:4: '(' binaryOp fExp fExp2 ')'
        {
            match(input, 68, FOLLOW_68_in_synpred66_Pddl2183);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_binaryOp_in_synpred66_Pddl2185);
            binaryOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred66_Pddl2187);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp2_in_synpred66_Pddl2189);
            fExp2();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred66_Pddl2191);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred67_Pddl
    public final void synpred67_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:357:4: ( '(' '-' fExp ')' )
        // Pddl.g:357:4: '(' '-' fExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred67_Pddl2208);
            if (state.failed) {
                return;
            }

            match(input, 72, FOLLOW_72_in_synpred67_Pddl2210);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred67_Pddl2212);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred67_Pddl2214);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred68_Pddl
    public final void synpred68_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:358:4: ( '(' 'sin' fExp ')' )
        // Pddl.g:358:4: '(' 'sin' fExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred68_Pddl2227);
            if (state.failed) {
                return;
            }

            match(input, 136, FOLLOW_136_in_synpred68_Pddl2229);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred68_Pddl2231);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred68_Pddl2233);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred69_Pddl
    public final void synpred69_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:359:4: ( '(' 'cos' fExp ')' )
        // Pddl.g:359:4: '(' 'cos' fExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred69_Pddl2246);
            if (state.failed) {
                return;
            }

            match(input, 112, FOLLOW_112_in_synpred69_Pddl2248);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred69_Pddl2250);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred69_Pddl2252);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred70_Pddl
    public final void synpred70_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:360:4: ( '(' 'abs' fExp ')' )
        // Pddl.g:360:4: '(' 'abs' fExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred70_Pddl2265);
            if (state.failed) {
                return;
            }

            match(input, 104, FOLLOW_104_in_synpred70_Pddl2267);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred70_Pddl2269);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred70_Pddl2271);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred100_Pddl
    public final void synpred100_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:427:12: ( NUMBER )
        // Pddl.g:427:12: NUMBER
        {
            match(input, NUMBER, FOLLOW_NUMBER_in_synpred100_Pddl2714);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred102_Pddl
    public final void synpred102_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:430:4: ( '(' 'and' ( daEffect )* ')' )
        // Pddl.g:430:4: '(' 'and' ( daEffect )* ')'
        {
            match(input, 68, FOLLOW_68_in_synpred102_Pddl2728);
            if (state.failed) {
                return;
            }

            match(input, 108, FOLLOW_108_in_synpred102_Pddl2730);
            if (state.failed) {
                return;
            }

            // Pddl.g:430:14: ( daEffect )*
            loop101:
            while (true) {
                int alt101 = 2;
                int LA101_0 = input.LA(1);
                if ((LA101_0 == 68)) {
                    alt101 = 1;
                }

                switch (alt101) {
                    case 1: // Pddl.g:430:14: daEffect
                    {
                        pushFollow(FOLLOW_daEffect_in_synpred102_Pddl2732);
                        daEffect();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        break loop101;
                }
            }

            match(input, 69, FOLLOW_69_in_synpred102_Pddl2735);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred103_Pddl
    public final void synpred103_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:431:4: ( timedEffect )
        // Pddl.g:431:4: timedEffect
        {
            pushFollow(FOLLOW_timedEffect_in_synpred103_Pddl2740);
            timedEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred104_Pddl
    public final void synpred104_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:432:4: ( '(' 'forall' '(' typedVariableList ')' daEffect ')' )
        // Pddl.g:432:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
        {
            match(input, 68, FOLLOW_68_in_synpred104_Pddl2745);
            if (state.failed) {
                return;
            }

            match(input, 119, FOLLOW_119_in_synpred104_Pddl2747);
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred104_Pddl2749);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_typedVariableList_in_synpred104_Pddl2751);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred104_Pddl2753);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daEffect_in_synpred104_Pddl2755);
            daEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred104_Pddl2757);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred105_Pddl
    public final void synpred105_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:433:4: ( '(' 'when' daGD timedEffect ')' )
        // Pddl.g:433:4: '(' 'when' daGD timedEffect ')'
        {
            match(input, 68, FOLLOW_68_in_synpred105_Pddl2762);
            if (state.failed) {
                return;
            }

            match(input, 142, FOLLOW_142_in_synpred105_Pddl2764);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daGD_in_synpred105_Pddl2766);
            daGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timedEffect_in_synpred105_Pddl2768);
            timedEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred105_Pddl2770);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred106_Pddl
    public final void synpred106_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:438:4: ( '(' 'at' timeSpecifier daEffect ')' )
        // Pddl.g:438:4: '(' 'at' timeSpecifier daEffect ')'
        {
            match(input, 68, FOLLOW_68_in_synpred106_Pddl2794);
            if (state.failed) {
                return;
            }

            match(input, 110, FOLLOW_110_in_synpred106_Pddl2796);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timeSpecifier_in_synpred106_Pddl2798);
            timeSpecifier();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daEffect_in_synpred106_Pddl2800);
            daEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred106_Pddl2802);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred107_Pddl
    public final void synpred107_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:439:4: ( '(' 'at' timeSpecifier fAssignDA ')' )
        // Pddl.g:439:4: '(' 'at' timeSpecifier fAssignDA ')'
        {
            match(input, 68, FOLLOW_68_in_synpred107_Pddl2812);
            if (state.failed) {
                return;
            }

            match(input, 110, FOLLOW_110_in_synpred107_Pddl2814);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timeSpecifier_in_synpred107_Pddl2816);
            timeSpecifier();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fAssignDA_in_synpred107_Pddl2818);
            fAssignDA();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred107_Pddl2820);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred108_Pddl
    public final void synpred108_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:448:9: ( ( binaryOp fExpDA fExpDA ) )
        // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
        {
            // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
            // Pddl.g:448:10: binaryOp fExpDA fExpDA
            {
                pushFollow(FOLLOW_binaryOp_in_synpred108_Pddl2876);
                binaryOp();
                state._fsp--;
                if (state.failed) {
                    return;
                }

                pushFollow(FOLLOW_fExpDA_in_synpred108_Pddl2878);
                fExpDA();
                state._fsp--;
                if (state.failed) {
                    return;
                }

                pushFollow(FOLLOW_fExpDA_in_synpred108_Pddl2880);
                fExpDA();
                state._fsp--;
                if (state.failed) {
                    return;
                }

            }

        }

    }

    // $ANTLR start synpred109_Pddl
    public final void synpred109_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:448:4: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' )
        // Pddl.g:448:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
        {
            match(input, 68, FOLLOW_68_in_synpred109_Pddl2872);
            if (state.failed) {
                return;
            }

            // Pddl.g:448:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
            int alt102 = 2;
            int LA102_0 = input.LA(1);
            if ((LA102_0 == 72)) {
                int LA102_1 = input.LA(2);
                if ((synpred108_Pddl())) {
                    alt102 = 1;
                } else if ((true)) {
                    alt102 = 2;
                }

            } else if (((LA102_0 >= 70 && LA102_0 <= 71) || LA102_0 == 73 || LA102_0 == 103)) {
                alt102 = 1;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 102, 0, input);
                throw nvae;
            }

            switch (alt102) {
                case 1: // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
                {
                    // Pddl.g:448:9: ( binaryOp fExpDA fExpDA )
                    // Pddl.g:448:10: binaryOp fExpDA fExpDA
                    {
                        pushFollow(FOLLOW_binaryOp_in_synpred109_Pddl2876);
                        binaryOp();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred109_Pddl2878);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred109_Pddl2880);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }

                }
                break;
                case 2: // Pddl.g:448:36: ( '-' fExpDA )
                {
                    // Pddl.g:448:36: ( '-' fExpDA )
                    // Pddl.g:448:37: '-' fExpDA
                    {
                        match(input, 72, FOLLOW_72_in_synpred109_Pddl2886);
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred109_Pddl2888);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }

                }
                break;

            }

            match(input, 69, FOLLOW_69_in_synpred109_Pddl2892);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred116_Pddl
    public final void synpred116_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:486:4: ( '(' ':init' ( initEl )* ')' )
        // Pddl.g:486:4: '(' ':init' ( initEl )* ')'
        {
            match(input, 68, FOLLOW_68_in_synpred116_Pddl3133);
            if (state.failed) {
                return;
            }

            match(input, 88, FOLLOW_88_in_synpred116_Pddl3135);
            if (state.failed) {
                return;
            }

            // Pddl.g:486:16: ( initEl )*
            loop103:
            while (true) {
                int alt103 = 2;
                int LA103_0 = input.LA(1);
                if ((LA103_0 == 68)) {
                    alt103 = 1;
                }

                switch (alt103) {
                    case 1: // Pddl.g:486:16: initEl
                    {
                        pushFollow(FOLLOW_initEl_in_synpred116_Pddl3137);
                        initEl();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        break loop103;
                }
            }

            match(input, 69, FOLLOW_69_in_synpred116_Pddl3140);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred127_Pddl
    public final void synpred127_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:521:4: ( '(' 'and' ( prefConGD )* ')' )
        // Pddl.g:521:4: '(' 'and' ( prefConGD )* ')'
        {
            match(input, 68, FOLLOW_68_in_synpred127_Pddl3429);
            if (state.failed) {
                return;
            }

            match(input, 108, FOLLOW_108_in_synpred127_Pddl3431);
            if (state.failed) {
                return;
            }

            // Pddl.g:521:14: ( prefConGD )*
            loop105:
            while (true) {
                int alt105 = 2;
                int LA105_0 = input.LA(1);
                if ((LA105_0 == 68)) {
                    alt105 = 1;
                }

                switch (alt105) {
                    case 1: // Pddl.g:521:14: prefConGD
                    {
                        pushFollow(FOLLOW_prefConGD_in_synpred127_Pddl3433);
                        prefConGD();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        break loop105;
                }
            }

            match(input, 69, FOLLOW_69_in_synpred127_Pddl3436);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred128_Pddl
    public final void synpred128_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:522:4: ( '(' 'forall' '(' typedVariableList ')' prefConGD ')' )
        // Pddl.g:522:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
        {
            match(input, 68, FOLLOW_68_in_synpred128_Pddl3441);
            if (state.failed) {
                return;
            }

            match(input, 119, FOLLOW_119_in_synpred128_Pddl3443);
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred128_Pddl3445);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_typedVariableList_in_synpred128_Pddl3447);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred128_Pddl3449);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_prefConGD_in_synpred128_Pddl3451);
            prefConGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred128_Pddl3453);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred130_Pddl
    public final void synpred130_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:523:4: ( '(' 'preference' ( NAME )? conGD ')' )
        // Pddl.g:523:4: '(' 'preference' ( NAME )? conGD ')'
        {
            match(input, 68, FOLLOW_68_in_synpred130_Pddl3458);
            if (state.failed) {
                return;
            }

            match(input, 132, FOLLOW_132_in_synpred130_Pddl3460);
            if (state.failed) {
                return;
            }

            // Pddl.g:523:21: ( NAME )?
            int alt106 = 2;
            int LA106_0 = input.LA(1);
            if ((LA106_0 == NAME)) {
                alt106 = 1;
            }
            switch (alt106) {
                case 1: // Pddl.g:523:21: NAME
                {
                    match(input, NAME, FOLLOW_NAME_in_synpred130_Pddl3462);
                    if (state.failed) {
                        return;
                    }

                }
                break;

            }

            pushFollow(FOLLOW_conGD_in_synpred130_Pddl3465);
            conGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred130_Pddl3467);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred132_Pddl
    public final void synpred132_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:535:4: ( '(' binaryOp metricFExp metricFExp ')' )
        // Pddl.g:535:4: '(' binaryOp metricFExp metricFExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred132_Pddl3528);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_binaryOp_in_synpred132_Pddl3530);
            binaryOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred132_Pddl3532);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred132_Pddl3534);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred132_Pddl3536);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred134_Pddl
    public final void synpred134_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:537:4: ( '(' multiOp metricFExp ( metricFExp )+ ')' )
        // Pddl.g:537:4: '(' multiOp metricFExp ( metricFExp )+ ')'
        {
            match(input, 68, FOLLOW_68_in_synpred134_Pddl3558);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_multiOp_in_synpred134_Pddl3560);
            multiOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred134_Pddl3562);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            // Pddl.g:537:27: ( metricFExp )+
            int cnt107 = 0;
            loop107:
            while (true) {
                int alt107 = 2;
                int LA107_0 = input.LA(1);
                if ((LA107_0 == NAME || LA107_0 == NUMBER || (LA107_0 >= 67 && LA107_0 <= 68))) {
                    alt107 = 1;
                }

                switch (alt107) {
                    case 1: // Pddl.g:537:27: metricFExp
                    {
                        pushFollow(FOLLOW_metricFExp_in_synpred134_Pddl3564);
                        metricFExp();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        if (cnt107 >= 1) {
                            break loop107;
                        }
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return;
                        }
                        EarlyExitException eee = new EarlyExitException(107, input);
                        throw eee;
                }
                cnt107++;
            }

            match(input, 69, FOLLOW_69_in_synpred134_Pddl3567);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred135_Pddl
    public final void synpred135_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:539:4: ( '(' '-' metricFExp ')' )
        // Pddl.g:539:4: '(' '-' metricFExp ')'
        {
            match(input, 68, FOLLOW_68_in_synpred135_Pddl3591);
            if (state.failed) {
                return;
            }

            match(input, 72, FOLLOW_72_in_synpred135_Pddl3593);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred135_Pddl3595);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 69, FOLLOW_69_in_synpred135_Pddl3597);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred137_Pddl
    public final void synpred137_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:542:4: ( fHead )
        // Pddl.g:542:4: fHead
        {
            pushFollow(FOLLOW_fHead_in_synpred137_Pddl3619);
            fHead();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // Delegated rules
    public final boolean synpred66_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred66_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred137_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred137_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred109_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred109_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred106_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred106_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred135_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred135_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred103_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred100_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred100_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred104_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred104_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred134_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred134_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred107_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred107_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred69_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred108_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred108_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred130_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred130_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred105_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred105_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred70_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred50_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred50_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred116_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred116_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred68_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred127_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred127_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred132_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred132_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred18_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred67_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred53_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred128_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred128_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred102_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred102_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public static class pddlDoc_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class domain_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class free_functionsDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class domainName_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class requireDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class typesDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class typedNameList_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class singleTypeNameList_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class type_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class primType_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class functionsDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class functionList_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class atomicFunctionSkeleton_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class functionSymbol_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class functionType_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class constantsDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class predicatesDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class atomicFormulaSkeleton_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class predicate_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class typedVariableList_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class singleTypeVarList_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class constraints_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class structureDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class actionDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class eventDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class processDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class constraintDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class actionSymbol_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class constraintSymbol_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class actionDefBody_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class constraintDefBody_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class goalDesc_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class equality_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fComp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class atomicTermFormula_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class term_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class durativeActionDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class daDefBody_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class daGD_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class prefTimedGD_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class timedGD_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class timeSpecifier_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class interval_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class derivedDef_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fExp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fExp2_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fHead_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class effect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class cEffect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class pEffect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class condEffect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class binaryOp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class multiOp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class binaryComp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class assignOp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class durationConstraint_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class simpleDurationConstraint_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class durOp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class durValue_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class daEffect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class timedEffect_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fAssignDA_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class fExpDA_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class problem_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class problemDecl_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class problemDomain_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class objectDecl_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class init_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class initEl_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class nameLiteral_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class atomicNameFormula_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class goal_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class probConstraints_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class prefConGD_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class metricSpec_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class optimization_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class metricFExp_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    public static class conGD_return extends ParserRuleReturnScope {

        Object tree;

        @Override
        public Object getTree ( ) {
            return tree;
        }
    }

    protected class DFA14 extends DFA {

        public DFA14 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }

        @Override
        public String getDescription ( ) {
            return "146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )";
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
            return "()+ loopback of 146:16: ( singleTypeNameList )+";
        }
    }

    protected class DFA25 extends DFA {

        public DFA25 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }

        @Override
        public String getDescription ( ) {
            return "197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )";
        }
    }

    protected class DFA23 extends DFA {

        public DFA23 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }

        @Override
        public String getDescription ( ) {
            return "()+ loopback of 197:20: ( singleTypeVarList )+";
        }
    }

    protected class DFA71 extends DFA {

        public DFA71 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }

        @Override
        public String getDescription ( ) {
            return "485:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) );";
        }

        @Override
        public int specialStateTransition (int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s) {
                case 0:
                    int LA71_25 = input.LA(1);

                    int index71_25 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred116_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 9;
                    }

                    input.seek(index71_25);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 1:
                    int LA71_28 = input.LA(1);

                    int index71_28 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred116_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 9;
                    }

                    input.seek(index71_28);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 2:
                    int LA71_29 = input.LA(1);

                    int index71_29 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred116_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 9;
                    }

                    input.seek(index71_29);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 3:
                    int LA71_41 = input.LA(1);

                    int index71_41 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred116_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 9;
                    }

                    input.seek(index71_41);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 4:
                    int LA71_43 = input.LA(1);

                    int index71_43 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred116_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 9;
                    }

                    input.seek(index71_43);
                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (state.backtracking > 0) {
                state.failed = true;
                return -1;
            }
            NoViableAltException nvae
                    = new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
}
