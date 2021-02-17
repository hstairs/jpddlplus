/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.parser;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

@SuppressWarnings("all")
public class ModifiedPddlParser extends Parser {

    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "AND_EFFECT", "AND_GD",
            "ANY_CHAR", "ASSIGN_EFFECT", "BINARY_OP", "COMPARISON_GD", "CONSTANTS",
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
            "'^'", "'all'", "'always'", "'always-within'", "'and'", "'assign'", "'at'",
            "'at-most-once'", "'cos'", "'decrease'", "'define'", "'domain'", "'either'",
            "'end'", "'exists'", "'forall'", "'hold-after'", "'hold-during'", "'imply'",
            "'increase'", "'is-violated'", "'maximize'", "'minimize'", "'not'", "'number'",
            "'oneof'", "'or'", "'over'", "'preference'", "'problem'", "'scale-down'",
            "'scale-up'", "'sin'", "'sometime'", "'sometime-after'", "'sometime-before'",
            "'start'", "'unknown'", "'when'", "'within'"
    };
    public static final int EOF = -1;
    public static final int T__66 = 66;
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
    public static final int ACTION = 4;
    public static final int AND_EFFECT = 5;
    public static final int AND_GD = 6;
    public static final int ANY_CHAR = 7;
    public static final int ASSIGN_EFFECT = 8;
    public static final int BINARY_OP = 9;
    public static final int COMPARISON_GD = 10;
    public static final int CONSTANTS = 11;
    public static final int CONSTRAINT = 12;
    public static final int COS = 13;
    public static final int DIGIT = 14;
    public static final int DOMAIN = 15;
    public static final int DOMAIN_NAME = 16;
    public static final int DURATIVE_ACTION = 17;
    public static final int EFFECT = 18;
    public static final int EITHER_TYPE = 19;
    public static final int EQUALITY_CON = 20;
    public static final int EVENT = 21;
    public static final int EXISTS_GD = 22;
    public static final int FORALL_EFFECT = 23;
    public static final int FORALL_GD = 24;
    public static final int FORMULAINIT = 25;
    public static final int FREE_FUNCTIONS = 26;
    public static final int FUNCTIONS = 27;
    public static final int FUNC_HEAD = 28;
    public static final int GLOBAL_CONSTRAINT = 29;
    public static final int GOAL = 30;
    public static final int IMPLY_GD = 31;
    public static final int INIT = 32;
    public static final int INIT_AT = 33;
    public static final int INIT_EQ = 34;
    public static final int LETTER = 35;
    public static final int LINE_COMMENT = 36;
    public static final int MINUS_OP = 37;
    public static final int MULTI_OP = 38;
    public static final int NAME = 39;
    public static final int NOT_EFFECT = 40;
    public static final int NOT_GD = 41;
    public static final int NOT_PRED_INIT = 42;
    public static final int NUMBER = 43;
    public static final int OBJECTS = 44;
    public static final int ONEOF = 45;
    public static final int OR_GD = 46;
    public static final int PRECONDITION = 47;
    public static final int PREDICATES = 48;
    public static final int PRED_HEAD = 49;
    public static final int PRED_INST = 50;
    public static final int PROBLEM = 51;
    public static final int PROBLEM_CONSTRAINT = 52;
    public static final int PROBLEM_DOMAIN = 53;
    public static final int PROBLEM_METRIC = 54;
    public static final int PROBLEM_NAME = 55;
    public static final int PROCESS = 56;
    public static final int REQUIREMENTS = 57;
    public static final int REQUIRE_KEY = 58;
    public static final int SIN = 59;
    public static final int TYPES = 60;
    public static final int UNARY_MINUS = 61;
    public static final int UNKNOWN = 62;
    public static final int VARIABLE = 63;
    public static final int WHEN_EFFECT = 64;
    public static final int WHITESPACE = 65;
    public static final BitSet FOLLOW_domain_in_pddlDoc381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_problem_in_pddlDoc385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_domain400 = new BitSet(new long[]{0x0000000000000000L, 0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_domain402 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_domainName_in_domain404 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_requireDef_in_domain412 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_typesDef_in_domain421 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_constantsDef_in_domain430 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_predicatesDef_in_domain439 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_functionsDef_in_domain448 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_free_functionsDef_in_domain457 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_constraints_in_domain466 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});

    ;
    public static final BitSet FOLLOW_structureDef_in_domain475 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    // $ANTLR end "pddlDoc"
    public static final BitSet FOLLOW_68_in_domain484 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_free_functionsDef568 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000100000L});
    // $ANTLR end "domain"
    public static final BitSet FOLLOW_84_in_free_functionsDef570 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});

    ;
    public static final BitSet FOLLOW_functionList_in_free_functionsDef572 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "free_functionsDef"
    public static final BitSet FOLLOW_68_in_free_functionsDef574 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_domainName597 = new BitSet(new long[]{0x0000000000000000L, 0x0002000000000000L});
    // $ANTLR end "domainName"
    public static final BitSet FOLLOW_113_in_domainName599 = new BitSet(new long[]{0x0000008000000000L});

    ;
    public static final BitSet FOLLOW_NAME_in_domainName601 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "requireDef"
    public static final BitSet FOLLOW_68_in_domainName603 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_requireDef630 = new BitSet(new long[]{0x0000000000000000L, 0x0000000040000000L});
    // $ANTLR end "typesDef"
    public static final BitSet FOLLOW_94_in_requireDef632 = new BitSet(new long[]{0x0400000000000000L});

    ;
    public static final BitSet FOLLOW_REQUIRE_KEY_in_requireDef634 = new BitSet(new long[]{0x0400000000000000L, 0x0000000000000010L});
    // $ANTLR end "typedNameList"
    public static final BitSet FOLLOW_68_in_requireDef637 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_typesDef658 = new BitSet(new long[]{0x0000000000000000L, 0x0000000080000000L});
    // $ANTLR end "singleTypeNameList"
    public static final BitSet FOLLOW_95_in_typesDef660 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedNameList_in_typesDef662 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "type"
    public static final BitSet FOLLOW_68_in_typesDef664 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_typedNameList691 = new BitSet(new long[]{0x0000008000000002L});
    // $ANTLR end "primType"
    public static final BitSet FOLLOW_singleTypeNameList_in_typedNameList696 = new BitSet(new long[]{0x0000008000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_typedNameList699 = new BitSet(new long[]{0x0000008000000002L});
    // $ANTLR end "functionsDef"
    public static final BitSet FOLLOW_NAME_in_singleTypeNameList719 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000080L});

    ;
    public static final BitSet FOLLOW_71_in_singleTypeNameList722 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000008L});
    // $ANTLR end "functionList"
    public static final BitSet FOLLOW_type_in_singleTypeNameList726 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_type753 = new BitSet(new long[]{0x0000000000000000L, 0x0004000000000000L});
    // $ANTLR end "atomicFunctionSkeleton"
    public static final BitSet FOLLOW_114_in_type755 = new BitSet(new long[]{0x0000008000000000L});

    ;
    public static final BitSet FOLLOW_primType_in_type757 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});
    // $ANTLR end "functionSymbol"
    public static final BitSet FOLLOW_68_in_type760 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_primType_in_type781 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "functionType"
    public static final BitSet FOLLOW_NAME_in_primType791 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_functionsDef801 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000200000L});
    // $ANTLR end "constantsDef"
    public static final BitSet FOLLOW_85_in_functionsDef803 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});

    ;
    public static final BitSet FOLLOW_functionList_in_functionsDef805 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "predicatesDef"
    public static final BitSet FOLLOW_68_in_functionsDef807 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_functionList828 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000088L});
    // $ANTLR end "atomicFormulaSkeleton"
    public static final BitSet FOLLOW_71_in_functionList832 = new BitSet(new long[]{0x0000000000000000L, 0x4000000000000000L});

    ;
    public static final BitSet FOLLOW_functionType_in_functionList834 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000008L});
    // $ANTLR end "predicate"
    public static final BitSet FOLLOW_67_in_atomicFunctionSkeleton850 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000004L});

    ;
    public static final BitSet FOLLOW_functionSymbol_in_atomicFunctionSkeleton853 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    // $ANTLR end "typedVariableList"
    public static final BitSet FOLLOW_typedVariableList_in_atomicFunctionSkeleton856 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_atomicFunctionSkeleton858 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "singleTypeVarList"
    public static final BitSet FOLLOW_126_in_functionType881 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_constantsDef892 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000800L});
    // $ANTLR end "constraints"
    public static final BitSet FOLLOW_75_in_constantsDef894 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedNameList_in_constantsDef896 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "structureDef"
    public static final BitSet FOLLOW_68_in_constantsDef898 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_predicatesDef918 = new BitSet(new long[]{0x0000000000000000L, 0x0000000010000000L});
    // $ANTLR end "actionDef"
    public static final BitSet FOLLOW_92_in_predicatesDef920 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_atomicFormulaSkeleton_in_predicatesDef922 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    // $ANTLR end "eventDef"
    public static final BitSet FOLLOW_68_in_predicatesDef925 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_atomicFormulaSkeleton946 = new BitSet(new long[]{0x0000008000000000L});
    // $ANTLR end "processDef"
    public static final BitSet FOLLOW_predicate_in_atomicFormulaSkeleton949 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_atomicFormulaSkeleton952 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "constraintDef"
    public static final BitSet FOLLOW_68_in_atomicFormulaSkeleton954 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_predicate965 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "actionSymbol"
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList980 = new BitSet(new long[]{0x8000000000000002L});

    ;
    public static final BitSet FOLLOW_singleTypeVarList_in_typedVariableList985 = new BitSet(new long[]{0x8000000000000002L});
    // $ANTLR end "constraintSymbol"
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList988 = new BitSet(new long[]{0x8000000000000002L});

    ;
    public static final BitSet FOLLOW_VARIABLE_in_singleTypeVarList1008 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000080L});
    // $ANTLR end "actionDefBody"
    public static final BitSet FOLLOW_71_in_singleTypeVarList1011 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_type_in_singleTypeVarList1015 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "constraintDefBody"
    public static final BitSet FOLLOW_67_in_constraints1046 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000002000L});

    ;
    public static final BitSet FOLLOW_77_in_constraints1049 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "goalDesc"
    public static final BitSet FOLLOW_conGD_in_constraints1052 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_constraints1054 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "equality"
    public static final BitSet FOLLOW_actionDef_in_structureDef1066 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_durativeActionDef_in_structureDef1071 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "fComp"
    public static final BitSet FOLLOW_derivedDef_in_structureDef1076 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_constraintDef_in_structureDef1081 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "atomicTermFormula"
    public static final BitSet FOLLOW_processDef_in_structureDef1086 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_eventDef_in_structureDef1091 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "term"
    public static final BitSet FOLLOW_67_in_actionDef1106 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000200L});

    ;
    public static final BitSet FOLLOW_73_in_actionDef1108 = new BitSet(new long[]{0x0000008000000000L});
    // $ANTLR end "durativeActionDef"
    public static final BitSet FOLLOW_actionSymbol_in_actionDef1110 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});

    ;
    public static final BitSet FOLLOW_90_in_actionDef1119 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "daDefBody"
    public static final BitSet FOLLOW_67_in_actionDef1122 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_actionDef1124 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "daGD"
    public static final BitSet FOLLOW_68_in_actionDef1126 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008040010L});

    ;
    public static final BitSet FOLLOW_actionDefBody_in_actionDef1139 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "prefTimedGD"
    public static final BitSet FOLLOW_68_in_actionDef1141 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_eventDef1174 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000080000L});
    // $ANTLR end "timedGD"
    public static final BitSet FOLLOW_83_in_eventDef1176 = new BitSet(new long[]{0x0000008000000000L});

    ;
    public static final BitSet FOLLOW_actionSymbol_in_eventDef1178 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});
    // $ANTLR end "timeSpecifier"
    public static final BitSet FOLLOW_90_in_eventDef1187 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_eventDef1190 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    // $ANTLR end "interval"
    public static final BitSet FOLLOW_typedVariableList_in_eventDef1192 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_eventDef1194 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008040010L});
    // $ANTLR end "derivedDef"
    public static final BitSet FOLLOW_actionDefBody_in_eventDef1207 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_eventDef1209 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "fExp"
    public static final BitSet FOLLOW_67_in_processDef1242 = new BitSet(new long[]{0x0000000000000000L, 0x0000000020000000L});

    ;
    public static final BitSet FOLLOW_93_in_processDef1244 = new BitSet(new long[]{0x0000008000000000L});
    // $ANTLR end "fExp2"
    public static final BitSet FOLLOW_actionSymbol_in_processDef1246 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});

    ;
    public static final BitSet FOLLOW_90_in_processDef1255 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "fHead"
    public static final BitSet FOLLOW_67_in_processDef1258 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_processDef1260 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "effect"
    public static final BitSet FOLLOW_68_in_processDef1262 = new BitSet(new long[]{0x0000000000000000L, 0x0000000008040010L});

    ;
    public static final BitSet FOLLOW_actionDefBody_in_processDef1275 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "cEffect"
    public static final BitSet FOLLOW_68_in_processDef1277 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_constraintDef1311 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000001000L});
    // $ANTLR end "pEffect"
    public static final BitSet FOLLOW_76_in_constraintDef1313 = new BitSet(new long[]{0x0000008000000000L});

    ;
    public static final BitSet FOLLOW_constraintSymbol_in_constraintDef1315 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});
    // $ANTLR end "condEffect"
    public static final BitSet FOLLOW_90_in_constraintDef1324 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_constraintDef1327 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    // $ANTLR end "binaryOp"
    public static final BitSet FOLLOW_typedVariableList_in_constraintDef1329 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_constraintDef1331 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000410L});
    // $ANTLR end "multiOp"
    public static final BitSet FOLLOW_constraintDefBody_in_constraintDef1344 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_constraintDef1346 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "binaryComp"
    public static final BitSet FOLLOW_NAME_in_actionSymbol1380 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_NAME_in_constraintSymbol1389 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "assignOp"
    public static final BitSet FOLLOW_91_in_actionDefBody1404 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_actionDefBody1408 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "durationConstraint"
    public static final BitSet FOLLOW_68_in_actionDefBody1410 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000040000L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_actionDefBody1415 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000040000L});
    // $ANTLR end "simpleDurationConstraint"
    public static final BitSet FOLLOW_82_in_actionDefBody1425 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_actionDefBody1429 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "durOp"
    public static final BitSet FOLLOW_68_in_actionDefBody1431 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_effect_in_actionDefBody1436 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "durValue"
    public static final BitSet FOLLOW_74_in_constraintDefBody1471 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_constraintDefBody1475 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "daEffect"
    public static final BitSet FOLLOW_68_in_constraintDefBody1477 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_constraintDefBody1482 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "timedEffect"
    public static final BitSet FOLLOW_atomicTermFormula_in_goalDesc1520 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1525 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    // $ANTLR end "fAssignDA"
    public static final BitSet FOLLOW_106_in_goalDesc1527 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1529 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    // $ANTLR end "fExpDA"
    public static final BitSet FOLLOW_68_in_goalDesc1532 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1557 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L});
    // $ANTLR end "problem"
    public static final BitSet FOLLOW_128_in_goalDesc1559 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1561 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    // $ANTLR end "problemDecl"
    public static final BitSet FOLLOW_68_in_goalDesc1564 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1589 = new BitSet(new long[]{0x0000000000000000L, 0x2000000000000000L});
    // $ANTLR end "problemDomain"
    public static final BitSet FOLLOW_125_in_goalDesc1591 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1593 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "objectDecl"
    public static final BitSet FOLLOW_68_in_goalDesc1595 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1619 = new BitSet(new long[]{0x0000000000000000L, 0x0100000000000000L});
    // $ANTLR end "init"
    public static final BitSet FOLLOW_120_in_goalDesc1621 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1623 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "initEl"
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1625 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1627 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "nameLiteral"
    public static final BitSet FOLLOW_67_in_goalDesc1653 = new BitSet(new long[]{0x0000000000000000L, 0x0010000000000000L});

    ;
    public static final BitSet FOLLOW_116_in_goalDesc1655 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "atomicNameFormula"
    public static final BitSet FOLLOW_67_in_goalDesc1657 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1659 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "goal"
    public static final BitSet FOLLOW_68_in_goalDesc1661 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1663 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end "probConstraints"
    public static final BitSet FOLLOW_68_in_goalDesc1665 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1691 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    // $ANTLR end "prefConGD"
    public static final BitSet FOLLOW_117_in_goalDesc1693 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});

    ;
    public static final BitSet FOLLOW_67_in_goalDesc1695 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    // $ANTLR end "metricSpec"
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1697 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1699 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end "optimization"
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1701 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});

    ;
    public static final BitSet FOLLOW_68_in_goalDesc1703 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "metricFExp"
    public static final BitSet FOLLOW_fComp_in_goalDesc1732 = new BitSet(new long[]{0x0000000000000002L});

    ;
    public static final BitSet FOLLOW_equality_in_goalDesc1759 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "conGD"
    public static final BitSet FOLLOW_67_in_equality1787 = new BitSet(new long[]{0x0000000000000000L, 0x0000000400000000L});
    // $ANTLR end synpred18_Pddl
    public static final BitSet FOLLOW_98_in_equality1790 = new BitSet(new long[]{0x8000008000000000L});
    // $ANTLR end synpred48_Pddl
    public static final BitSet FOLLOW_term_in_equality1792 = new BitSet(new long[]{0x8000008000000000L});
    // $ANTLR end synpred51_Pddl
    public static final BitSet FOLLOW_term_in_equality1794 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred64_Pddl
    public static final BitSet FOLLOW_68_in_equality1796 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred65_Pddl
    public static final BitSet FOLLOW_67_in_fComp1807 = new BitSet(new long[]{0x0000000000000000L, 0x0000001F00000000L});
    // $ANTLR end synpred66_Pddl
    public static final BitSet FOLLOW_binaryComp_in_fComp1810 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    // $ANTLR end synpred67_Pddl
    public static final BitSet FOLLOW_fExp_in_fComp1812 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    // $ANTLR end synpred97_Pddl
    public static final BitSet FOLLOW_fExp_in_fComp1814 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred99_Pddl
    public static final BitSet FOLLOW_68_in_fComp1816 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred100_Pddl
    public static final BitSet FOLLOW_67_in_atomicTermFormula1828 = new BitSet(new long[]{0x0000008000000000L});
    // $ANTLR end synpred101_Pddl
    public static final BitSet FOLLOW_predicate_in_atomicTermFormula1830 = new BitSet(new long[]{0x8000008000000000L, 0x0000000000000010L});
    // $ANTLR end synpred102_Pddl
    public static final BitSet FOLLOW_term_in_atomicTermFormula1832 = new BitSet(new long[]{0x8000008000000000L, 0x0000000000000010L});
    // $ANTLR end synpred103_Pddl
    public static final BitSet FOLLOW_68_in_atomicTermFormula1835 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred104_Pddl
    public static final BitSet FOLLOW_67_in_durativeActionDef1872 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000020000L});
    // $ANTLR end synpred105_Pddl
    public static final BitSet FOLLOW_81_in_durativeActionDef1874 = new BitSet(new long[]{0x0000008000000000L});
    // $ANTLR end synpred106_Pddl
    public static final BitSet FOLLOW_actionSymbol_in_durativeActionDef1876 = new BitSet(new long[]{0x0000000000000000L, 0x0000000004000000L});
    // $ANTLR end synpred113_Pddl
    public static final BitSet FOLLOW_90_in_durativeActionDef1885 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end synpred124_Pddl
    public static final BitSet FOLLOW_67_in_durativeActionDef1888 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred125_Pddl
    public static final BitSet FOLLOW_typedVariableList_in_durativeActionDef1891 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred127_Pddl
    public static final BitSet FOLLOW_68_in_durativeActionDef1895 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000050400L});
    // $ANTLR end synpred129_Pddl
    public static final BitSet FOLLOW_daDefBody_in_durativeActionDef1908 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    // $ANTLR end synpred131_Pddl
    public static final BitSet FOLLOW_68_in_durativeActionDef1910 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end synpred132_Pddl
    public static final BitSet FOLLOW_80_in_daDefBody1943 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    // $ANTLR end synpred134_Pddl
    public static final BitSet FOLLOW_durationConstraint_in_daDefBody1945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_daDefBody1950 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_daDefBody1954 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daDefBody1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daGD_in_daDefBody1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_daDefBody1970 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_daDefBody1974 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daDefBody1976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daEffect_in_daDefBody1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefTimedGD_in_daGD1996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daGD2001 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_daGD2003 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_daGD_in_daGD2005 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_daGD2008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daGD2013 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_daGD2015 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_daGD2017 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_daGD2019 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daGD2021 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daGD_in_daGD2023 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daGD2025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_prefTimedGD2041 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_130_in_prefTimedGD2043 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_prefTimedGD2045 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2048 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_prefTimedGD2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_timedGD2061 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_timedGD2063 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedGD2065 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2067 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_timedGD2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_timedGD2074 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_timedGD2076 = new BitSet(new long[]{0x0000000000000000L, 0x0000008000000000L});
    public static final BitSet FOLLOW_interval_in_timedGD2078 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2080 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_timedGD2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_interval2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_derivedDef2117 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_derivedDef2120 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_typedVariableList_in_derivedDef2123 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_derivedDef2125 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_derivedDef2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_fExp2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fExp2147 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_fExp2149 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_fExp2151 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp2_in_fExp2153 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fExp2155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fExp2172 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2174 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_fExp2176 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fExp2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fExp2194 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_fExp2196 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_fExp2198 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fExp2200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fExp2216 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_fExp2218 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_fExp2220 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fExp2222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_fExp2235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExp22247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fHead2257 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2259 = new BitSet(new long[]{0x8000008000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_term_in_fHead2261 = new BitSet(new long[]{0x8000008000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fHead2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_effect2299 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_effect2301 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_cEffect_in_effect2303 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_effect2306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cEffect_in_effect2320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_cEffect2331 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_cEffect2333 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_cEffect2335 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_cEffect2337 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_cEffect2339 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_effect_in_cEffect2341 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_cEffect2343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_cEffect2361 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_140_in_cEffect2363 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_cEffect2365 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_condEffect_in_cEffect2367 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_cEffect2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_cEffect2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_pEffect2398 = new BitSet(new long[]{0x0000000000000000L, 0x0200880000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_assignOp_in_pEffect2400 = new BitSet(new long[]{0x0000008000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fHead_in_pEffect2402 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_pEffect2404 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pEffect2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_pEffect2426 = new BitSet(new long[]{0x0000000000000000L, 0x2000000000000000L});
    public static final BitSet FOLLOW_125_in_pEffect2428 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2430 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pEffect2432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_condEffect2461 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_condEffect2463 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2465 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_condEffect2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_durationConstraint2587 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_durationConstraint2589 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2591 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_durationConstraint2594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_durationConstraint2599 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_durationConstraint2601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_simpleDurationConstraint2617 = new BitSet(new long[]{0x0000000000000000L, 0x0000001600000000L});
    public static final BitSet FOLLOW_durOp_in_simpleDurationConstraint2619 = new BitSet(new long[]{0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_simpleDurationConstraint2621 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_durValue_in_simpleDurationConstraint2623 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_simpleDurationConstraint2625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_simpleDurationConstraint2630 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_simpleDurationConstraint2632 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_simpleDurationConstraint2634 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2636 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_simpleDurationConstraint2638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_durValue2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_durValue2669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daEffect2679 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_daEffect2681 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2683 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_daEffect2686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daEffect2696 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_daEffect2698 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_daEffect2700 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_daEffect2702 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daEffect2704 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2706 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daEffect2708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daEffect2713 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_140_in_daEffect2715 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daGD_in_daEffect2717 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2719 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daEffect2721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_daEffect2726 = new BitSet(new long[]{0x0000000000000000L, 0x0200880000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_assignOp_in_daEffect2728 = new BitSet(new long[]{0x0000008000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fHead_in_daEffect2730 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_daEffect2732 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_daEffect2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_timedEffect2745 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_timedEffect2747 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2749 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daEffect_in_timedEffect2751 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_timedEffect2753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_timedEffect2763 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_timedEffect2765 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2767 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_fAssignDA_in_timedEffect2769 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_timedEffect2771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_timedEffect2776 = new BitSet(new long[]{0x0000000000000000L, 0x0200880000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_assignOp_in_timedEffect2778 = new BitSet(new long[]{0x0000008000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fHead_in_timedEffect2780 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_timedEffect2782 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_timedEffect2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fAssignDA2804 = new BitSet(new long[]{0x0000000000000000L, 0x0200880000000000L, 0x0000000000000030L});
    public static final BitSet FOLLOW_assignOp_in_fAssignDA2806 = new BitSet(new long[]{0x0000008000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fHead_in_fAssignDA2808 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_fAssignDA2810 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fAssignDA2812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_fExpDA2823 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_fExpDA2827 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2829 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2831 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_71_in_fExpDA2837 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2839 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_fExpDA2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_fExpDA2848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExpDA2853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_problem2867 = new BitSet(new long[]{0x0000000000000000L, 0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_problem2869 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_problemDecl_in_problem2871 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_problemDomain_in_problem2876 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_requireDef_in_problem2884 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_objectDecl_in_problem2893 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_init_in_problem2902 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goal_in_problem2910 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_probConstraints_in_problem2918 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_metricSpec_in_problem2927 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_problem2943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_problemDecl3000 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_problemDecl3002 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDecl3004 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_problemDecl3006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_problemDomain3032 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_problemDomain3034 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDomain3036 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_problemDomain3038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_objectDecl3058 = new BitSet(new long[]{0x0000000000000000L, 0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_objectDecl3060 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedNameList_in_objectDecl3062 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_objectDecl3064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_init3084 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_init3086 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_initEl_in_init3088 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_init3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_init3106 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_init3108 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_init3110 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_init3112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_initEl3136 = new BitSet(new long[]{0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_initEl3138 = new BitSet(new long[]{0x0000008000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fHead_in_initEl3140 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3142 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_initEl3144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_initEl3167 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_initEl3169 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3171 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3173 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_initEl3175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_initEl3191 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000800L});
    public static final BitSet FOLLOW_139_in_initEl3193 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3196 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_initEl3198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_initEl3212 = new BitSet(new long[]{0x0000000000000000L, 0x8000000000000000L});
    public static final BitSet FOLLOW_127_in_initEl3214 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3217 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_initEl3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_initEl3235 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_initEl3237 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3240 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_initEl3243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_nameLiteral3269 = new BitSet(new long[]{0x0000000000000000L, 0x2000000000000000L});
    public static final BitSet FOLLOW_125_in_nameLiteral3271 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3273 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_nameLiteral3275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_atomicNameFormula3294 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_predicate_in_atomicNameFormula3296 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_NAME_in_atomicNameFormula3298 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_atomicNameFormula3301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_goal3326 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_goal3328 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_goal3330 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_goal3333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_probConstraints3351 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_probConstraints3353 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_prefConGD_in_probConstraints3356 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_probConstraints3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_prefConGD3380 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_prefConGD3382 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3384 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_prefConGD3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_prefConGD3392 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_prefConGD3394 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_prefConGD3396 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_prefConGD3398 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_prefConGD3400 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3402 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_prefConGD3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_prefConGD3409 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_130_in_prefConGD3411 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_prefConGD3413 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3416 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_prefConGD3418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_metricSpec3434 = new BitSet(new long[]{0x0000000000000000L, 0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_metricSpec3436 = new BitSet(new long[]{0x0000000000000000L, 0x1800000000000000L});
    public static final BitSet FOLLOW_optimization_in_metricSpec3438 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricSpec3440 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_metricSpec3442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_metricFExp3479 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_metricFExp3481 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3483 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3485 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_metricFExp3487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_metricFExp3509 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000060L});
    public static final BitSet FOLLOW_multiOp_in_metricFExp3511 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3513 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3515 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000001CL});
    public static final BitSet FOLLOW_68_in_metricFExp3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_metricFExp3542 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_metricFExp3544 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3546 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_metricFExp3548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_metricFExp3565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_metricFExp3570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_metricFExp3585 = new BitSet(new long[]{0x0000000000000000L, 0x0400000000000000L});
    public static final BitSet FOLLOW_122_in_metricFExp3587 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_NAME_in_metricFExp3589 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_metricFExp3591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3605 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_conGD3607 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_conGD_in_conGD3609 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_conGD3612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3617 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_conGD3619 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_conGD3621 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_conGD3623 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3625 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_conGD_in_conGD3627 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3634 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_conGD3636 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_conGD3638 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3640 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3668 = new BitSet(new long[]{0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_104_in_conGD3670 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3672 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3679 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_135_in_conGD3681 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3683 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3691 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000002000L});
    public static final BitSet FOLLOW_141_in_conGD3693 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3695 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3697 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3704 = new BitSet(new long[]{0x0000000000000000L, 0x0000200000000000L});
    public static final BitSet FOLLOW_109_in_conGD3706 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3708 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3715 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000100L});
    public static final BitSet FOLLOW_136_in_conGD3717 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3719 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3721 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3728 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000200L});
    public static final BitSet FOLLOW_137_in_conGD3730 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3732 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3734 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3741 = new BitSet(new long[]{0x0000000000000000L, 0x0000020000000000L});
    public static final BitSet FOLLOW_105_in_conGD3743 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3745 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3747 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3749 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3756 = new BitSet(new long[]{0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_conGD3758 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3760 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3762 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3764 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_conGD3771 = new BitSet(new long[]{0x0000000000000000L, 0x0040000000000000L});
    public static final BitSet FOLLOW_118_in_conGD3773 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3775 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3777 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_conGD3779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fComp_in_synpred48_Pddl1732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred51_Pddl1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred64_Pddl2147 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred64_Pddl2149 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_synpred64_Pddl2151 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp2_in_synpred64_Pddl2153 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred64_Pddl2155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred65_Pddl2172 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred65_Pddl2174 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_synpred65_Pddl2176 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred65_Pddl2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred66_Pddl2194 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_synpred66_Pddl2196 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_synpred66_Pddl2198 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred66_Pddl2200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred67_Pddl2216 = new BitSet(new long[]{0x0000000000000000L, 0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_synpred67_Pddl2218 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_fExp_in_synpred67_Pddl2220 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred67_Pddl2222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_synpred97_Pddl2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred99_Pddl2679 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_synpred99_Pddl2681 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_daEffect_in_synpred99_Pddl2683 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_synpred99_Pddl2686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_synpred100_Pddl2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred101_Pddl2696 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_synpred101_Pddl2698 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred101_Pddl2700 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred101_Pddl2702 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred101_Pddl2704 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daEffect_in_synpred101_Pddl2706 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred101_Pddl2708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred102_Pddl2713 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_140_in_synpred102_Pddl2715 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daGD_in_synpred102_Pddl2717 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_timedEffect_in_synpred102_Pddl2719 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred102_Pddl2721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred103_Pddl2745 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_synpred103_Pddl2747 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred103_Pddl2749 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_daEffect_in_synpred103_Pddl2751 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred103_Pddl2753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred104_Pddl2763 = new BitSet(new long[]{0x0000000000000000L, 0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_synpred104_Pddl2765 = new BitSet(new long[]{0x0000000000000000L, 0x0008000000000000L, 0x0000000000000400L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred104_Pddl2767 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_fAssignDA_in_synpred104_Pddl2769 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred104_Pddl2771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binaryOp_in_synpred105_Pddl2827 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_synpred105_Pddl2829 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_synpred105_Pddl2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred106_Pddl2823 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred106_Pddl2827 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_synpred106_Pddl2829 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_synpred106_Pddl2831 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_71_in_synpred106_Pddl2837 = new BitSet(new long[]{0x0000088000000000L, 0x000000200000000CL});
    public static final BitSet FOLLOW_fExpDA_in_synpred106_Pddl2839 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred106_Pddl2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred113_Pddl3084 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_synpred113_Pddl3086 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_initEl_in_synpred113_Pddl3088 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_synpred113_Pddl3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred124_Pddl3380 = new BitSet(new long[]{0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_synpred124_Pddl3382 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_prefConGD_in_synpred124_Pddl3384 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000018L});
    public static final BitSet FOLLOW_68_in_synpred124_Pddl3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred125_Pddl3392 = new BitSet(new long[]{0x0000000000000000L, 0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_synpred125_Pddl3394 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred125_Pddl3396 = new BitSet(new long[]{0x8000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred125_Pddl3398 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred125_Pddl3400 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_prefConGD_in_synpred125_Pddl3402 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred125_Pddl3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred127_Pddl3409 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_130_in_synpred127_Pddl3411 = new BitSet(new long[]{0x0000008000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_synpred127_Pddl3413 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000008L});
    public static final BitSet FOLLOW_conGD_in_synpred127_Pddl3416 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred127_Pddl3418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred129_Pddl3479 = new BitSet(new long[]{0x0000000000000000L, 0x00000040000001E0L});
    public static final BitSet FOLLOW_binaryOp_in_synpred129_Pddl3481 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_synpred129_Pddl3483 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_synpred129_Pddl3485 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred129_Pddl3487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred131_Pddl3509 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000060L});
    public static final BitSet FOLLOW_multiOp_in_synpred131_Pddl3511 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_synpred131_Pddl3513 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_synpred131_Pddl3515 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000001CL});
    public static final BitSet FOLLOW_68_in_synpred131_Pddl3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_synpred132_Pddl3542 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred132_Pddl3544 = new BitSet(new long[]{0x0000088000000000L, 0x000000000000000CL});
    public static final BitSet FOLLOW_metricFExp_in_synpred132_Pddl3546 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred132_Pddl3548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_synpred134_Pddl3570 = new BitSet(new long[]{0x0000000000000002L});
    static final String DFA14_eotS
            = "\4\uffff";
    static final String DFA14_eofS
            = "\4\uffff";
    static final String DFA14_minS
            = "\2\47\2\uffff";
    static final String DFA14_maxS
            = "\1\104\1\107\2\uffff";
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
            = "\2\47\2\uffff";
    static final String DFA12_maxS
            = "\1\104\1\107\2\uffff";
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
            = "\2\77\2\uffff";
    static final String DFA25_maxS
            = "\1\104\1\107\2\uffff";
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
            = "\2\77\2\uffff";
    static final String DFA23_maxS
            = "\1\104\1\107\2\uffff";
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
    static final String DFA70_eotS
            = "\50\uffff";
    static final String DFA70_eofS
            = "\50\uffff";
    static final String DFA70_minS
            = "\1\103\1\127\1\103\1\47\1\uffff\1\103\1\47\1\103\1\uffff\6\47\1\103\1"
            + "\47\1\103\2\47\1\104\1\103\1\47\2\0\1\47\1\104\2\47\1\103\2\47\2\103\1"
            + "\0\1\47\1\0\1\47\1\104\1\103";
    static final String DFA70_maxS
            = "\1\103\1\127\1\104\1\u008b\1\uffff\2\103\1\104\1\uffff\1\104\1\u0080\1"
            + "\u0086\2\103\1\u0080\6\104\1\103\1\104\2\0\3\104\1\103\1\104\1\u0080\3"
            + "\104\1\0\1\104\1\0\3\104";
    static final String DFA70_acceptS
            = "\4\uffff\1\1\3\uffff\1\2\37\uffff";
    static final String DFA70_specialS
            = "\27\uffff\1\0\1\1\11\uffff\1\2\1\uffff\1\3\3\uffff}>";
    static final String[] DFA70_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4",
            "\1\11\70\uffff\2\10\1\6\2\10\5\uffff\1\10\1\uffff\1\4\7\uffff\2\10\2"
                    + "\uffff\1\10\4\uffff\1\5\1\uffff\1\4\1\7\12\uffff\1\4",
            "",
            "\1\12",
            "\1\14\3\uffff\1\10\23\uffff\1\10\2\uffff\1\15\1\13",
            "\1\16\1\17",
            "",
            "\1\20\27\uffff\1\10\4\uffff\1\21",
            "\1\22\70\uffff\5\10\5\uffff\1\10\11\uffff\2\10\2\uffff\1\10\4\uffff"
                    + "\1\10\2\uffff\1\10",
            "\1\23\32\uffff\1\23\2\uffff\4\10\35\uffff\1\10\7\uffff\1\10\27\uffff"
                    + "\1\10",
            "\1\10\3\uffff\1\24\23\uffff\1\10\2\uffff\2\10",
            "\1\10\3\uffff\1\24\26\uffff\2\10",
            "\1\26\70\uffff\5\10\5\uffff\1\10\11\uffff\2\10\2\uffff\1\10\4\uffff"
                    + "\1\25\2\uffff\1\10",
            "\1\4\1\27",
            "\1\20\27\uffff\1\10\4\uffff\1\21",
            "\1\4\1\30",
            "\1\31\27\uffff\1\10\4\uffff\1\32",
            "\1\33\27\uffff\1\33\4\uffff\1\34",
            "\1\35",
            "\1\36",
            "\1\37\27\uffff\1\10\4\uffff\1\40",
            "\1\uffff",
            "\1\uffff",
            "\1\31\27\uffff\1\10\4\uffff\1\32",
            "\1\41",
            "\1\33\27\uffff\1\33\4\uffff\1\34",
            "\1\10\3\uffff\1\24\26\uffff\2\10",
            "\1\4\1\42",
            "\1\43\70\uffff\5\10\5\uffff\1\10\11\uffff\2\10\2\uffff\1\10\4\uffff"
                    + "\1\10\2\uffff\1\10",
            "\1\37\27\uffff\1\10\4\uffff\1\40",
            "\1\16\1\17",
            "\1\4\1\44",
            "\1\uffff",
            "\1\45\27\uffff\1\10\4\uffff\1\46",
            "\1\uffff",
            "\1\45\27\uffff\1\10\4\uffff\1\46",
            "\1\47",
            "\1\16\1\17"
    };
    static final short[] DFA70_eot = DFA.unpackEncodedString(DFA70_eotS);
    static final short[] DFA70_eof = DFA.unpackEncodedString(DFA70_eofS);
    static final char[] DFA70_min = DFA.unpackEncodedStringToUnsignedChars(DFA70_minS);
    static final char[] DFA70_max = DFA.unpackEncodedStringToUnsignedChars(DFA70_maxS);
    static final short[] DFA70_accept = DFA.unpackEncodedString(DFA70_acceptS);
    static final short[] DFA70_special = DFA.unpackEncodedString(DFA70_specialS);
    static final short[][] DFA70_transition;

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
        int numStates = DFA70_transitionS.length;
        DFA70_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA70_transition[i] = DFA.unpackEncodedString(DFA70_transitionS[i]);
        }
    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA25 dfa25 = new DFA25(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA70 dfa70 = new DFA70(this);
    private boolean wasError = false;

    // delegators
    public ModifiedPddlParser (TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public ModifiedPddlParser (TokenStream input, RecognizerSharedState state) {
        super(input, state);
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
        return ModifiedPddlParser.tokenNames;
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
    // Pddl.g:102:1: pddlDoc : ( domain | problem );
    public final ModifiedPddlParser.pddlDoc_return pddlDoc ( ) throws RecognitionException {
        ModifiedPddlParser.pddlDoc_return retval = new ModifiedPddlParser.pddlDoc_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope domain1 = null;
        ParserRuleReturnScope problem2 = null;

        try {
            // Pddl.g:102:9: ( domain | problem )
            int alt1 = 2;
            int LA1_0 = input.LA(1);
            if ((LA1_0 == 67)) {
                int LA1_1 = input.LA(2);
                if ((LA1_1 == 112)) {
                    int LA1_2 = input.LA(3);
                    if ((LA1_2 == 67)) {
                        int LA1_3 = input.LA(4);
                        if ((LA1_3 == 113)) {
                            alt1 = 1;
                        } else if ((LA1_3 == 131)) {
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
                case 1: // Pddl.g:102:11: domain
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_domain_in_pddlDoc381);
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
                case 2: // Pddl.g:102:20: problem
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_problem_in_pddlDoc385);
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
        }
        return retval;
    }

    // $ANTLR start "domain"
    // Pddl.g:106:1: domain : '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) ;
    public final ModifiedPddlParser.domain_return domain ( ) throws RecognitionException {
        ModifiedPddlParser.domain_return retval = new ModifiedPddlParser.domain_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_112 = new RewriteRuleTokenStream(adaptor, "token 112");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
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
            // Pddl.g:107:5: ( '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) )
            // Pddl.g:107:7: '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')'
            {
                char_literal3 = (Token) match(input, 67, FOLLOW_67_in_domain400);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal3);
                }

                string_literal4 = (Token) match(input, 112, FOLLOW_112_in_domain402);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_112.add(string_literal4);
                }

                pushFollow(FOLLOW_domainName_in_domain404);
                domainName5 = domainName();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_domainName.add(domainName5.getTree());
                }
                // Pddl.g:108:7: ( requireDef )?
                int alt2 = 2;
                int LA2_0 = input.LA(1);
                if ((LA2_0 == 67)) {
                    int LA2_1 = input.LA(2);
                    if ((LA2_1 == 94)) {
                        alt2 = 1;
                    }
                }
                switch (alt2) {
                    case 1: // Pddl.g:108:7: requireDef
                    {
                        pushFollow(FOLLOW_requireDef_in_domain412);
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

                // Pddl.g:109:7: ( typesDef )?
                int alt3 = 2;
                int LA3_0 = input.LA(1);
                if ((LA3_0 == 67)) {
                    int LA3_1 = input.LA(2);
                    if ((LA3_1 == 95)) {
                        alt3 = 1;
                    }
                }
                switch (alt3) {
                    case 1: // Pddl.g:109:7: typesDef
                    {
                        pushFollow(FOLLOW_typesDef_in_domain421);
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

                // Pddl.g:110:7: ( constantsDef )?
                int alt4 = 2;
                int LA4_0 = input.LA(1);
                if ((LA4_0 == 67)) {
                    int LA4_1 = input.LA(2);
                    if ((LA4_1 == 75)) {
                        alt4 = 1;
                    }
                }
                switch (alt4) {
                    case 1: // Pddl.g:110:7: constantsDef
                    {
                        pushFollow(FOLLOW_constantsDef_in_domain430);
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

                // Pddl.g:111:7: ( predicatesDef )?
                int alt5 = 2;
                int LA5_0 = input.LA(1);
                if ((LA5_0 == 67)) {
                    int LA5_1 = input.LA(2);
                    if ((LA5_1 == 92)) {
                        alt5 = 1;
                    }
                }
                switch (alt5) {
                    case 1: // Pddl.g:111:7: predicatesDef
                    {
                        pushFollow(FOLLOW_predicatesDef_in_domain439);
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

                // Pddl.g:112:7: ( functionsDef )?
                int alt6 = 2;
                int LA6_0 = input.LA(1);
                if ((LA6_0 == 67)) {
                    int LA6_1 = input.LA(2);
                    if ((LA6_1 == 85)) {
                        alt6 = 1;
                    }
                }
                switch (alt6) {
                    case 1: // Pddl.g:112:7: functionsDef
                    {
                        pushFollow(FOLLOW_functionsDef_in_domain448);
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

                // Pddl.g:113:7: ( free_functionsDef )?
                int alt7 = 2;
                int LA7_0 = input.LA(1);
                if ((LA7_0 == 67)) {
                    int LA7_1 = input.LA(2);
                    if ((LA7_1 == 84)) {
                        alt7 = 1;
                    }
                }
                switch (alt7) {
                    case 1: // Pddl.g:113:7: free_functionsDef
                    {
                        pushFollow(FOLLOW_free_functionsDef_in_domain457);
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

                // Pddl.g:114:7: ( constraints )?
                int alt8 = 2;
                int LA8_0 = input.LA(1);
                if ((LA8_0 == 67)) {
                    int LA8_1 = input.LA(2);
                    if ((LA8_1 == 77)) {
                        alt8 = 1;
                    }
                }
                switch (alt8) {
                    case 1: // Pddl.g:114:7: constraints
                    {
                        pushFollow(FOLLOW_constraints_in_domain466);
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

                // Pddl.g:115:7: ( structureDef )*
                loop9:
                while (true) {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);
                    if ((LA9_0 == 67)) {
                        alt9 = 1;
                    }

                    switch (alt9) {
                        case 1: // Pddl.g:115:7: structureDef
                        {
                            pushFollow(FOLLOW_structureDef_in_domain475);
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

                char_literal14 = (Token) match(input, 68, FOLLOW_68_in_domain484);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal14);
                }

                // AST REWRITE
                // elements: constraints, structureDef, requireDef, constantsDef, free_functionsDef, domainName, functionsDef, typesDef, predicatesDef
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 117:7: -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
                    {
                        // Pddl.g:117:10: ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DOMAIN, "DOMAIN"), root_1);
                            adaptor.addChild(root_1, stream_domainName.nextTree());
                            // Pddl.g:117:30: ( requireDef )?
                            if (stream_requireDef.hasNext()) {
                                adaptor.addChild(root_1, stream_requireDef.nextTree());
                            }
                            stream_requireDef.reset();

                            // Pddl.g:117:42: ( typesDef )?
                            if (stream_typesDef.hasNext()) {
                                adaptor.addChild(root_1, stream_typesDef.nextTree());
                            }
                            stream_typesDef.reset();

                            // Pddl.g:118:17: ( constantsDef )?
                            if (stream_constantsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_constantsDef.nextTree());
                            }
                            stream_constantsDef.reset();

                            // Pddl.g:118:31: ( predicatesDef )?
                            if (stream_predicatesDef.hasNext()) {
                                adaptor.addChild(root_1, stream_predicatesDef.nextTree());
                            }
                            stream_predicatesDef.reset();

                            // Pddl.g:118:46: ( functionsDef )?
                            if (stream_functionsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_functionsDef.nextTree());
                            }
                            stream_functionsDef.reset();

                            // Pddl.g:118:60: ( free_functionsDef )?
                            if (stream_free_functionsDef.hasNext()) {
                                adaptor.addChild(root_1, stream_free_functionsDef.nextTree());
                            }
                            stream_free_functionsDef.reset();

                            // Pddl.g:119:17: ( constraints )?
                            if (stream_constraints.hasNext()) {
                                adaptor.addChild(root_1, stream_constraints.nextTree());
                            }
                            stream_constraints.reset();

                            // Pddl.g:119:30: ( structureDef )*
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
        }
        return retval;
    }

    // $ANTLR start "free_functionsDef"
    // Pddl.g:122:1: free_functionsDef : '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) ;
    public final ModifiedPddlParser.free_functionsDef_return free_functionsDef ( ) throws RecognitionException {
        ModifiedPddlParser.free_functionsDef_return retval = new ModifiedPddlParser.free_functionsDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal15 = null;
        Token string_literal16 = null;
        Token char_literal18 = null;
        ParserRuleReturnScope functionList17 = null;

        Object char_literal15_tree = null;
        Object string_literal16_tree = null;
        Object char_literal18_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_84 = new RewriteRuleTokenStream(adaptor, "token 84");
        RewriteRuleSubtreeStream stream_functionList = new RewriteRuleSubtreeStream(adaptor, "rule functionList");

        try {
            // Pddl.g:123:2: ( '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) )
            // Pddl.g:123:4: '(' ':free_functions' functionList ')'
            {
                char_literal15 = (Token) match(input, 67, FOLLOW_67_in_free_functionsDef568);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal15);
                }

                string_literal16 = (Token) match(input, 84, FOLLOW_84_in_free_functionsDef570);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_84.add(string_literal16);
                }

                pushFollow(FOLLOW_functionList_in_free_functionsDef572);
                functionList17 = functionList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_functionList.add(functionList17.getTree());
                }
                char_literal18 = (Token) match(input, 68, FOLLOW_68_in_free_functionsDef574);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal18);
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
                    // 124:2: -> ^( FREE_FUNCTIONS functionList )
                    {
                        // Pddl.g:124:5: ^( FREE_FUNCTIONS functionList )
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
        }
        return retval;
    }

    // $ANTLR start "domainName"
    // Pddl.g:127:1: domainName : '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) ;
    public final ModifiedPddlParser.domainName_return domainName ( ) throws RecognitionException {
        ModifiedPddlParser.domainName_return retval = new ModifiedPddlParser.domainName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal19 = null;
        Token string_literal20 = null;
        Token NAME21 = null;
        Token char_literal22 = null;

        Object char_literal19_tree = null;
        Object string_literal20_tree = null;
        Object NAME21_tree = null;
        Object char_literal22_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_113 = new RewriteRuleTokenStream(adaptor, "token 113");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            // Pddl.g:128:5: ( '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) )
            // Pddl.g:128:7: '(' 'domain' NAME ')'
            {
                char_literal19 = (Token) match(input, 67, FOLLOW_67_in_domainName597);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal19);
                }

                string_literal20 = (Token) match(input, 113, FOLLOW_113_in_domainName599);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_113.add(string_literal20);
                }

                NAME21 = (Token) match(input, NAME, FOLLOW_NAME_in_domainName601);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME21);
                }

                char_literal22 = (Token) match(input, 68, FOLLOW_68_in_domainName603);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal22);
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
                    // 129:6: -> ^( DOMAIN_NAME NAME )
                    {
                        // Pddl.g:129:9: ^( DOMAIN_NAME NAME )
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
        }
        return retval;
    }

    // $ANTLR start "requireDef"
    // Pddl.g:132:1: requireDef : '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) ;
    public final ModifiedPddlParser.requireDef_return requireDef ( ) throws RecognitionException {
        ModifiedPddlParser.requireDef_return retval = new ModifiedPddlParser.requireDef_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_94 = new RewriteRuleTokenStream(adaptor, "token 94");

        try {
            // Pddl.g:133:2: ( '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) )
            // Pddl.g:133:4: '(' ':requirements' ( REQUIRE_KEY )+ ')'
            {
                char_literal23 = (Token) match(input, 67, FOLLOW_67_in_requireDef630);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal23);
                }

                string_literal24 = (Token) match(input, 94, FOLLOW_94_in_requireDef632);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_94.add(string_literal24);
                }

                // Pddl.g:133:24: ( REQUIRE_KEY )+
                int cnt10 = 0;
                loop10:
                while (true) {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);
                    if ((LA10_0 == REQUIRE_KEY)) {
                        alt10 = 1;
                    }

                    switch (alt10) {
                        case 1: // Pddl.g:133:24: REQUIRE_KEY
                        {
                            REQUIRE_KEY25 = (Token) match(input, REQUIRE_KEY, FOLLOW_REQUIRE_KEY_in_requireDef634);
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

                char_literal26 = (Token) match(input, 68, FOLLOW_68_in_requireDef637);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal26);
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
                    // 134:2: -> ^( REQUIREMENTS ( REQUIRE_KEY )+ )
                    {
                        // Pddl.g:134:5: ^( REQUIREMENTS ( REQUIRE_KEY )+ )
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
        }
        return retval;
    }

    // $ANTLR start "typesDef"
    // Pddl.g:137:1: typesDef : '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) ;
    public final ModifiedPddlParser.typesDef_return typesDef ( ) throws RecognitionException {
        ModifiedPddlParser.typesDef_return retval = new ModifiedPddlParser.typesDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal27 = null;
        Token string_literal28 = null;
        Token char_literal30 = null;
        ParserRuleReturnScope typedNameList29 = null;

        Object char_literal27_tree = null;
        Object string_literal28_tree = null;
        Object char_literal30_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_95 = new RewriteRuleTokenStream(adaptor, "token 95");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            // Pddl.g:138:2: ( '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) )
            // Pddl.g:138:4: '(' ':types' typedNameList ')'
            {
                char_literal27 = (Token) match(input, 67, FOLLOW_67_in_typesDef658);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal27);
                }

                string_literal28 = (Token) match(input, 95, FOLLOW_95_in_typesDef660);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_95.add(string_literal28);
                }

                pushFollow(FOLLOW_typedNameList_in_typesDef662);
                typedNameList29 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList29.getTree());
                }
                char_literal30 = (Token) match(input, 68, FOLLOW_68_in_typesDef664);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal30);
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
                    // 139:4: -> ^( TYPES typedNameList )
                    {
                        // Pddl.g:139:7: ^( TYPES typedNameList )
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
        }
        return retval;
    }

    // $ANTLR start "typedNameList"
    // Pddl.g:143:1: typedNameList : ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) ;
    public final ModifiedPddlParser.typedNameList_return typedNameList ( ) throws RecognitionException {
        ModifiedPddlParser.typedNameList_return retval = new ModifiedPddlParser.typedNameList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME31 = null;
        Token NAME33 = null;
        ParserRuleReturnScope singleTypeNameList32 = null;

        Object NAME31_tree = null;
        Object NAME33_tree = null;

        try {
            // Pddl.g:144:5: ( ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) )
            // Pddl.g:144:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:144:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
                int alt14 = 2;
                alt14 = dfa14.predict(input);
                switch (alt14) {
                    case 1: // Pddl.g:144:8: ( NAME )*
                    {
                        // Pddl.g:144:8: ( NAME )*
                        loop11:
                        while (true) {
                            int alt11 = 2;
                            int LA11_0 = input.LA(1);
                            if ((LA11_0 == NAME)) {
                                alt11 = 1;
                            }

                            switch (alt11) {
                                case 1: // Pddl.g:144:8: NAME
                                {
                                    NAME31 = (Token) match(input, NAME, FOLLOW_NAME_in_typedNameList691);
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
                    case 2: // Pddl.g:144:16: ( singleTypeNameList )+ ( NAME )*
                    {
                        // Pddl.g:144:16: ( singleTypeNameList )+
                        int cnt12 = 0;
                        loop12:
                        while (true) {
                            int alt12 = 2;
                            alt12 = dfa12.predict(input);
                            switch (alt12) {
                                case 1: // Pddl.g:144:16: singleTypeNameList
                                {
                                    pushFollow(FOLLOW_singleTypeNameList_in_typedNameList696);
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

                        // Pddl.g:144:36: ( NAME )*
                        loop13:
                        while (true) {
                            int alt13 = 2;
                            int LA13_0 = input.LA(1);
                            if ((LA13_0 == NAME)) {
                                alt13 = 1;
                            }

                            switch (alt13) {
                                case 1: // Pddl.g:144:36: NAME
                                {
                                    NAME33 = (Token) match(input, NAME, FOLLOW_NAME_in_typedNameList699);
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
        }
        return retval;
    }

    // $ANTLR start "singleTypeNameList"
    // Pddl.g:147:1: singleTypeNameList : ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ ;
    public final ModifiedPddlParser.singleTypeNameList_return singleTypeNameList ( ) throws RecognitionException {
        ModifiedPddlParser.singleTypeNameList_return retval = new ModifiedPddlParser.singleTypeNameList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME34 = null;
        Token char_literal35 = null;
        ParserRuleReturnScope t = null;

        Object NAME34_tree = null;
        Object char_literal35_tree = null;
        RewriteRuleTokenStream stream_71 = new RewriteRuleTokenStream(adaptor, "token 71");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");
        RewriteRuleSubtreeStream stream_type = new RewriteRuleSubtreeStream(adaptor, "rule type");

        try {
            // Pddl.g:148:5: ( ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ )
            // Pddl.g:148:7: ( ( NAME )+ '-' t= type )
            {
                // Pddl.g:148:7: ( ( NAME )+ '-' t= type )
                // Pddl.g:148:8: ( NAME )+ '-' t= type
                {
                    // Pddl.g:148:8: ( NAME )+
                    int cnt15 = 0;
                    loop15:
                    while (true) {
                        int alt15 = 2;
                        int LA15_0 = input.LA(1);
                        if ((LA15_0 == NAME)) {
                            alt15 = 1;
                        }

                        switch (alt15) {
                            case 1: // Pddl.g:148:8: NAME
                            {
                                NAME34 = (Token) match(input, NAME, FOLLOW_NAME_in_singleTypeNameList719);
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

                    char_literal35 = (Token) match(input, 71, FOLLOW_71_in_singleTypeNameList722);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_71.add(char_literal35);
                    }

                    pushFollow(FOLLOW_type_in_singleTypeNameList726);
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
                    // 149:4: -> ( ^( NAME $t) )+
                    {
                        if (!(stream_t.hasNext() || stream_NAME.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_t.hasNext() || stream_NAME.hasNext()) {
                            // Pddl.g:149:7: ^( NAME $t)
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
        }
        return retval;
    }

    // $ANTLR start "type"
    // Pddl.g:152:1: type : ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType );
    public final ModifiedPddlParser.type_return type ( ) throws RecognitionException {
        ModifiedPddlParser.type_return retval = new ModifiedPddlParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal36 = null;
        Token string_literal37 = null;
        Token char_literal39 = null;
        ParserRuleReturnScope primType38 = null;
        ParserRuleReturnScope primType40 = null;

        Object char_literal36_tree = null;
        Object string_literal37_tree = null;
        Object char_literal39_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_114 = new RewriteRuleTokenStream(adaptor, "token 114");
        RewriteRuleSubtreeStream stream_primType = new RewriteRuleSubtreeStream(adaptor, "rule primType");

        try {
            // Pddl.g:153:2: ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType )
            int alt17 = 2;
            int LA17_0 = input.LA(1);
            if ((LA17_0 == 67)) {
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
                case 1: // Pddl.g:153:4: ( '(' 'either' ( primType )+ ')' )
                {
                    // Pddl.g:153:4: ( '(' 'either' ( primType )+ ')' )
                    // Pddl.g:153:6: '(' 'either' ( primType )+ ')'
                    {
                        char_literal36 = (Token) match(input, 67, FOLLOW_67_in_type753);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_67.add(char_literal36);
                        }

                        string_literal37 = (Token) match(input, 114, FOLLOW_114_in_type755);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_114.add(string_literal37);
                        }

                        // Pddl.g:153:19: ( primType )+
                        int cnt16 = 0;
                        loop16:
                        while (true) {
                            int alt16 = 2;
                            int LA16_0 = input.LA(1);
                            if ((LA16_0 == NAME)) {
                                alt16 = 1;
                            }

                            switch (alt16) {
                                case 1: // Pddl.g:153:19: primType
                                {
                                    pushFollow(FOLLOW_primType_in_type757);
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

                        char_literal39 = (Token) match(input, 68, FOLLOW_68_in_type760);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_68.add(char_literal39);
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
                        // 154:4: -> ^( EITHER_TYPE ( primType )+ )
                        {
                            // Pddl.g:154:7: ^( EITHER_TYPE ( primType )+ )
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
                case 2: // Pddl.g:155:4: primType
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_primType_in_type781);
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
        }
        return retval;
    }

    // $ANTLR start "primType"
    // Pddl.g:158:1: primType : NAME ;
    public final ModifiedPddlParser.primType_return primType ( ) throws RecognitionException {
        ModifiedPddlParser.primType_return retval = new ModifiedPddlParser.primType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME41 = null;

        Object NAME41_tree = null;

        try {
            // Pddl.g:158:10: ( NAME )
            // Pddl.g:158:12: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME41 = (Token) match(input, NAME, FOLLOW_NAME_in_primType791);
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
        }
        return retval;
    }

    // $ANTLR start "functionsDef"
    // Pddl.g:160:1: functionsDef : '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) ;
    public final ModifiedPddlParser.functionsDef_return functionsDef ( ) throws RecognitionException {
        ModifiedPddlParser.functionsDef_return retval = new ModifiedPddlParser.functionsDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal42 = null;
        Token string_literal43 = null;
        Token char_literal45 = null;
        ParserRuleReturnScope functionList44 = null;

        Object char_literal42_tree = null;
        Object string_literal43_tree = null;
        Object char_literal45_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_85 = new RewriteRuleTokenStream(adaptor, "token 85");
        RewriteRuleSubtreeStream stream_functionList = new RewriteRuleSubtreeStream(adaptor, "rule functionList");

        try {
            // Pddl.g:161:2: ( '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) )
            // Pddl.g:161:4: '(' ':functions' functionList ')'
            {
                char_literal42 = (Token) match(input, 67, FOLLOW_67_in_functionsDef801);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal42);
                }

                string_literal43 = (Token) match(input, 85, FOLLOW_85_in_functionsDef803);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_85.add(string_literal43);
                }

                pushFollow(FOLLOW_functionList_in_functionsDef805);
                functionList44 = functionList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_functionList.add(functionList44.getTree());
                }
                char_literal45 = (Token) match(input, 68, FOLLOW_68_in_functionsDef807);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal45);
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
                    // 162:2: -> ^( FUNCTIONS functionList )
                    {
                        // Pddl.g:162:5: ^( FUNCTIONS functionList )
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
        }
        return retval;
    }

    // $ANTLR start "functionList"
    // Pddl.g:165:1: functionList : ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* ;
    public final ModifiedPddlParser.functionList_return functionList ( ) throws RecognitionException {
        ModifiedPddlParser.functionList_return retval = new ModifiedPddlParser.functionList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal47 = null;
        ParserRuleReturnScope atomicFunctionSkeleton46 = null;
        ParserRuleReturnScope functionType48 = null;

        Object char_literal47_tree = null;

        try {
            // Pddl.g:166:2: ( ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* )
            // Pddl.g:166:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:166:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
                loop20:
                while (true) {
                    int alt20 = 2;
                    int LA20_0 = input.LA(1);
                    if ((LA20_0 == 67)) {
                        alt20 = 1;
                    }

                    switch (alt20) {
                        case 1: // Pddl.g:166:5: ( atomicFunctionSkeleton )+ ( '-' functionType )?
                        {
                            // Pddl.g:166:5: ( atomicFunctionSkeleton )+
                            int cnt18 = 0;
                            loop18:
                            while (true) {
                                int alt18 = 2;
                                int LA18_0 = input.LA(1);
                                if ((LA18_0 == 67)) {
                                    int LA18_2 = input.LA(2);
                                    if ((synpred18_Pddl())) {
                                        alt18 = 1;
                                    }

                                }

                                switch (alt18) {
                                    case 1: // Pddl.g:166:5: atomicFunctionSkeleton
                                    {
                                        pushFollow(FOLLOW_atomicFunctionSkeleton_in_functionList828);
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

                            // Pddl.g:166:29: ( '-' functionType )?
                            int alt19 = 2;
                            int LA19_0 = input.LA(1);
                            if ((LA19_0 == 71)) {
                                alt19 = 1;
                            }
                            switch (alt19) {
                                case 1: // Pddl.g:166:30: '-' functionType
                                {
                                    char_literal47 = (Token) match(input, 71, FOLLOW_71_in_functionList832);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        char_literal47_tree = (Object) adaptor.create(char_literal47);
                                        adaptor.addChild(root_0, char_literal47_tree);
                                    }

                                    pushFollow(FOLLOW_functionType_in_functionList834);
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
        }
        return retval;
    }

    // $ANTLR start "atomicFunctionSkeleton"
    // Pddl.g:169:1: atomicFunctionSkeleton : '(' ! functionSymbol ^ typedVariableList ')' !;
    public final ModifiedPddlParser.atomicFunctionSkeleton_return atomicFunctionSkeleton ( ) throws RecognitionException {
        ModifiedPddlParser.atomicFunctionSkeleton_return retval = new ModifiedPddlParser.atomicFunctionSkeleton_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal49 = null;
        Token char_literal52 = null;
        ParserRuleReturnScope functionSymbol50 = null;
        ParserRuleReturnScope typedVariableList51 = null;

        Object char_literal49_tree = null;
        Object char_literal52_tree = null;

        try {
            // Pddl.g:170:2: ( '(' ! functionSymbol ^ typedVariableList ')' !)
            // Pddl.g:170:4: '(' ! functionSymbol ^ typedVariableList ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal49 = (Token) match(input, 67, FOLLOW_67_in_atomicFunctionSkeleton850);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_functionSymbol_in_atomicFunctionSkeleton853);
                functionSymbol50 = functionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    root_0 = (Object) adaptor.becomeRoot(functionSymbol50.getTree(), root_0);
                }
                pushFollow(FOLLOW_typedVariableList_in_atomicFunctionSkeleton856);
                typedVariableList51 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList51.getTree());
                }

                char_literal52 = (Token) match(input, 68, FOLLOW_68_in_atomicFunctionSkeleton858);
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
        }
        return retval;
    }

    // $ANTLR start "functionSymbol"
    // Pddl.g:173:1: functionSymbol : ( NAME | '#t' );
    public final ModifiedPddlParser.functionSymbol_return functionSymbol ( ) throws RecognitionException {
        ModifiedPddlParser.functionSymbol_return retval = new ModifiedPddlParser.functionSymbol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set53 = null;

        Object set53_tree = null;

        try {
            // Pddl.g:173:16: ( NAME | '#t' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set53 = input.LT(1);
                if (input.LA(1) == NAME || input.LA(1) == 66) {
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
        }
        return retval;
    }

    // $ANTLR start "functionType"
    // Pddl.g:175:1: functionType : 'number' ;
    public final ModifiedPddlParser.functionType_return functionType ( ) throws RecognitionException {
        ModifiedPddlParser.functionType_return retval = new ModifiedPddlParser.functionType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal54 = null;

        Object string_literal54_tree = null;

        try {
            // Pddl.g:175:14: ( 'number' )
            // Pddl.g:175:16: 'number'
            {
                root_0 = (Object) adaptor.nil();

                string_literal54 = (Token) match(input, 126, FOLLOW_126_in_functionType881);
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
        }
        return retval;
    }

    // $ANTLR start "constantsDef"
    // Pddl.g:177:1: constantsDef : '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) ;
    public final ModifiedPddlParser.constantsDef_return constantsDef ( ) throws RecognitionException {
        ModifiedPddlParser.constantsDef_return retval = new ModifiedPddlParser.constantsDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal55 = null;
        Token string_literal56 = null;
        Token char_literal58 = null;
        ParserRuleReturnScope typedNameList57 = null;

        Object char_literal55_tree = null;
        Object string_literal56_tree = null;
        Object char_literal58_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_75 = new RewriteRuleTokenStream(adaptor, "token 75");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            // Pddl.g:178:2: ( '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) )
            // Pddl.g:178:4: '(' ':constants' typedNameList ')'
            {
                char_literal55 = (Token) match(input, 67, FOLLOW_67_in_constantsDef892);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal55);
                }

                string_literal56 = (Token) match(input, 75, FOLLOW_75_in_constantsDef894);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_75.add(string_literal56);
                }

                pushFollow(FOLLOW_typedNameList_in_constantsDef896);
                typedNameList57 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList57.getTree());
                }
                char_literal58 = (Token) match(input, 68, FOLLOW_68_in_constantsDef898);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal58);
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
                    // 179:2: -> ^( CONSTANTS typedNameList )
                    {
                        // Pddl.g:179:5: ^( CONSTANTS typedNameList )
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
        }
        return retval;
    }

    // $ANTLR start "predicatesDef"
    // Pddl.g:182:1: predicatesDef : '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) ;
    public final ModifiedPddlParser.predicatesDef_return predicatesDef ( ) throws RecognitionException {
        ModifiedPddlParser.predicatesDef_return retval = new ModifiedPddlParser.predicatesDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal59 = null;
        Token string_literal60 = null;
        Token char_literal62 = null;
        ParserRuleReturnScope atomicFormulaSkeleton61 = null;

        Object char_literal59_tree = null;
        Object string_literal60_tree = null;
        Object char_literal62_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_92 = new RewriteRuleTokenStream(adaptor, "token 92");
        RewriteRuleSubtreeStream stream_atomicFormulaSkeleton = new RewriteRuleSubtreeStream(adaptor, "rule atomicFormulaSkeleton");

        try {
            // Pddl.g:183:2: ( '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) )
            // Pddl.g:183:4: '(' ':predicates' ( atomicFormulaSkeleton )+ ')'
            {
                char_literal59 = (Token) match(input, 67, FOLLOW_67_in_predicatesDef918);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal59);
                }

                string_literal60 = (Token) match(input, 92, FOLLOW_92_in_predicatesDef920);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_92.add(string_literal60);
                }

                // Pddl.g:183:22: ( atomicFormulaSkeleton )+
                int cnt21 = 0;
                loop21:
                while (true) {
                    int alt21 = 2;
                    int LA21_0 = input.LA(1);
                    if ((LA21_0 == 67)) {
                        alt21 = 1;
                    }

                    switch (alt21) {
                        case 1: // Pddl.g:183:22: atomicFormulaSkeleton
                        {
                            pushFollow(FOLLOW_atomicFormulaSkeleton_in_predicatesDef922);
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

                char_literal62 = (Token) match(input, 68, FOLLOW_68_in_predicatesDef925);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal62);
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
                    // 184:2: -> ^( PREDICATES ( atomicFormulaSkeleton )+ )
                    {
                        // Pddl.g:184:5: ^( PREDICATES ( atomicFormulaSkeleton )+ )
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
        }
        return retval;
    }

    // $ANTLR start "atomicFormulaSkeleton"
    // Pddl.g:187:1: atomicFormulaSkeleton : '(' ! predicate ^ typedVariableList ')' !;
    public final ModifiedPddlParser.atomicFormulaSkeleton_return atomicFormulaSkeleton ( ) throws RecognitionException {
        ModifiedPddlParser.atomicFormulaSkeleton_return retval = new ModifiedPddlParser.atomicFormulaSkeleton_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal63 = null;
        Token char_literal66 = null;
        ParserRuleReturnScope predicate64 = null;
        ParserRuleReturnScope typedVariableList65 = null;

        Object char_literal63_tree = null;
        Object char_literal66_tree = null;

        try {
            // Pddl.g:188:2: ( '(' ! predicate ^ typedVariableList ')' !)
            // Pddl.g:188:4: '(' ! predicate ^ typedVariableList ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal63 = (Token) match(input, 67, FOLLOW_67_in_atomicFormulaSkeleton946);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_predicate_in_atomicFormulaSkeleton949);
                predicate64 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    root_0 = (Object) adaptor.becomeRoot(predicate64.getTree(), root_0);
                }
                pushFollow(FOLLOW_typedVariableList_in_atomicFormulaSkeleton952);
                typedVariableList65 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList65.getTree());
                }

                char_literal66 = (Token) match(input, 68, FOLLOW_68_in_atomicFormulaSkeleton954);
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
        }
        return retval;
    }

    // $ANTLR start "predicate"
    // Pddl.g:191:1: predicate : NAME ;
    public final ModifiedPddlParser.predicate_return predicate ( ) throws RecognitionException {
        ModifiedPddlParser.predicate_return retval = new ModifiedPddlParser.predicate_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME67 = null;

        Object NAME67_tree = null;

        try {
            // Pddl.g:191:11: ( NAME )
            // Pddl.g:191:13: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME67 = (Token) match(input, NAME, FOLLOW_NAME_in_predicate965);
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
        }
        return retval;
    }

    // $ANTLR start "typedVariableList"
    // Pddl.g:194:1: typedVariableList : ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) ;
    public final ModifiedPddlParser.typedVariableList_return typedVariableList ( ) throws RecognitionException {
        ModifiedPddlParser.typedVariableList_return retval = new ModifiedPddlParser.typedVariableList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARIABLE68 = null;
        Token VARIABLE70 = null;
        ParserRuleReturnScope singleTypeVarList69 = null;

        Object VARIABLE68_tree = null;
        Object VARIABLE70_tree = null;

        try {
            // Pddl.g:195:5: ( ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) )
            // Pddl.g:195:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            {
                root_0 = (Object) adaptor.nil();

                // Pddl.g:195:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
                int alt25 = 2;
                alt25 = dfa25.predict(input);
                switch (alt25) {
                    case 1: // Pddl.g:195:8: ( VARIABLE )*
                    {
                        // Pddl.g:195:8: ( VARIABLE )*
                        loop22:
                        while (true) {
                            int alt22 = 2;
                            int LA22_0 = input.LA(1);
                            if ((LA22_0 == VARIABLE)) {
                                alt22 = 1;
                            }

                            switch (alt22) {
                                case 1: // Pddl.g:195:8: VARIABLE
                                {
                                    VARIABLE68 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_typedVariableList980);
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
                    case 2: // Pddl.g:195:20: ( singleTypeVarList )+ ( VARIABLE )*
                    {
                        // Pddl.g:195:20: ( singleTypeVarList )+
                        int cnt23 = 0;
                        loop23:
                        while (true) {
                            int alt23 = 2;
                            alt23 = dfa23.predict(input);
                            switch (alt23) {
                                case 1: // Pddl.g:195:20: singleTypeVarList
                                {
                                    pushFollow(FOLLOW_singleTypeVarList_in_typedVariableList985);
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

                        // Pddl.g:195:39: ( VARIABLE )*
                        loop24:
                        while (true) {
                            int alt24 = 2;
                            int LA24_0 = input.LA(1);
                            if ((LA24_0 == VARIABLE)) {
                                alt24 = 1;
                            }

                            switch (alt24) {
                                case 1: // Pddl.g:195:39: VARIABLE
                                {
                                    VARIABLE70 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_typedVariableList988);
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
        }
        return retval;
    }

    // $ANTLR start "singleTypeVarList"
    // Pddl.g:198:1: singleTypeVarList : ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ ;
    public final ModifiedPddlParser.singleTypeVarList_return singleTypeVarList ( ) throws RecognitionException {
        ModifiedPddlParser.singleTypeVarList_return retval = new ModifiedPddlParser.singleTypeVarList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARIABLE71 = null;
        Token char_literal72 = null;
        ParserRuleReturnScope t = null;

        Object VARIABLE71_tree = null;
        Object char_literal72_tree = null;
        RewriteRuleTokenStream stream_VARIABLE = new RewriteRuleTokenStream(adaptor, "token VARIABLE");
        RewriteRuleTokenStream stream_71 = new RewriteRuleTokenStream(adaptor, "token 71");
        RewriteRuleSubtreeStream stream_type = new RewriteRuleSubtreeStream(adaptor, "rule type");

        try {
            // Pddl.g:199:5: ( ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ )
            // Pddl.g:199:7: ( ( VARIABLE )+ '-' t= type )
            {
                // Pddl.g:199:7: ( ( VARIABLE )+ '-' t= type )
                // Pddl.g:199:8: ( VARIABLE )+ '-' t= type
                {
                    // Pddl.g:199:8: ( VARIABLE )+
                    int cnt26 = 0;
                    loop26:
                    while (true) {
                        int alt26 = 2;
                        int LA26_0 = input.LA(1);
                        if ((LA26_0 == VARIABLE)) {
                            alt26 = 1;
                        }

                        switch (alt26) {
                            case 1: // Pddl.g:199:8: VARIABLE
                            {
                                VARIABLE71 = (Token) match(input, VARIABLE, FOLLOW_VARIABLE_in_singleTypeVarList1008);
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

                    char_literal72 = (Token) match(input, 71, FOLLOW_71_in_singleTypeVarList1011);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_71.add(char_literal72);
                    }

                    pushFollow(FOLLOW_type_in_singleTypeVarList1015);
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
                // elements: VARIABLE, t
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
                    // 200:7: -> ( ^( VARIABLE $t) )+
                    {
                        if (!(stream_VARIABLE.hasNext() || stream_t.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_VARIABLE.hasNext() || stream_t.hasNext()) {
                            // Pddl.g:200:10: ^( VARIABLE $t)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_VARIABLE.nextNode(), root_1);
                                adaptor.addChild(root_1, stream_t.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_VARIABLE.reset();
                        stream_t.reset();

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
        }
        return retval;
    }

    // $ANTLR start "constraints"
    // Pddl.g:203:1: constraints : '(' ! ':constraints' ^ conGD ')' !;
    public final ModifiedPddlParser.constraints_return constraints ( ) throws RecognitionException {
        ModifiedPddlParser.constraints_return retval = new ModifiedPddlParser.constraints_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal73 = null;
        Token string_literal74 = null;
        Token char_literal76 = null;
        ParserRuleReturnScope conGD75 = null;

        Object char_literal73_tree = null;
        Object string_literal74_tree = null;
        Object char_literal76_tree = null;

        try {
            // Pddl.g:204:2: ( '(' ! ':constraints' ^ conGD ')' !)
            // Pddl.g:204:4: '(' ! ':constraints' ^ conGD ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal73 = (Token) match(input, 67, FOLLOW_67_in_constraints1046);
                if (state.failed) {
                    return retval;
                }
                string_literal74 = (Token) match(input, 77, FOLLOW_77_in_constraints1049);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal74_tree = (Object) adaptor.create(string_literal74);
                    root_0 = (Object) adaptor.becomeRoot(string_literal74_tree, root_0);
                }

                pushFollow(FOLLOW_conGD_in_constraints1052);
                conGD75 = conGD();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, conGD75.getTree());
                }

                char_literal76 = (Token) match(input, 68, FOLLOW_68_in_constraints1054);
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
        }
        return retval;
    }

    // $ANTLR start "structureDef"
    // Pddl.g:207:1: structureDef : ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef );
    public final ModifiedPddlParser.structureDef_return structureDef ( ) throws RecognitionException {
        ModifiedPddlParser.structureDef_return retval = new ModifiedPddlParser.structureDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope actionDef77 = null;
        ParserRuleReturnScope durativeActionDef78 = null;
        ParserRuleReturnScope derivedDef79 = null;
        ParserRuleReturnScope constraintDef80 = null;
        ParserRuleReturnScope processDef81 = null;
        ParserRuleReturnScope eventDef82 = null;

        try {
            // Pddl.g:208:2: ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef )
            int alt27 = 6;
            int LA27_0 = input.LA(1);
            if ((LA27_0 == 67)) {
                switch (input.LA(2)) {
                    case 73: {
                        alt27 = 1;
                    }
                    break;
                    case 81: {
                        alt27 = 2;
                    }
                    break;
                    case 78: {
                        alt27 = 3;
                    }
                    break;
                    case 76: {
                        alt27 = 4;
                    }
                    break;
                    case 93: {
                        alt27 = 5;
                    }
                    break;
                    case 83: {
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
                case 1: // Pddl.g:208:4: actionDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_actionDef_in_structureDef1066);
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
                case 2: // Pddl.g:209:4: durativeActionDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_durativeActionDef_in_structureDef1071);
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
                case 3: // Pddl.g:210:4: derivedDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_derivedDef_in_structureDef1076);
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
                case 4: // Pddl.g:211:4: constraintDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_constraintDef_in_structureDef1081);
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
                case 5: // Pddl.g:212:4: processDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_processDef_in_structureDef1086);
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
                case 6: // Pddl.g:213:4: eventDef
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_eventDef_in_structureDef1091);
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
        }
        return retval;
    }

    // $ANTLR start "actionDef"
    // Pddl.g:219:1: actionDef : '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final ModifiedPddlParser.actionDef_return actionDef ( ) throws RecognitionException {
        ModifiedPddlParser.actionDef_return retval = new ModifiedPddlParser.actionDef_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleTokenStream stream_73 = new RewriteRuleTokenStream(adaptor, "token 73");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            // Pddl.g:220:2: ( '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:220:4: '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal83 = (Token) match(input, 67, FOLLOW_67_in_actionDef1106);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal83);
                }

                string_literal84 = (Token) match(input, 73, FOLLOW_73_in_actionDef1108);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_73.add(string_literal84);
                }

                pushFollow(FOLLOW_actionSymbol_in_actionDef1110);
                actionSymbol85 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol85.getTree());
                }
                string_literal86 = (Token) match(input, 90, FOLLOW_90_in_actionDef1119);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal86);
                }

                char_literal87 = (Token) match(input, 67, FOLLOW_67_in_actionDef1122);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal87);
                }

                pushFollow(FOLLOW_typedVariableList_in_actionDef1124);
                typedVariableList88 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList88.getTree());
                }
                char_literal89 = (Token) match(input, 68, FOLLOW_68_in_actionDef1126);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal89);
                }

                pushFollow(FOLLOW_actionDefBody_in_actionDef1139);
                actionDefBody90 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody90.getTree());
                }
                char_literal91 = (Token) match(input, 68, FOLLOW_68_in_actionDef1141);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal91);
                }

                // AST REWRITE
                // elements: actionSymbol, typedVariableList, actionDefBody
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 223:8: -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:223:11: ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ACTION, "ACTION"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:223:33: ( typedVariableList )?
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
        }
        return retval;
    }

    // $ANTLR start "eventDef"
    // Pddl.g:225:1: eventDef : '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final ModifiedPddlParser.eventDef_return eventDef ( ) throws RecognitionException {
        ModifiedPddlParser.eventDef_return retval = new ModifiedPddlParser.eventDef_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleTokenStream stream_83 = new RewriteRuleTokenStream(adaptor, "token 83");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            // Pddl.g:226:2: ( '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:226:4: '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal92 = (Token) match(input, 67, FOLLOW_67_in_eventDef1174);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal92);
                }

                string_literal93 = (Token) match(input, 83, FOLLOW_83_in_eventDef1176);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_83.add(string_literal93);
                }

                pushFollow(FOLLOW_actionSymbol_in_eventDef1178);
                actionSymbol94 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol94.getTree());
                }
                string_literal95 = (Token) match(input, 90, FOLLOW_90_in_eventDef1187);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal95);
                }

                char_literal96 = (Token) match(input, 67, FOLLOW_67_in_eventDef1190);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal96);
                }

                pushFollow(FOLLOW_typedVariableList_in_eventDef1192);
                typedVariableList97 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList97.getTree());
                }
                char_literal98 = (Token) match(input, 68, FOLLOW_68_in_eventDef1194);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal98);
                }

                pushFollow(FOLLOW_actionDefBody_in_eventDef1207);
                actionDefBody99 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody99.getTree());
                }
                char_literal100 = (Token) match(input, 68, FOLLOW_68_in_eventDef1209);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal100);
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
                    // 229:8: -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:229:11: ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EVENT, "EVENT"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:229:32: ( typedVariableList )?
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
        }
        return retval;
    }

    // $ANTLR start "processDef"
    // Pddl.g:231:1: processDef : '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final ModifiedPddlParser.processDef_return processDef ( ) throws RecognitionException {
        ModifiedPddlParser.processDef_return retval = new ModifiedPddlParser.processDef_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleTokenStream stream_93 = new RewriteRuleTokenStream(adaptor, "token 93");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody = new RewriteRuleSubtreeStream(adaptor, "rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            // Pddl.g:232:2: ( '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:232:4: '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
                char_literal101 = (Token) match(input, 67, FOLLOW_67_in_processDef1242);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal101);
                }

                string_literal102 = (Token) match(input, 93, FOLLOW_93_in_processDef1244);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_93.add(string_literal102);
                }

                pushFollow(FOLLOW_actionSymbol_in_processDef1246);
                actionSymbol103 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol103.getTree());
                }
                string_literal104 = (Token) match(input, 90, FOLLOW_90_in_processDef1255);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal104);
                }

                char_literal105 = (Token) match(input, 67, FOLLOW_67_in_processDef1258);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal105);
                }

                pushFollow(FOLLOW_typedVariableList_in_processDef1260);
                typedVariableList106 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList106.getTree());
                }
                char_literal107 = (Token) match(input, 68, FOLLOW_68_in_processDef1262);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal107);
                }

                pushFollow(FOLLOW_actionDefBody_in_processDef1275);
                actionDefBody108 = actionDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionDefBody.add(actionDefBody108.getTree());
                }
                char_literal109 = (Token) match(input, 68, FOLLOW_68_in_processDef1277);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal109);
                }

                // AST REWRITE
                // elements: actionSymbol, actionDefBody, typedVariableList
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 235:8: -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
                    {
                        // Pddl.g:235:11: ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROCESS, "PROCESS"), root_1);
                            adaptor.addChild(root_1, stream_actionSymbol.nextTree());
                            // Pddl.g:235:34: ( typedVariableList )?
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
        }
        return retval;
    }

    // $ANTLR start "constraintDef"
    // Pddl.g:238:1: constraintDef : '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) ;
    public final ModifiedPddlParser.constraintDef_return constraintDef ( ) throws RecognitionException {
        ModifiedPddlParser.constraintDef_return retval = new ModifiedPddlParser.constraintDef_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleTokenStream stream_76 = new RewriteRuleTokenStream(adaptor, "token 76");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_constraintDefBody = new RewriteRuleSubtreeStream(adaptor, "rule constraintDefBody");
        RewriteRuleSubtreeStream stream_constraintSymbol = new RewriteRuleSubtreeStream(adaptor, "rule constraintSymbol");

        try {
            // Pddl.g:239:2: ( '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) )
            // Pddl.g:239:4: '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')'
            {
                char_literal110 = (Token) match(input, 67, FOLLOW_67_in_constraintDef1311);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal110);
                }

                string_literal111 = (Token) match(input, 76, FOLLOW_76_in_constraintDef1313);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_76.add(string_literal111);
                }

                pushFollow(FOLLOW_constraintSymbol_in_constraintDef1315);
                constraintSymbol112 = constraintSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_constraintSymbol.add(constraintSymbol112.getTree());
                }
                string_literal113 = (Token) match(input, 90, FOLLOW_90_in_constraintDef1324);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal113);
                }

                char_literal114 = (Token) match(input, 67, FOLLOW_67_in_constraintDef1327);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal114);
                }

                pushFollow(FOLLOW_typedVariableList_in_constraintDef1329);
                typedVariableList115 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedVariableList.add(typedVariableList115.getTree());
                }
                char_literal116 = (Token) match(input, 68, FOLLOW_68_in_constraintDef1331);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal116);
                }

                pushFollow(FOLLOW_constraintDefBody_in_constraintDef1344);
                constraintDefBody117 = constraintDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_constraintDefBody.add(constraintDefBody117.getTree());
                }
                char_literal118 = (Token) match(input, 68, FOLLOW_68_in_constraintDef1346);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal118);
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
                    // 242:8: -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
                    {
                        // Pddl.g:242:11: ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(GLOBAL_CONSTRAINT, "GLOBAL_CONSTRAINT"), root_1);
                            adaptor.addChild(root_1, stream_constraintSymbol.nextTree());
                            // Pddl.g:242:48: ( typedVariableList )?
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
        }
        return retval;
    }

    // $ANTLR start "actionSymbol"
    // Pddl.g:246:1: actionSymbol : NAME ;
    public final ModifiedPddlParser.actionSymbol_return actionSymbol ( ) throws RecognitionException {
        ModifiedPddlParser.actionSymbol_return retval = new ModifiedPddlParser.actionSymbol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME119 = null;

        Object NAME119_tree = null;

        try {
            // Pddl.g:246:14: ( NAME )
            // Pddl.g:246:16: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME119 = (Token) match(input, NAME, FOLLOW_NAME_in_actionSymbol1380);
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
        }
        return retval;
    }

    // $ANTLR start "constraintSymbol"
    // Pddl.g:248:1: constraintSymbol : NAME ;
    public final ModifiedPddlParser.constraintSymbol_return constraintSymbol ( ) throws RecognitionException {
        ModifiedPddlParser.constraintSymbol_return retval = new ModifiedPddlParser.constraintSymbol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME120 = null;

        Object NAME120_tree = null;

        try {
            // Pddl.g:248:18: ( NAME )
            // Pddl.g:248:20: NAME
            {
                root_0 = (Object) adaptor.nil();

                NAME120 = (Token) match(input, NAME, FOLLOW_NAME_in_constraintSymbol1389);
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
        }
        return retval;
    }

    // $ANTLR start "actionDefBody"
    // Pddl.g:253:1: actionDefBody : ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) ;
    public final ModifiedPddlParser.actionDefBody_return actionDefBody ( ) throws RecognitionException {
        ModifiedPddlParser.actionDefBody_return retval = new ModifiedPddlParser.actionDefBody_return();
        retval.start = input.LT(1);

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
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_91 = new RewriteRuleTokenStream(adaptor, "token 91");
        RewriteRuleTokenStream stream_82 = new RewriteRuleTokenStream(adaptor, "token 82");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_effect = new RewriteRuleSubtreeStream(adaptor, "rule effect");

        try {
            // Pddl.g:254:2: ( ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) )
            // Pddl.g:254:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )?
            {
                // Pddl.g:254:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )?
                int alt29 = 2;
                int LA29_0 = input.LA(1);
                if ((LA29_0 == 91)) {
                    alt29 = 1;
                }
                switch (alt29) {
                    case 1: // Pddl.g:254:6: ':precondition' ( ( '(' ')' ) | goalDesc )
                    {
                        string_literal121 = (Token) match(input, 91, FOLLOW_91_in_actionDefBody1404);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_91.add(string_literal121);
                        }

                        // Pddl.g:254:22: ( ( '(' ')' ) | goalDesc )
                        int alt28 = 2;
                        int LA28_0 = input.LA(1);
                        if ((LA28_0 == 67)) {
                            int LA28_1 = input.LA(2);
                            if ((LA28_1 == 68)) {
                                alt28 = 1;
                            } else if ((LA28_1 == NAME || (LA28_1 >= 96 && LA28_1 <= 100) || LA28_1 == 106 || (LA28_1 >= 116 && LA28_1 <= 117) || LA28_1 == 120 || LA28_1 == 125 || LA28_1 == 128)) {
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
                            case 1: // Pddl.g:254:23: ( '(' ')' )
                            {
                                // Pddl.g:254:23: ( '(' ')' )
                                // Pddl.g:254:24: '(' ')'
                                {
                                    char_literal122 = (Token) match(input, 67, FOLLOW_67_in_actionDefBody1408);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_67.add(char_literal122);
                                    }

                                    char_literal123 = (Token) match(input, 68, FOLLOW_68_in_actionDefBody1410);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal123);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:254:35: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_actionDefBody1415);
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

                // Pddl.g:255:4: ( ':effect' ( ( '(' ')' ) | effect ) )?
                int alt31 = 2;
                int LA31_0 = input.LA(1);
                if ((LA31_0 == 82)) {
                    alt31 = 1;
                }
                switch (alt31) {
                    case 1: // Pddl.g:255:6: ':effect' ( ( '(' ')' ) | effect )
                    {
                        string_literal125 = (Token) match(input, 82, FOLLOW_82_in_actionDefBody1425);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_82.add(string_literal125);
                        }

                        // Pddl.g:255:16: ( ( '(' ')' ) | effect )
                        int alt30 = 2;
                        int LA30_0 = input.LA(1);
                        if ((LA30_0 == 67)) {
                            int LA30_1 = input.LA(2);
                            if ((LA30_1 == 68)) {
                                alt30 = 1;
                            } else if ((LA30_1 == NAME || (LA30_1 >= 106 && LA30_1 <= 107) || LA30_1 == 111 || LA30_1 == 117 || LA30_1 == 121 || LA30_1 == 125 || (LA30_1 >= 132 && LA30_1 <= 133) || LA30_1 == 140)) {
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
                            case 1: // Pddl.g:255:17: ( '(' ')' )
                            {
                                // Pddl.g:255:17: ( '(' ')' )
                                // Pddl.g:255:18: '(' ')'
                                {
                                    char_literal126 = (Token) match(input, 67, FOLLOW_67_in_actionDefBody1429);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_67.add(char_literal126);
                                    }

                                    char_literal127 = (Token) match(input, 68, FOLLOW_68_in_actionDefBody1431);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal127);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:255:29: effect
                            {
                                pushFollow(FOLLOW_effect_in_actionDefBody1436);
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
                // elements: effect, goalDesc
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 256:4: -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? )
                    {
                        // Pddl.g:256:7: ^( PRECONDITION ( goalDesc )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
                            // Pddl.g:256:22: ( goalDesc )?
                            if (stream_goalDesc.hasNext()) {
                                adaptor.addChild(root_1, stream_goalDesc.nextTree());
                            }
                            stream_goalDesc.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                        // Pddl.g:256:33: ^( EFFECT ( effect )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(EFFECT, "EFFECT"), root_1);
                            // Pddl.g:256:42: ( effect )?
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
        }
        return retval;
    }

    // $ANTLR start "constraintDefBody"
    // Pddl.g:259:1: constraintDefBody : ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) ;
    public final ModifiedPddlParser.constraintDefBody_return constraintDefBody ( ) throws RecognitionException {
        ModifiedPddlParser.constraintDefBody_return retval = new ModifiedPddlParser.constraintDefBody_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal129 = null;
        Token char_literal130 = null;
        Token char_literal131 = null;
        ParserRuleReturnScope goalDesc132 = null;

        Object string_literal129_tree = null;
        Object char_literal130_tree = null;
        Object char_literal131_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_74 = new RewriteRuleTokenStream(adaptor, "token 74");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");

        try {
            // Pddl.g:260:2: ( ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) )
            // Pddl.g:260:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
            {
                // Pddl.g:260:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
                int alt33 = 2;
                int LA33_0 = input.LA(1);
                if ((LA33_0 == 74)) {
                    alt33 = 1;
                }
                switch (alt33) {
                    case 1: // Pddl.g:260:6: ':condition' ( ( '(' ')' ) | goalDesc )
                    {
                        string_literal129 = (Token) match(input, 74, FOLLOW_74_in_constraintDefBody1471);
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_74.add(string_literal129);
                        }

                        // Pddl.g:260:19: ( ( '(' ')' ) | goalDesc )
                        int alt32 = 2;
                        int LA32_0 = input.LA(1);
                        if ((LA32_0 == 67)) {
                            int LA32_1 = input.LA(2);
                            if ((LA32_1 == 68)) {
                                alt32 = 1;
                            } else if ((LA32_1 == NAME || (LA32_1 >= 96 && LA32_1 <= 100) || LA32_1 == 106 || (LA32_1 >= 116 && LA32_1 <= 117) || LA32_1 == 120 || LA32_1 == 125 || LA32_1 == 128)) {
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
                            case 1: // Pddl.g:260:20: ( '(' ')' )
                            {
                                // Pddl.g:260:20: ( '(' ')' )
                                // Pddl.g:260:21: '(' ')'
                                {
                                    char_literal130 = (Token) match(input, 67, FOLLOW_67_in_constraintDefBody1475);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_67.add(char_literal130);
                                    }

                                    char_literal131 = (Token) match(input, 68, FOLLOW_68_in_constraintDefBody1477);
                                    if (state.failed) {
                                        return retval;
                                    }
                                    if (state.backtracking == 0) {
                                        stream_68.add(char_literal131);
                                    }

                                }

                            }
                            break;
                            case 2: // Pddl.g:260:32: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_constraintDefBody1482);
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
                    // 261:4: -> ^( PRECONDITION ( goalDesc )? )
                    {
                        // Pddl.g:261:7: ^( PRECONDITION ( goalDesc )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
                            // Pddl.g:261:22: ( goalDesc )?
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
        }
        return retval;
    }

    // $ANTLR start "goalDesc"
    // Pddl.g:276:1: goalDesc : ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) );
    public final ModifiedPddlParser.goalDesc_return goalDesc ( ) throws RecognitionException {
        ModifiedPddlParser.goalDesc_return retval = new ModifiedPddlParser.goalDesc_return();
        retval.start = input.LT(1);

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
        Token char_literal150 = null;
        Token char_literal151 = null;
        Token string_literal152 = null;
        Token char_literal153 = null;
        Token char_literal155 = null;
        Token char_literal157 = null;
        Token char_literal158 = null;
        Token string_literal159 = null;
        Token char_literal160 = null;
        Token char_literal162 = null;
        Token char_literal164 = null;
        ParserRuleReturnScope atomicTermFormula133 = null;
        ParserRuleReturnScope goalDesc136 = null;
        ParserRuleReturnScope goalDesc140 = null;
        ParserRuleReturnScope goalDesc144 = null;
        ParserRuleReturnScope goalDesc148 = null;
        ParserRuleReturnScope goalDesc149 = null;
        ParserRuleReturnScope typedVariableList154 = null;
        ParserRuleReturnScope goalDesc156 = null;
        ParserRuleReturnScope typedVariableList161 = null;
        ParserRuleReturnScope goalDesc163 = null;
        ParserRuleReturnScope fComp165 = null;
        ParserRuleReturnScope equality166 = null;

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
        Object char_literal150_tree = null;
        Object char_literal151_tree = null;
        Object string_literal152_tree = null;
        Object char_literal153_tree = null;
        Object char_literal155_tree = null;
        Object char_literal157_tree = null;
        Object char_literal158_tree = null;
        Object string_literal159_tree = null;
        Object char_literal160_tree = null;
        Object char_literal162_tree = null;
        Object char_literal164_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_125 = new RewriteRuleTokenStream(adaptor, "token 125");
        RewriteRuleTokenStream stream_116 = new RewriteRuleTokenStream(adaptor, "token 116");
        RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(adaptor, "token 106");
        RewriteRuleTokenStream stream_128 = new RewriteRuleTokenStream(adaptor, "token 128");
        RewriteRuleTokenStream stream_117 = new RewriteRuleTokenStream(adaptor, "token 117");
        RewriteRuleTokenStream stream_120 = new RewriteRuleTokenStream(adaptor, "token 120");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_fComp = new RewriteRuleSubtreeStream(adaptor, "rule fComp");
        RewriteRuleSubtreeStream stream_equality = new RewriteRuleSubtreeStream(adaptor, "rule equality");

        try {
            // Pddl.g:277:2: ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) )
            int alt36 = 9;
            int LA36_0 = input.LA(1);
            if ((LA36_0 == 67)) {
                switch (input.LA(2)) {
                    case 106: {
                        alt36 = 2;
                    }
                    break;
                    case 128: {
                        alt36 = 3;
                    }
                    break;
                    case 125: {
                        alt36 = 4;
                    }
                    break;
                    case 120: {
                        alt36 = 5;
                    }
                    break;
                    case 116: {
                        alt36 = 6;
                    }
                    break;
                    case 117: {
                        alt36 = 7;
                    }
                    break;
                    case 98: {
                        switch (input.LA(3)) {
                            case NAME: {
                                switch (input.LA(4)) {
                                    case NAME: {
                                        int LA36_13 = input.LA(5);
                                        if ((LA36_13 == 68)) {
                                            int LA36_14 = input.LA(6);
                                            if ((synpred48_Pddl())) {
                                                alt36 = 8;
                                            } else if ((true)) {
                                                alt36 = 9;
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
                                                        = new NoViableAltException("", 36, 13, input);
                                                throw nvae;
                                            } finally {
                                                input.rewind(nvaeMark);
                                            }
                                        }

                                    }
                                    break;
                                    case NUMBER:
                                    case 66:
                                    case 67: {
                                        alt36 = 8;
                                    }
                                    break;
                                    case VARIABLE: {
                                        alt36 = 9;
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
                                                    = new NoViableAltException("", 36, 11, input);
                                            throw nvae;
                                        } finally {
                                            input.rewind(nvaeMark);
                                        }
                                }
                            }
                            break;
                            case NUMBER:
                            case 66:
                            case 67: {
                                alt36 = 8;
                            }
                            break;
                            case VARIABLE: {
                                alt36 = 9;
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
                                            = new NoViableAltException("", 36, 8, input);
                                    throw nvae;
                                } finally {
                                    input.rewind(nvaeMark);
                                }
                        }
                    }
                    break;
                    case NAME: {
                        alt36 = 1;
                    }
                    break;
                    case 96:
                    case 97:
                    case 99:
                    case 100: {
                        alt36 = 8;
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
                                    = new NoViableAltException("", 36, 1, input);
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
                        = new NoViableAltException("", 36, 0, input);
                throw nvae;
            }

            switch (alt36) {
                case 1: // Pddl.g:277:4: atomicTermFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicTermFormula_in_goalDesc1520);
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
                case 2: // Pddl.g:278:4: '(' 'and' ( goalDesc )* ')'
                {
                    char_literal134 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1525);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal134);
                    }

                    string_literal135 = (Token) match(input, 106, FOLLOW_106_in_goalDesc1527);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_106.add(string_literal135);
                    }

                    // Pddl.g:278:14: ( goalDesc )*
                    loop34:
                    while (true) {
                        int alt34 = 2;
                        int LA34_0 = input.LA(1);
                        if ((LA34_0 == 67)) {
                            alt34 = 1;
                        }

                        switch (alt34) {
                            case 1: // Pddl.g:278:14: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_goalDesc1529);
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

                    char_literal137 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1532);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal137);
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
                        // 279:12: -> ^( AND_GD ( goalDesc )* )
                        {
                            // Pddl.g:279:15: ^( AND_GD ( goalDesc )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_GD, "AND_GD"), root_1);
                                // Pddl.g:279:24: ( goalDesc )*
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
                case 3: // Pddl.g:280:4: '(' 'or' ( goalDesc )* ')'
                {
                    char_literal138 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1557);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal138);
                    }

                    string_literal139 = (Token) match(input, 128, FOLLOW_128_in_goalDesc1559);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_128.add(string_literal139);
                    }

                    // Pddl.g:280:13: ( goalDesc )*
                    loop35:
                    while (true) {
                        int alt35 = 2;
                        int LA35_0 = input.LA(1);
                        if ((LA35_0 == 67)) {
                            alt35 = 1;
                        }

                        switch (alt35) {
                            case 1: // Pddl.g:280:13: goalDesc
                            {
                                pushFollow(FOLLOW_goalDesc_in_goalDesc1561);
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

                    char_literal141 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1564);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal141);
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
                        // 281:12: -> ^( OR_GD ( goalDesc )* )
                        {
                            // Pddl.g:281:15: ^( OR_GD ( goalDesc )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OR_GD, "OR_GD"), root_1);
                                // Pddl.g:281:23: ( goalDesc )*
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
                case 4: // Pddl.g:282:4: '(' 'not' goalDesc ')'
                {
                    char_literal142 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1589);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal142);
                    }

                    string_literal143 = (Token) match(input, 125, FOLLOW_125_in_goalDesc1591);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_125.add(string_literal143);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1593);
                    goalDesc144 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc144.getTree());
                    }
                    char_literal145 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1595);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal145);
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
                        // 283:12: -> ^( NOT_GD goalDesc )
                        {
                            // Pddl.g:283:15: ^( NOT_GD goalDesc )
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
                case 5: // Pddl.g:284:4: '(' 'imply' goalDesc goalDesc ')'
                {
                    char_literal146 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1619);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal146);
                    }

                    string_literal147 = (Token) match(input, 120, FOLLOW_120_in_goalDesc1621);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_120.add(string_literal147);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1623);
                    goalDesc148 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc148.getTree());
                    }
                    pushFollow(FOLLOW_goalDesc_in_goalDesc1625);
                    goalDesc149 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc149.getTree());
                    }
                    char_literal150 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1627);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal150);
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
                        // 285:12: -> ^( IMPLY_GD goalDesc goalDesc )
                        {
                            // Pddl.g:285:15: ^( IMPLY_GD goalDesc goalDesc )
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
                case 6: // Pddl.g:286:4: '(' 'exists' '(' typedVariableList ')' goalDesc ')'
                {
                    char_literal151 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1653);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal151);
                    }

                    string_literal152 = (Token) match(input, 116, FOLLOW_116_in_goalDesc1655);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_116.add(string_literal152);
                    }

                    char_literal153 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1657);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal153);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1659);
                    typedVariableList154 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList154.getTree());
                    }
                    char_literal155 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1661);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal155);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1663);
                    goalDesc156 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc156.getTree());
                    }
                    char_literal157 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1665);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal157);
                    }

                    // AST REWRITE
                    // elements: goalDesc, typedVariableList
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 287:12: -> ^( EXISTS_GD typedVariableList goalDesc )
                        {
                            // Pddl.g:287:15: ^( EXISTS_GD typedVariableList goalDesc )
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
                case 7: // Pddl.g:288:4: '(' 'forall' '(' typedVariableList ')' goalDesc ')'
                {
                    char_literal158 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1691);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal158);
                    }

                    string_literal159 = (Token) match(input, 117, FOLLOW_117_in_goalDesc1693);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_117.add(string_literal159);
                    }

                    char_literal160 = (Token) match(input, 67, FOLLOW_67_in_goalDesc1695);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal160);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1697);
                    typedVariableList161 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList161.getTree());
                    }
                    char_literal162 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1699);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal162);
                    }

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1701);
                    goalDesc163 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc163.getTree());
                    }
                    char_literal164 = (Token) match(input, 68, FOLLOW_68_in_goalDesc1703);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal164);
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
                        // 289:12: -> ^( FORALL_GD typedVariableList goalDesc )
                        {
                            // Pddl.g:289:15: ^( FORALL_GD typedVariableList goalDesc )
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
                case 8: // Pddl.g:290:7: fComp
                {
                    pushFollow(FOLLOW_fComp_in_goalDesc1732);
                    fComp165 = fComp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fComp.add(fComp165.getTree());
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
                        // 291:15: -> ^( COMPARISON_GD fComp )
                        {
                            // Pddl.g:291:18: ^( COMPARISON_GD fComp )
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
                case 9: // Pddl.g:292:4: equality
                {
                    pushFollow(FOLLOW_equality_in_goalDesc1759);
                    equality166 = equality();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_equality.add(equality166.getTree());
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
                        // 293:6: -> ^( EQUALITY_CON equality )
                        {
                            // Pddl.g:293:9: ^( EQUALITY_CON equality )
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
        }
        return retval;
    }

    // $ANTLR start "equality"
    // Pddl.g:296:1: equality : '(' ! '=' term term ')' !;
    public final ModifiedPddlParser.equality_return equality ( ) throws RecognitionException {
        ModifiedPddlParser.equality_return retval = new ModifiedPddlParser.equality_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal167 = null;
        Token char_literal168 = null;
        Token char_literal171 = null;
        ParserRuleReturnScope term169 = null;
        ParserRuleReturnScope term170 = null;

        Object char_literal167_tree = null;
        Object char_literal168_tree = null;
        Object char_literal171_tree = null;

        try {
            // Pddl.g:297:2: ( '(' ! '=' term term ')' !)
            // Pddl.g:297:4: '(' ! '=' term term ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal167 = (Token) match(input, 67, FOLLOW_67_in_equality1787);
                if (state.failed) {
                    return retval;
                }
                char_literal168 = (Token) match(input, 98, FOLLOW_98_in_equality1790);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal168_tree = (Object) adaptor.create(char_literal168);
                    adaptor.addChild(root_0, char_literal168_tree);
                }

                pushFollow(FOLLOW_term_in_equality1792);
                term169 = term();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, term169.getTree());
                }

                pushFollow(FOLLOW_term_in_equality1794);
                term170 = term();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, term170.getTree());
                }

                char_literal171 = (Token) match(input, 68, FOLLOW_68_in_equality1796);
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
        }
        return retval;
    }

    // $ANTLR start "fComp"
    // Pddl.g:299:1: fComp : '(' ! binaryComp fExp fExp ')' !;
    public final ModifiedPddlParser.fComp_return fComp ( ) throws RecognitionException {
        ModifiedPddlParser.fComp_return retval = new ModifiedPddlParser.fComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal172 = null;
        Token char_literal176 = null;
        ParserRuleReturnScope binaryComp173 = null;
        ParserRuleReturnScope fExp174 = null;
        ParserRuleReturnScope fExp175 = null;

        Object char_literal172_tree = null;
        Object char_literal176_tree = null;

        try {
            // Pddl.g:300:2: ( '(' ! binaryComp fExp fExp ')' !)
            // Pddl.g:300:4: '(' ! binaryComp fExp fExp ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal172 = (Token) match(input, 67, FOLLOW_67_in_fComp1807);
                if (state.failed) {
                    return retval;
                }
                pushFollow(FOLLOW_binaryComp_in_fComp1810);
                binaryComp173 = binaryComp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, binaryComp173.getTree());
                }

                pushFollow(FOLLOW_fExp_in_fComp1812);
                fExp174 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp174.getTree());
                }

                pushFollow(FOLLOW_fExp_in_fComp1814);
                fExp175 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp175.getTree());
                }

                char_literal176 = (Token) match(input, 68, FOLLOW_68_in_fComp1816);
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
        }
        return retval;
    }

    // $ANTLR start "atomicTermFormula"
    // Pddl.g:303:1: atomicTermFormula : '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) ;
    public final ModifiedPddlParser.atomicTermFormula_return atomicTermFormula ( ) throws RecognitionException {
        ModifiedPddlParser.atomicTermFormula_return retval = new ModifiedPddlParser.atomicTermFormula_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal177 = null;
        Token char_literal180 = null;
        ParserRuleReturnScope predicate178 = null;
        ParserRuleReturnScope term179 = null;

        Object char_literal177_tree = null;
        Object char_literal180_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_predicate = new RewriteRuleSubtreeStream(adaptor, "rule predicate");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(adaptor, "rule term");

        try {
            // Pddl.g:304:2: ( '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) )
            // Pddl.g:304:4: '(' predicate ( term )* ')'
            {
                char_literal177 = (Token) match(input, 67, FOLLOW_67_in_atomicTermFormula1828);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal177);
                }

                pushFollow(FOLLOW_predicate_in_atomicTermFormula1830);
                predicate178 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_predicate.add(predicate178.getTree());
                }
                // Pddl.g:304:18: ( term )*
                loop37:
                while (true) {
                    int alt37 = 2;
                    int LA37_0 = input.LA(1);
                    if ((LA37_0 == NAME || LA37_0 == VARIABLE)) {
                        alt37 = 1;
                    }

                    switch (alt37) {
                        case 1: // Pddl.g:304:18: term
                        {
                            pushFollow(FOLLOW_term_in_atomicTermFormula1832);
                            term179 = term();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_term.add(term179.getTree());
                            }
                        }
                        break;

                        default:
                            break loop37;
                    }
                }

                char_literal180 = (Token) match(input, 68, FOLLOW_68_in_atomicTermFormula1835);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal180);
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
                    // 304:28: -> ^( PRED_HEAD predicate ( term )* )
                    {
                        // Pddl.g:304:31: ^( PRED_HEAD predicate ( term )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRED_HEAD, "PRED_HEAD"), root_1);
                            adaptor.addChild(root_1, stream_predicate.nextTree());
                            // Pddl.g:304:53: ( term )*
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
        }
        return retval;
    }

    // $ANTLR start "term"
    // Pddl.g:307:1: term : ( NAME | VARIABLE );
    public final ModifiedPddlParser.term_return term ( ) throws RecognitionException {
        ModifiedPddlParser.term_return retval = new ModifiedPddlParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set181 = null;

        Object set181_tree = null;

        try {
            // Pddl.g:307:6: ( NAME | VARIABLE )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set181 = input.LT(1);
                if (input.LA(1) == NAME || input.LA(1) == VARIABLE) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set181));
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
        }
        return retval;
    }

    // $ANTLR start "durativeActionDef"
    // Pddl.g:311:1: durativeActionDef : '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) ;
    public final ModifiedPddlParser.durativeActionDef_return durativeActionDef ( ) throws RecognitionException {
        ModifiedPddlParser.durativeActionDef_return retval = new ModifiedPddlParser.durativeActionDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal182 = null;
        Token string_literal183 = null;
        Token string_literal185 = null;
        Token char_literal186 = null;
        Token char_literal188 = null;
        Token char_literal190 = null;
        ParserRuleReturnScope actionSymbol184 = null;
        ParserRuleReturnScope typedVariableList187 = null;
        ParserRuleReturnScope daDefBody189 = null;

        Object char_literal182_tree = null;
        Object string_literal183_tree = null;
        Object string_literal185_tree = null;
        Object char_literal186_tree = null;
        Object char_literal188_tree = null;
        Object char_literal190_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_90 = new RewriteRuleTokenStream(adaptor, "token 90");
        RewriteRuleTokenStream stream_81 = new RewriteRuleTokenStream(adaptor, "token 81");
        RewriteRuleSubtreeStream stream_daDefBody = new RewriteRuleSubtreeStream(adaptor, "rule daDefBody");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule actionSymbol");

        try {
            // Pddl.g:312:2: ( '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) )
            // Pddl.g:312:4: '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')'
            {
                char_literal182 = (Token) match(input, 67, FOLLOW_67_in_durativeActionDef1872);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal182);
                }

                string_literal183 = (Token) match(input, 81, FOLLOW_81_in_durativeActionDef1874);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_81.add(string_literal183);
                }

                pushFollow(FOLLOW_actionSymbol_in_durativeActionDef1876);
                actionSymbol184 = actionSymbol();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_actionSymbol.add(actionSymbol184.getTree());
                }
                string_literal185 = (Token) match(input, 90, FOLLOW_90_in_durativeActionDef1885);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_90.add(string_literal185);
                }

                char_literal186 = (Token) match(input, 67, FOLLOW_67_in_durativeActionDef1888);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal186);
                }

                // Pddl.g:313:27: ( typedVariableList )?
                int alt38 = 2;
                int LA38_0 = input.LA(1);
                if ((LA38_0 == VARIABLE)) {
                    alt38 = 1;
                } else if ((LA38_0 == 68)) {
                    int LA38_2 = input.LA(2);
                    if ((synpred51_Pddl())) {
                        alt38 = 1;
                    }
                }
                switch (alt38) {
                    case 1: // Pddl.g:313:28: typedVariableList
                    {
                        pushFollow(FOLLOW_typedVariableList_in_durativeActionDef1891);
                        typedVariableList187 = typedVariableList();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_typedVariableList.add(typedVariableList187.getTree());
                        }
                    }
                    break;

                }

                char_literal188 = (Token) match(input, 68, FOLLOW_68_in_durativeActionDef1895);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal188);
                }

                pushFollow(FOLLOW_daDefBody_in_durativeActionDef1908);
                daDefBody189 = daDefBody();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_daDefBody.add(daDefBody189.getTree());
                }
                char_literal190 = (Token) match(input, 68, FOLLOW_68_in_durativeActionDef1910);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal190);
                }

                // AST REWRITE
                // elements: typedVariableList, actionSymbol, daDefBody
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 315:8: -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
                    {
                        // Pddl.g:315:11: ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
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
        }
        return retval;
    }

    // $ANTLR start "daDefBody"
    // Pddl.g:318:1: daDefBody : ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) );
    public final ModifiedPddlParser.daDefBody_return daDefBody ( ) throws RecognitionException {
        ModifiedPddlParser.daDefBody_return retval = new ModifiedPddlParser.daDefBody_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal191 = null;
        Token string_literal193 = null;
        Token char_literal194 = null;
        Token char_literal195 = null;
        Token string_literal197 = null;
        Token char_literal198 = null;
        Token char_literal199 = null;
        ParserRuleReturnScope durationConstraint192 = null;
        ParserRuleReturnScope daGD196 = null;
        ParserRuleReturnScope daEffect200 = null;

        Object string_literal191_tree = null;
        Object string_literal193_tree = null;
        Object char_literal194_tree = null;
        Object char_literal195_tree = null;
        Object string_literal197_tree = null;
        Object char_literal198_tree = null;
        Object char_literal199_tree = null;

        try {
            // Pddl.g:319:2: ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) )
            int alt41 = 3;
            switch (input.LA(1)) {
                case 80: {
                    alt41 = 1;
                }
                break;
                case 74: {
                    alt41 = 2;
                }
                break;
                case 82: {
                    alt41 = 3;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 41, 0, input);
                    throw nvae;
            }
            switch (alt41) {
                case 1: // Pddl.g:319:4: ':duration' durationConstraint
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal191 = (Token) match(input, 80, FOLLOW_80_in_daDefBody1943);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal191_tree = (Object) adaptor.create(string_literal191);
                        adaptor.addChild(root_0, string_literal191_tree);
                    }

                    pushFollow(FOLLOW_durationConstraint_in_daDefBody1945);
                    durationConstraint192 = durationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durationConstraint192.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:320:4: ':condition' ( ( '(' ')' ) | daGD )
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal193 = (Token) match(input, 74, FOLLOW_74_in_daDefBody1950);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal193_tree = (Object) adaptor.create(string_literal193);
                        adaptor.addChild(root_0, string_literal193_tree);
                    }

                    // Pddl.g:320:17: ( ( '(' ')' ) | daGD )
                    int alt39 = 2;
                    int LA39_0 = input.LA(1);
                    if ((LA39_0 == 67)) {
                        int LA39_1 = input.LA(2);
                        if ((LA39_1 == 68)) {
                            alt39 = 1;
                        } else if ((LA39_1 == 106 || LA39_1 == 108 || LA39_1 == 117 || (LA39_1 >= 129 && LA39_1 <= 130))) {
                            alt39 = 2;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae
                                        = new NoViableAltException("", 39, 1, input);
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
                                = new NoViableAltException("", 39, 0, input);
                        throw nvae;
                    }

                    switch (alt39) {
                        case 1: // Pddl.g:320:18: ( '(' ')' )
                        {
                            // Pddl.g:320:18: ( '(' ')' )
                            // Pddl.g:320:19: '(' ')'
                            {
                                char_literal194 = (Token) match(input, 67, FOLLOW_67_in_daDefBody1954);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal194_tree = (Object) adaptor.create(char_literal194);
                                    adaptor.addChild(root_0, char_literal194_tree);
                                }

                                char_literal195 = (Token) match(input, 68, FOLLOW_68_in_daDefBody1956);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal195_tree = (Object) adaptor.create(char_literal195);
                                    adaptor.addChild(root_0, char_literal195_tree);
                                }

                            }

                        }
                        break;
                        case 2: // Pddl.g:320:30: daGD
                        {
                            pushFollow(FOLLOW_daGD_in_daDefBody1961);
                            daGD196 = daGD();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                adaptor.addChild(root_0, daGD196.getTree());
                            }

                        }
                        break;

                    }

                }
                break;
                case 3: // Pddl.g:321:7: ':effect' ( ( '(' ')' ) | daEffect )
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal197 = (Token) match(input, 82, FOLLOW_82_in_daDefBody1970);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal197_tree = (Object) adaptor.create(string_literal197);
                        adaptor.addChild(root_0, string_literal197_tree);
                    }

                    // Pddl.g:321:17: ( ( '(' ')' ) | daEffect )
                    int alt40 = 2;
                    int LA40_0 = input.LA(1);
                    if ((LA40_0 == 67)) {
                        int LA40_1 = input.LA(2);
                        if ((LA40_1 == 68)) {
                            alt40 = 1;
                        } else if (((LA40_1 >= 106 && LA40_1 <= 108) || LA40_1 == 111 || LA40_1 == 117 || LA40_1 == 121 || (LA40_1 >= 132 && LA40_1 <= 133) || LA40_1 == 140)) {
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
                        case 1: // Pddl.g:321:18: ( '(' ')' )
                        {
                            // Pddl.g:321:18: ( '(' ')' )
                            // Pddl.g:321:19: '(' ')'
                            {
                                char_literal198 = (Token) match(input, 67, FOLLOW_67_in_daDefBody1974);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal198_tree = (Object) adaptor.create(char_literal198);
                                    adaptor.addChild(root_0, char_literal198_tree);
                                }

                                char_literal199 = (Token) match(input, 68, FOLLOW_68_in_daDefBody1976);
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
                        case 2: // Pddl.g:321:30: daEffect
                        {
                            pushFollow(FOLLOW_daEffect_in_daDefBody1981);
                            daEffect200 = daEffect();
                            state._fsp--;
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                adaptor.addChild(root_0, daEffect200.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "daGD"
    // Pddl.g:324:1: daGD : ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' );
    public final ModifiedPddlParser.daGD_return daGD ( ) throws RecognitionException {
        ModifiedPddlParser.daGD_return retval = new ModifiedPddlParser.daGD_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal202 = null;
        Token string_literal203 = null;
        Token char_literal205 = null;
        Token char_literal206 = null;
        Token string_literal207 = null;
        Token char_literal208 = null;
        Token char_literal210 = null;
        Token char_literal212 = null;
        ParserRuleReturnScope prefTimedGD201 = null;
        ParserRuleReturnScope daGD204 = null;
        ParserRuleReturnScope typedVariableList209 = null;
        ParserRuleReturnScope daGD211 = null;

        Object char_literal202_tree = null;
        Object string_literal203_tree = null;
        Object char_literal205_tree = null;
        Object char_literal206_tree = null;
        Object string_literal207_tree = null;
        Object char_literal208_tree = null;
        Object char_literal210_tree = null;
        Object char_literal212_tree = null;

        try {
            // Pddl.g:325:2: ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' )
            int alt43 = 3;
            int LA43_0 = input.LA(1);
            if ((LA43_0 == 67)) {
                switch (input.LA(2)) {
                    case 108:
                    case 129:
                    case 130: {
                        alt43 = 1;
                    }
                    break;
                    case 106: {
                        alt43 = 2;
                    }
                    break;
                    case 117: {
                        alt43 = 3;
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
                                    = new NoViableAltException("", 43, 1, input);
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
                        = new NoViableAltException("", 43, 0, input);
                throw nvae;
            }

            switch (alt43) {
                case 1: // Pddl.g:325:4: prefTimedGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_prefTimedGD_in_daGD1996);
                    prefTimedGD201 = prefTimedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, prefTimedGD201.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:326:4: '(' 'and' ( daGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal202 = (Token) match(input, 67, FOLLOW_67_in_daGD2001);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal202_tree = (Object) adaptor.create(char_literal202);
                        adaptor.addChild(root_0, char_literal202_tree);
                    }

                    string_literal203 = (Token) match(input, 106, FOLLOW_106_in_daGD2003);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal203_tree = (Object) adaptor.create(string_literal203);
                        adaptor.addChild(root_0, string_literal203_tree);
                    }

                    // Pddl.g:326:14: ( daGD )*
                    loop42:
                    while (true) {
                        int alt42 = 2;
                        int LA42_0 = input.LA(1);
                        if ((LA42_0 == 67)) {
                            alt42 = 1;
                        }

                        switch (alt42) {
                            case 1: // Pddl.g:326:14: daGD
                            {
                                pushFollow(FOLLOW_daGD_in_daGD2005);
                                daGD204 = daGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, daGD204.getTree());
                                }

                            }
                            break;

                            default:
                                break loop42;
                        }
                    }

                    char_literal205 = (Token) match(input, 68, FOLLOW_68_in_daGD2008);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal205_tree = (Object) adaptor.create(char_literal205);
                        adaptor.addChild(root_0, char_literal205_tree);
                    }

                }
                break;
                case 3: // Pddl.g:327:4: '(' 'forall' '(' typedVariableList ')' daGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal206 = (Token) match(input, 67, FOLLOW_67_in_daGD2013);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal206_tree = (Object) adaptor.create(char_literal206);
                        adaptor.addChild(root_0, char_literal206_tree);
                    }

                    string_literal207 = (Token) match(input, 117, FOLLOW_117_in_daGD2015);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal207_tree = (Object) adaptor.create(string_literal207);
                        adaptor.addChild(root_0, string_literal207_tree);
                    }

                    char_literal208 = (Token) match(input, 67, FOLLOW_67_in_daGD2017);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal208_tree = (Object) adaptor.create(char_literal208);
                        adaptor.addChild(root_0, char_literal208_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daGD2019);
                    typedVariableList209 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList209.getTree());
                    }

                    char_literal210 = (Token) match(input, 68, FOLLOW_68_in_daGD2021);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal210_tree = (Object) adaptor.create(char_literal210);
                        adaptor.addChild(root_0, char_literal210_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daGD2023);
                    daGD211 = daGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daGD211.getTree());
                    }

                    char_literal212 = (Token) match(input, 68, FOLLOW_68_in_daGD2025);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal212_tree = (Object) adaptor.create(char_literal212);
                        adaptor.addChild(root_0, char_literal212_tree);
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
        }
        return retval;
    }

    // $ANTLR start "prefTimedGD"
    // Pddl.g:330:1: prefTimedGD : ( timedGD | '(' 'preference' ( NAME )? timedGD ')' );
    public final ModifiedPddlParser.prefTimedGD_return prefTimedGD ( ) throws RecognitionException {
        ModifiedPddlParser.prefTimedGD_return retval = new ModifiedPddlParser.prefTimedGD_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal214 = null;
        Token string_literal215 = null;
        Token NAME216 = null;
        Token char_literal218 = null;
        ParserRuleReturnScope timedGD213 = null;
        ParserRuleReturnScope timedGD217 = null;

        Object char_literal214_tree = null;
        Object string_literal215_tree = null;
        Object NAME216_tree = null;
        Object char_literal218_tree = null;

        try {
            // Pddl.g:331:2: ( timedGD | '(' 'preference' ( NAME )? timedGD ')' )
            int alt45 = 2;
            int LA45_0 = input.LA(1);
            if ((LA45_0 == 67)) {
                int LA45_1 = input.LA(2);
                if ((LA45_1 == 108 || LA45_1 == 129)) {
                    alt45 = 1;
                } else if ((LA45_1 == 130)) {
                    alt45 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 45, 1, input);
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
                        = new NoViableAltException("", 45, 0, input);
                throw nvae;
            }

            switch (alt45) {
                case 1: // Pddl.g:331:4: timedGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2036);
                    timedGD213 = timedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedGD213.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:332:4: '(' 'preference' ( NAME )? timedGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal214 = (Token) match(input, 67, FOLLOW_67_in_prefTimedGD2041);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal214_tree = (Object) adaptor.create(char_literal214);
                        adaptor.addChild(root_0, char_literal214_tree);
                    }

                    string_literal215 = (Token) match(input, 130, FOLLOW_130_in_prefTimedGD2043);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal215_tree = (Object) adaptor.create(string_literal215);
                        adaptor.addChild(root_0, string_literal215_tree);
                    }

                    // Pddl.g:332:21: ( NAME )?
                    int alt44 = 2;
                    int LA44_0 = input.LA(1);
                    if ((LA44_0 == NAME)) {
                        alt44 = 1;
                    }
                    switch (alt44) {
                        case 1: // Pddl.g:332:21: NAME
                        {
                            NAME216 = (Token) match(input, NAME, FOLLOW_NAME_in_prefTimedGD2045);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                NAME216_tree = (Object) adaptor.create(NAME216);
                                adaptor.addChild(root_0, NAME216_tree);
                            }

                        }
                        break;

                    }

                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2048);
                    timedGD217 = timedGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedGD217.getTree());
                    }

                    char_literal218 = (Token) match(input, 68, FOLLOW_68_in_prefTimedGD2050);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal218_tree = (Object) adaptor.create(char_literal218);
                        adaptor.addChild(root_0, char_literal218_tree);
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
        }
        return retval;
    }

    // $ANTLR start "timedGD"
    // Pddl.g:335:1: timedGD : ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' );
    public final ModifiedPddlParser.timedGD_return timedGD ( ) throws RecognitionException {
        ModifiedPddlParser.timedGD_return retval = new ModifiedPddlParser.timedGD_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal219 = null;
        Token string_literal220 = null;
        Token char_literal223 = null;
        Token char_literal224 = null;
        Token string_literal225 = null;
        Token char_literal228 = null;
        ParserRuleReturnScope timeSpecifier221 = null;
        ParserRuleReturnScope goalDesc222 = null;
        ParserRuleReturnScope interval226 = null;
        ParserRuleReturnScope goalDesc227 = null;

        Object char_literal219_tree = null;
        Object string_literal220_tree = null;
        Object char_literal223_tree = null;
        Object char_literal224_tree = null;
        Object string_literal225_tree = null;
        Object char_literal228_tree = null;

        try {
            // Pddl.g:336:2: ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' )
            int alt46 = 2;
            int LA46_0 = input.LA(1);
            if ((LA46_0 == 67)) {
                int LA46_1 = input.LA(2);
                if ((LA46_1 == 108)) {
                    alt46 = 1;
                } else if ((LA46_1 == 129)) {
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
                case 1: // Pddl.g:336:4: '(' 'at' timeSpecifier goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal219 = (Token) match(input, 67, FOLLOW_67_in_timedGD2061);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal219_tree = (Object) adaptor.create(char_literal219);
                        adaptor.addChild(root_0, char_literal219_tree);
                    }

                    string_literal220 = (Token) match(input, 108, FOLLOW_108_in_timedGD2063);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal220_tree = (Object) adaptor.create(string_literal220);
                        adaptor.addChild(root_0, string_literal220_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedGD2065);
                    timeSpecifier221 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier221.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_timedGD2067);
                    goalDesc222 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc222.getTree());
                    }

                    char_literal223 = (Token) match(input, 68, FOLLOW_68_in_timedGD2069);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal223_tree = (Object) adaptor.create(char_literal223);
                        adaptor.addChild(root_0, char_literal223_tree);
                    }

                }
                break;
                case 2: // Pddl.g:337:4: '(' 'over' interval goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal224 = (Token) match(input, 67, FOLLOW_67_in_timedGD2074);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal224_tree = (Object) adaptor.create(char_literal224);
                        adaptor.addChild(root_0, char_literal224_tree);
                    }

                    string_literal225 = (Token) match(input, 129, FOLLOW_129_in_timedGD2076);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal225_tree = (Object) adaptor.create(string_literal225);
                        adaptor.addChild(root_0, string_literal225_tree);
                    }

                    pushFollow(FOLLOW_interval_in_timedGD2078);
                    interval226 = interval();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, interval226.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_timedGD2080);
                    goalDesc227 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc227.getTree());
                    }

                    char_literal228 = (Token) match(input, 68, FOLLOW_68_in_timedGD2082);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal228_tree = (Object) adaptor.create(char_literal228);
                        adaptor.addChild(root_0, char_literal228_tree);
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
        }
        return retval;
    }

    // $ANTLR start "timeSpecifier"
    // Pddl.g:340:1: timeSpecifier : ( 'start' | 'end' );
    public final ModifiedPddlParser.timeSpecifier_return timeSpecifier ( ) throws RecognitionException {
        ModifiedPddlParser.timeSpecifier_return retval = new ModifiedPddlParser.timeSpecifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set229 = null;

        Object set229_tree = null;

        try {
            // Pddl.g:340:15: ( 'start' | 'end' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set229 = input.LT(1);
                if (input.LA(1) == 115 || input.LA(1) == 138) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set229));
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
        }
        return retval;
    }

    // $ANTLR start "interval"
    // Pddl.g:341:1: interval : 'all' ;
    public final ModifiedPddlParser.interval_return interval ( ) throws RecognitionException {
        ModifiedPddlParser.interval_return retval = new ModifiedPddlParser.interval_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal230 = null;

        Object string_literal230_tree = null;

        try {
            // Pddl.g:341:10: ( 'all' )
            // Pddl.g:341:12: 'all'
            {
                root_0 = (Object) adaptor.nil();

                string_literal230 = (Token) match(input, 103, FOLLOW_103_in_interval2104);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal230_tree = (Object) adaptor.create(string_literal230);
                    adaptor.addChild(root_0, string_literal230_tree);
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
        }
        return retval;
    }

    // $ANTLR start "derivedDef"
    // Pddl.g:345:1: derivedDef : '(' ! ':derived' ^ typedVariableList goalDesc ')' !;
    public final ModifiedPddlParser.derivedDef_return derivedDef ( ) throws RecognitionException {
        ModifiedPddlParser.derivedDef_return retval = new ModifiedPddlParser.derivedDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal231 = null;
        Token string_literal232 = null;
        Token char_literal235 = null;
        ParserRuleReturnScope typedVariableList233 = null;
        ParserRuleReturnScope goalDesc234 = null;

        Object char_literal231_tree = null;
        Object string_literal232_tree = null;
        Object char_literal235_tree = null;

        try {
            // Pddl.g:346:2: ( '(' ! ':derived' ^ typedVariableList goalDesc ')' !)
            // Pddl.g:346:4: '(' ! ':derived' ^ typedVariableList goalDesc ')' !
            {
                root_0 = (Object) adaptor.nil();

                char_literal231 = (Token) match(input, 67, FOLLOW_67_in_derivedDef2117);
                if (state.failed) {
                    return retval;
                }
                string_literal232 = (Token) match(input, 78, FOLLOW_78_in_derivedDef2120);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    string_literal232_tree = (Object) adaptor.create(string_literal232);
                    root_0 = (Object) adaptor.becomeRoot(string_literal232_tree, root_0);
                }

                pushFollow(FOLLOW_typedVariableList_in_derivedDef2123);
                typedVariableList233 = typedVariableList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, typedVariableList233.getTree());
                }

                pushFollow(FOLLOW_goalDesc_in_derivedDef2125);
                goalDesc234 = goalDesc();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, goalDesc234.getTree());
                }

                char_literal235 = (Token) match(input, 68, FOLLOW_68_in_derivedDef2127);
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
        }
        return retval;
    }

    // $ANTLR start "fExp"
    // Pddl.g:351:1: fExp : ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | fHead );
    public final ModifiedPddlParser.fExp_return fExp ( ) throws RecognitionException {
        ModifiedPddlParser.fExp_return retval = new ModifiedPddlParser.fExp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER236 = null;
        Token char_literal237 = null;
        Token char_literal241 = null;
        Token char_literal242 = null;
        Token char_literal243 = null;
        Token char_literal245 = null;
        Token char_literal246 = null;
        Token string_literal247 = null;
        Token char_literal249 = null;
        Token char_literal250 = null;
        Token string_literal251 = null;
        Token char_literal253 = null;
        ParserRuleReturnScope binaryOp238 = null;
        ParserRuleReturnScope fExp239 = null;
        ParserRuleReturnScope fExp2240 = null;
        ParserRuleReturnScope fExp244 = null;
        ParserRuleReturnScope fExp248 = null;
        ParserRuleReturnScope fExp252 = null;
        ParserRuleReturnScope fHead254 = null;

        Object NUMBER236_tree = null;
        Object char_literal237_tree = null;
        Object char_literal241_tree = null;
        Object char_literal242_tree = null;
        Object char_literal243_tree = null;
        Object char_literal245_tree = null;
        Object char_literal246_tree = null;
        Object string_literal247_tree = null;
        Object char_literal249_tree = null;
        Object char_literal250_tree = null;
        Object string_literal251_tree = null;
        Object char_literal253_tree = null;
        RewriteRuleTokenStream stream_110 = new RewriteRuleTokenStream(adaptor, "token 110");
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_134 = new RewriteRuleTokenStream(adaptor, "token 134");
        RewriteRuleTokenStream stream_71 = new RewriteRuleTokenStream(adaptor, "token 71");
        RewriteRuleSubtreeStream stream_binaryOp = new RewriteRuleSubtreeStream(adaptor, "rule binaryOp");
        RewriteRuleSubtreeStream stream_fExp2 = new RewriteRuleSubtreeStream(adaptor, "rule fExp2");
        RewriteRuleSubtreeStream stream_fExp = new RewriteRuleSubtreeStream(adaptor, "rule fExp");

        try {
            // Pddl.g:352:2: ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | fHead )
            int alt47 = 6;
            switch (input.LA(1)) {
                case NUMBER: {
                    alt47 = 1;
                }
                break;
                case 67: {
                    int LA47_2 = input.LA(2);
                    if ((synpred64_Pddl())) {
                        alt47 = 2;
                    } else if ((synpred65_Pddl())) {
                        alt47 = 3;
                    } else if ((synpred66_Pddl())) {
                        alt47 = 4;
                    } else if ((synpred67_Pddl())) {
                        alt47 = 5;
                    } else if ((true)) {
                        alt47 = 6;
                    }

                }
                break;
                case NAME:
                case 66: {
                    alt47 = 6;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 47, 0, input);
                    throw nvae;
            }
            switch (alt47) {
                case 1: // Pddl.g:352:4: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER236 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_fExp2142);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER236_tree = (Object) adaptor.create(NUMBER236);
                        adaptor.addChild(root_0, NUMBER236_tree);
                    }

                }
                break;
                case 2: // Pddl.g:353:4: '(' binaryOp fExp fExp2 ')'
                {
                    char_literal237 = (Token) match(input, 67, FOLLOW_67_in_fExp2147);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal237);
                    }

                    pushFollow(FOLLOW_binaryOp_in_fExp2149);
                    binaryOp238 = binaryOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_binaryOp.add(binaryOp238.getTree());
                    }
                    pushFollow(FOLLOW_fExp_in_fExp2151);
                    fExp239 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp239.getTree());
                    }
                    pushFollow(FOLLOW_fExp2_in_fExp2153);
                    fExp2240 = fExp2();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp2.add(fExp2240.getTree());
                    }
                    char_literal241 = (Token) match(input, 68, FOLLOW_68_in_fExp2155);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal241);
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
                        // 353:32: -> ^( BINARY_OP binaryOp fExp fExp2 )
                        {
                            // Pddl.g:353:35: ^( BINARY_OP binaryOp fExp fExp2 )
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
                case 3: // Pddl.g:354:4: '(' '-' fExp ')'
                {
                    char_literal242 = (Token) match(input, 67, FOLLOW_67_in_fExp2172);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal242);
                    }

                    char_literal243 = (Token) match(input, 71, FOLLOW_71_in_fExp2174);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_71.add(char_literal243);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2176);
                    fExp244 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp244.getTree());
                    }
                    char_literal245 = (Token) match(input, 68, FOLLOW_68_in_fExp2178);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal245);
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
                        // 354:21: -> ^( UNARY_MINUS fExp )
                        {
                            // Pddl.g:354:24: ^( UNARY_MINUS fExp )
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
                case 4: // Pddl.g:355:7: '(' 'sin' fExp ')'
                {
                    char_literal246 = (Token) match(input, 67, FOLLOW_67_in_fExp2194);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal246);
                    }

                    string_literal247 = (Token) match(input, 134, FOLLOW_134_in_fExp2196);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_134.add(string_literal247);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2198);
                    fExp248 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp248.getTree());
                    }
                    char_literal249 = (Token) match(input, 68, FOLLOW_68_in_fExp2200);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal249);
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
                        // 355:26: -> ^( SIN fExp )
                        {
                            // Pddl.g:355:29: ^( SIN fExp )
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
                case 5: // Pddl.g:356:7: '(' 'cos' fExp ')'
                {
                    char_literal250 = (Token) match(input, 67, FOLLOW_67_in_fExp2216);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal250);
                    }

                    string_literal251 = (Token) match(input, 110, FOLLOW_110_in_fExp2218);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_110.add(string_literal251);
                    }

                    pushFollow(FOLLOW_fExp_in_fExp2220);
                    fExp252 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp252.getTree());
                    }
                    char_literal253 = (Token) match(input, 68, FOLLOW_68_in_fExp2222);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal253);
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
                        // 356:26: -> ^( COS fExp )
                        {
                            // Pddl.g:356:29: ^( COS fExp )
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
                case 6: // Pddl.g:357:4: fHead
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fHead_in_fExp2235);
                    fHead254 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead254.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "fExp2"
    // Pddl.g:362:1: fExp2 : fExp ;
    public final ModifiedPddlParser.fExp2_return fExp2 ( ) throws RecognitionException {
        ModifiedPddlParser.fExp2_return retval = new ModifiedPddlParser.fExp2_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope fExp255 = null;

        try {
            // Pddl.g:362:7: ( fExp )
            // Pddl.g:362:9: fExp
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_fExp_in_fExp22247);
                fExp255 = fExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExp255.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "fHead"
    // Pddl.g:364:1: fHead : ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) );
    public final ModifiedPddlParser.fHead_return fHead ( ) throws RecognitionException {
        ModifiedPddlParser.fHead_return retval = new ModifiedPddlParser.fHead_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal256 = null;
        Token char_literal259 = null;
        ParserRuleReturnScope functionSymbol257 = null;
        ParserRuleReturnScope term258 = null;
        ParserRuleReturnScope functionSymbol260 = null;

        Object char_literal256_tree = null;
        Object char_literal259_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(adaptor, "rule term");
        RewriteRuleSubtreeStream stream_functionSymbol = new RewriteRuleSubtreeStream(adaptor, "rule functionSymbol");

        try {
            // Pddl.g:365:2: ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) )
            int alt49 = 2;
            int LA49_0 = input.LA(1);
            if ((LA49_0 == 67)) {
                alt49 = 1;
            } else if ((LA49_0 == NAME || LA49_0 == 66)) {
                alt49 = 2;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 49, 0, input);
                throw nvae;
            }

            switch (alt49) {
                case 1: // Pddl.g:365:4: '(' functionSymbol ( term )* ')'
                {
                    char_literal256 = (Token) match(input, 67, FOLLOW_67_in_fHead2257);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal256);
                    }

                    pushFollow(FOLLOW_functionSymbol_in_fHead2259);
                    functionSymbol257 = functionSymbol();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_functionSymbol.add(functionSymbol257.getTree());
                    }
                    // Pddl.g:365:23: ( term )*
                    loop48:
                    while (true) {
                        int alt48 = 2;
                        int LA48_0 = input.LA(1);
                        if ((LA48_0 == NAME || LA48_0 == VARIABLE)) {
                            alt48 = 1;
                        }

                        switch (alt48) {
                            case 1: // Pddl.g:365:23: term
                            {
                                pushFollow(FOLLOW_term_in_fHead2261);
                                term258 = term();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_term.add(term258.getTree());
                                }
                            }
                            break;

                            default:
                                break loop48;
                        }
                    }

                    char_literal259 = (Token) match(input, 68, FOLLOW_68_in_fHead2264);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal259);
                    }

                    // AST REWRITE
                    // elements: term, functionSymbol
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 365:33: -> ^( FUNC_HEAD functionSymbol ( term )* )
                        {
                            // Pddl.g:365:36: ^( FUNC_HEAD functionSymbol ( term )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FUNC_HEAD, "FUNC_HEAD"), root_1);
                                adaptor.addChild(root_1, stream_functionSymbol.nextTree());
                                // Pddl.g:365:63: ( term )*
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
                case 2: // Pddl.g:366:4: functionSymbol
                {
                    pushFollow(FOLLOW_functionSymbol_in_fHead2280);
                    functionSymbol260 = functionSymbol();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_functionSymbol.add(functionSymbol260.getTree());
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
                        // 366:19: -> ^( FUNC_HEAD functionSymbol )
                        {
                            // Pddl.g:366:22: ^( FUNC_HEAD functionSymbol )
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
        }
        return retval;
    }

    // $ANTLR start "effect"
    // Pddl.g:369:1: effect : ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect );
    public final ModifiedPddlParser.effect_return effect ( ) throws RecognitionException {
        ModifiedPddlParser.effect_return retval = new ModifiedPddlParser.effect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal261 = null;
        Token string_literal262 = null;
        Token char_literal264 = null;
        ParserRuleReturnScope cEffect263 = null;
        ParserRuleReturnScope cEffect265 = null;

        Object char_literal261_tree = null;
        Object string_literal262_tree = null;
        Object char_literal264_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(adaptor, "token 106");
        RewriteRuleSubtreeStream stream_cEffect = new RewriteRuleSubtreeStream(adaptor, "rule cEffect");

        try {
            // Pddl.g:370:2: ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect )
            int alt51 = 2;
            int LA51_0 = input.LA(1);
            if ((LA51_0 == 67)) {
                int LA51_1 = input.LA(2);
                if ((LA51_1 == 106)) {
                    alt51 = 1;
                } else if ((LA51_1 == NAME || LA51_1 == 107 || LA51_1 == 111 || LA51_1 == 117 || LA51_1 == 121 || LA51_1 == 125 || (LA51_1 >= 132 && LA51_1 <= 133) || LA51_1 == 140)) {
                    alt51 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 51, 1, input);
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
                        = new NoViableAltException("", 51, 0, input);
                throw nvae;
            }

            switch (alt51) {
                case 1: // Pddl.g:370:4: '(' 'and' ( cEffect )* ')'
                {
                    char_literal261 = (Token) match(input, 67, FOLLOW_67_in_effect2299);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal261);
                    }

                    string_literal262 = (Token) match(input, 106, FOLLOW_106_in_effect2301);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_106.add(string_literal262);
                    }

                    // Pddl.g:370:14: ( cEffect )*
                    loop50:
                    while (true) {
                        int alt50 = 2;
                        int LA50_0 = input.LA(1);
                        if ((LA50_0 == 67)) {
                            alt50 = 1;
                        }

                        switch (alt50) {
                            case 1: // Pddl.g:370:14: cEffect
                            {
                                pushFollow(FOLLOW_cEffect_in_effect2303);
                                cEffect263 = cEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_cEffect.add(cEffect263.getTree());
                                }
                            }
                            break;

                            default:
                                break loop50;
                        }
                    }

                    char_literal264 = (Token) match(input, 68, FOLLOW_68_in_effect2306);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal264);
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
                        // 370:27: -> ^( AND_EFFECT ( cEffect )* )
                        {
                            // Pddl.g:370:30: ^( AND_EFFECT ( cEffect )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
                                // Pddl.g:370:43: ( cEffect )*
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
                case 2: // Pddl.g:371:4: cEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_cEffect_in_effect2320);
                    cEffect265 = cEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, cEffect265.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "cEffect"
    // Pddl.g:374:1: cEffect : ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect );
    public final ModifiedPddlParser.cEffect_return cEffect ( ) throws RecognitionException {
        ModifiedPddlParser.cEffect_return retval = new ModifiedPddlParser.cEffect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal266 = null;
        Token string_literal267 = null;
        Token char_literal268 = null;
        Token char_literal270 = null;
        Token char_literal272 = null;
        Token char_literal273 = null;
        Token string_literal274 = null;
        Token char_literal277 = null;
        ParserRuleReturnScope typedVariableList269 = null;
        ParserRuleReturnScope effect271 = null;
        ParserRuleReturnScope goalDesc275 = null;
        ParserRuleReturnScope condEffect276 = null;
        ParserRuleReturnScope pEffect278 = null;

        Object char_literal266_tree = null;
        Object string_literal267_tree = null;
        Object char_literal268_tree = null;
        Object char_literal270_tree = null;
        Object char_literal272_tree = null;
        Object char_literal273_tree = null;
        Object string_literal274_tree = null;
        Object char_literal277_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_117 = new RewriteRuleTokenStream(adaptor, "token 117");
        RewriteRuleTokenStream stream_140 = new RewriteRuleTokenStream(adaptor, "token 140");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList = new RewriteRuleSubtreeStream(adaptor, "rule typedVariableList");
        RewriteRuleSubtreeStream stream_effect = new RewriteRuleSubtreeStream(adaptor, "rule effect");
        RewriteRuleSubtreeStream stream_condEffect = new RewriteRuleSubtreeStream(adaptor, "rule condEffect");

        try {
            // Pddl.g:375:2: ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect )
            int alt52 = 3;
            int LA52_0 = input.LA(1);
            if ((LA52_0 == 67)) {
                switch (input.LA(2)) {
                    case 117: {
                        alt52 = 1;
                    }
                    break;
                    case 140: {
                        alt52 = 2;
                    }
                    break;
                    case NAME:
                    case 107:
                    case 111:
                    case 121:
                    case 125:
                    case 132:
                    case 133: {
                        alt52 = 3;
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
                case 1: // Pddl.g:375:4: '(' 'forall' '(' typedVariableList ')' effect ')'
                {
                    char_literal266 = (Token) match(input, 67, FOLLOW_67_in_cEffect2331);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal266);
                    }

                    string_literal267 = (Token) match(input, 117, FOLLOW_117_in_cEffect2333);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_117.add(string_literal267);
                    }

                    char_literal268 = (Token) match(input, 67, FOLLOW_67_in_cEffect2335);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal268);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_cEffect2337);
                    typedVariableList269 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_typedVariableList.add(typedVariableList269.getTree());
                    }
                    char_literal270 = (Token) match(input, 68, FOLLOW_68_in_cEffect2339);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal270);
                    }

                    pushFollow(FOLLOW_effect_in_cEffect2341);
                    effect271 = effect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_effect.add(effect271.getTree());
                    }
                    char_literal272 = (Token) match(input, 68, FOLLOW_68_in_cEffect2343);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal272);
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
                        // 376:4: -> ^( FORALL_EFFECT typedVariableList effect )
                        {
                            // Pddl.g:376:7: ^( FORALL_EFFECT typedVariableList effect )
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
                case 2: // Pddl.g:377:4: '(' 'when' goalDesc condEffect ')'
                {
                    char_literal273 = (Token) match(input, 67, FOLLOW_67_in_cEffect2361);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal273);
                    }

                    string_literal274 = (Token) match(input, 140, FOLLOW_140_in_cEffect2363);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_140.add(string_literal274);
                    }

                    pushFollow(FOLLOW_goalDesc_in_cEffect2365);
                    goalDesc275 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc275.getTree());
                    }
                    pushFollow(FOLLOW_condEffect_in_cEffect2367);
                    condEffect276 = condEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_condEffect.add(condEffect276.getTree());
                    }
                    char_literal277 = (Token) match(input, 68, FOLLOW_68_in_cEffect2369);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal277);
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
                        // 378:4: -> ^( WHEN_EFFECT goalDesc condEffect )
                        {
                            // Pddl.g:378:7: ^( WHEN_EFFECT goalDesc condEffect )
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
                case 3: // Pddl.g:379:4: pEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_pEffect_in_cEffect2387);
                    pEffect278 = pEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, pEffect278.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "pEffect"
    // Pddl.g:382:1: pEffect : ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula );
    public final ModifiedPddlParser.pEffect_return pEffect ( ) throws RecognitionException {
        ModifiedPddlParser.pEffect_return retval = new ModifiedPddlParser.pEffect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal279 = null;
        Token char_literal283 = null;
        Token char_literal284 = null;
        Token string_literal285 = null;
        Token char_literal287 = null;
        ParserRuleReturnScope assignOp280 = null;
        ParserRuleReturnScope fHead281 = null;
        ParserRuleReturnScope fExp282 = null;
        ParserRuleReturnScope atomicTermFormula286 = null;
        ParserRuleReturnScope atomicTermFormula288 = null;

        Object char_literal279_tree = null;
        Object char_literal283_tree = null;
        Object char_literal284_tree = null;
        Object string_literal285_tree = null;
        Object char_literal287_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_125 = new RewriteRuleTokenStream(adaptor, "token 125");
        RewriteRuleSubtreeStream stream_atomicTermFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicTermFormula");
        RewriteRuleSubtreeStream stream_assignOp = new RewriteRuleSubtreeStream(adaptor, "rule assignOp");
        RewriteRuleSubtreeStream stream_fExp = new RewriteRuleSubtreeStream(adaptor, "rule fExp");
        RewriteRuleSubtreeStream stream_fHead = new RewriteRuleSubtreeStream(adaptor, "rule fHead");

        try {
            // Pddl.g:383:2: ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula )
            int alt53 = 3;
            int LA53_0 = input.LA(1);
            if ((LA53_0 == 67)) {
                switch (input.LA(2)) {
                    case 125: {
                        alt53 = 2;
                    }
                    break;
                    case 107:
                    case 111:
                    case 121:
                    case 132:
                    case 133: {
                        alt53 = 1;
                    }
                    break;
                    case NAME: {
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
                case 1: // Pddl.g:383:4: '(' assignOp fHead fExp ')'
                {
                    char_literal279 = (Token) match(input, 67, FOLLOW_67_in_pEffect2398);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal279);
                    }

                    pushFollow(FOLLOW_assignOp_in_pEffect2400);
                    assignOp280 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_assignOp.add(assignOp280.getTree());
                    }
                    pushFollow(FOLLOW_fHead_in_pEffect2402);
                    fHead281 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fHead.add(fHead281.getTree());
                    }
                    pushFollow(FOLLOW_fExp_in_pEffect2404);
                    fExp282 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fExp.add(fExp282.getTree());
                    }
                    char_literal283 = (Token) match(input, 68, FOLLOW_68_in_pEffect2406);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal283);
                    }

                    // AST REWRITE
                    // elements: fExp, fHead, assignOp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 384:4: -> ^( ASSIGN_EFFECT assignOp fHead fExp )
                        {
                            // Pddl.g:384:7: ^( ASSIGN_EFFECT assignOp fHead fExp )
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
                case 2: // Pddl.g:385:4: '(' 'not' atomicTermFormula ')'
                {
                    char_literal284 = (Token) match(input, 67, FOLLOW_67_in_pEffect2426);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal284);
                    }

                    string_literal285 = (Token) match(input, 125, FOLLOW_125_in_pEffect2428);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_125.add(string_literal285);
                    }

                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2430);
                    atomicTermFormula286 = atomicTermFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicTermFormula.add(atomicTermFormula286.getTree());
                    }
                    char_literal287 = (Token) match(input, 68, FOLLOW_68_in_pEffect2432);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal287);
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
                        // 386:4: -> ^( NOT_EFFECT atomicTermFormula )
                        {
                            // Pddl.g:386:7: ^( NOT_EFFECT atomicTermFormula )
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
                case 3: // Pddl.g:387:4: atomicTermFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2448);
                    atomicTermFormula288 = atomicTermFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, atomicTermFormula288.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "condEffect"
    // Pddl.g:392:1: condEffect : ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect );
    public final ModifiedPddlParser.condEffect_return condEffect ( ) throws RecognitionException {
        ModifiedPddlParser.condEffect_return retval = new ModifiedPddlParser.condEffect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal289 = null;
        Token string_literal290 = null;
        Token char_literal292 = null;
        ParserRuleReturnScope pEffect291 = null;
        ParserRuleReturnScope pEffect293 = null;

        Object char_literal289_tree = null;
        Object string_literal290_tree = null;
        Object char_literal292_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(adaptor, "token 106");
        RewriteRuleSubtreeStream stream_pEffect = new RewriteRuleSubtreeStream(adaptor, "rule pEffect");

        try {
            // Pddl.g:393:2: ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect )
            int alt55 = 2;
            int LA55_0 = input.LA(1);
            if ((LA55_0 == 67)) {
                int LA55_1 = input.LA(2);
                if ((LA55_1 == 106)) {
                    alt55 = 1;
                } else if ((LA55_1 == NAME || LA55_1 == 107 || LA55_1 == 111 || LA55_1 == 121 || LA55_1 == 125 || (LA55_1 >= 132 && LA55_1 <= 133))) {
                    alt55 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae
                                = new NoViableAltException("", 55, 1, input);
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
                        = new NoViableAltException("", 55, 0, input);
                throw nvae;
            }

            switch (alt55) {
                case 1: // Pddl.g:393:4: '(' 'and' ( pEffect )* ')'
                {
                    char_literal289 = (Token) match(input, 67, FOLLOW_67_in_condEffect2461);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal289);
                    }

                    string_literal290 = (Token) match(input, 106, FOLLOW_106_in_condEffect2463);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_106.add(string_literal290);
                    }

                    // Pddl.g:393:14: ( pEffect )*
                    loop54:
                    while (true) {
                        int alt54 = 2;
                        int LA54_0 = input.LA(1);
                        if ((LA54_0 == 67)) {
                            alt54 = 1;
                        }

                        switch (alt54) {
                            case 1: // Pddl.g:393:14: pEffect
                            {
                                pushFollow(FOLLOW_pEffect_in_condEffect2465);
                                pEffect291 = pEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_pEffect.add(pEffect291.getTree());
                                }
                            }
                            break;

                            default:
                                break loop54;
                        }
                    }

                    char_literal292 = (Token) match(input, 68, FOLLOW_68_in_condEffect2468);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal292);
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
                        // 393:27: -> ^( AND_EFFECT ( pEffect )* )
                        {
                            // Pddl.g:393:30: ^( AND_EFFECT ( pEffect )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
                                // Pddl.g:393:43: ( pEffect )*
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
                case 2: // Pddl.g:394:4: pEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_pEffect_in_condEffect2482);
                    pEffect293 = pEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, pEffect293.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "binaryOp"
    // Pddl.g:399:1: binaryOp : ( '*' | '+' | '-' | '/' | '^' );
    public final ModifiedPddlParser.binaryOp_return binaryOp ( ) throws RecognitionException {
        ModifiedPddlParser.binaryOp_return retval = new ModifiedPddlParser.binaryOp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set294 = null;

        Object set294_tree = null;

        try {
            // Pddl.g:399:10: ( '*' | '+' | '-' | '/' | '^' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set294 = input.LT(1);
                if ((input.LA(1) >= 69 && input.LA(1) <= 72) || input.LA(1) == 102) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set294));
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
        }
        return retval;
    }

    // $ANTLR start "multiOp"
    // Pddl.g:401:1: multiOp : ( '*' | '+' );
    public final ModifiedPddlParser.multiOp_return multiOp ( ) throws RecognitionException {
        ModifiedPddlParser.multiOp_return retval = new ModifiedPddlParser.multiOp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set295 = null;

        Object set295_tree = null;

        try {
            // Pddl.g:401:9: ( '*' | '+' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set295 = input.LT(1);
                if ((input.LA(1) >= 69 && input.LA(1) <= 70)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set295));
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
        }
        return retval;
    }

    // $ANTLR start "binaryComp"
    // Pddl.g:403:1: binaryComp : ( '>' | '<' | '=' | '>=' | '<=' );
    public final ModifiedPddlParser.binaryComp_return binaryComp ( ) throws RecognitionException {
        ModifiedPddlParser.binaryComp_return retval = new ModifiedPddlParser.binaryComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set296 = null;

        Object set296_tree = null;

        try {
            // Pddl.g:403:12: ( '>' | '<' | '=' | '>=' | '<=' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set296 = input.LT(1);
                if ((input.LA(1) >= 96 && input.LA(1) <= 100)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set296));
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
        }
        return retval;
    }

    // $ANTLR start "assignOp"
    // Pddl.g:405:1: assignOp : ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' );
    public final ModifiedPddlParser.assignOp_return assignOp ( ) throws RecognitionException {
        ModifiedPddlParser.assignOp_return retval = new ModifiedPddlParser.assignOp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set297 = null;

        Object set297_tree = null;

        try {
            // Pddl.g:405:10: ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set297 = input.LT(1);
                if (input.LA(1) == 107 || input.LA(1) == 111 || input.LA(1) == 121 || (input.LA(1) >= 132 && input.LA(1) <= 133)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set297));
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
        }
        return retval;
    }

    // $ANTLR start "durationConstraint"
    // Pddl.g:410:1: durationConstraint : ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint );
    public final ModifiedPddlParser.durationConstraint_return durationConstraint ( ) throws RecognitionException {
        ModifiedPddlParser.durationConstraint_return retval = new ModifiedPddlParser.durationConstraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal298 = null;
        Token string_literal299 = null;
        Token char_literal301 = null;
        Token char_literal302 = null;
        Token char_literal303 = null;
        ParserRuleReturnScope simpleDurationConstraint300 = null;
        ParserRuleReturnScope simpleDurationConstraint304 = null;

        Object char_literal298_tree = null;
        Object string_literal299_tree = null;
        Object char_literal301_tree = null;
        Object char_literal302_tree = null;
        Object char_literal303_tree = null;

        try {
            // Pddl.g:411:2: ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint )
            int alt57 = 3;
            int LA57_0 = input.LA(1);
            if ((LA57_0 == 67)) {
                switch (input.LA(2)) {
                    case 106: {
                        alt57 = 1;
                    }
                    break;
                    case 68: {
                        alt57 = 2;
                    }
                    break;
                    case 97:
                    case 98:
                    case 100:
                    case 108: {
                        alt57 = 3;
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
                                    = new NoViableAltException("", 57, 1, input);
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
                        = new NoViableAltException("", 57, 0, input);
                throw nvae;
            }

            switch (alt57) {
                case 1: // Pddl.g:411:4: '(' 'and' ( simpleDurationConstraint )+ ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal298 = (Token) match(input, 67, FOLLOW_67_in_durationConstraint2587);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal298_tree = (Object) adaptor.create(char_literal298);
                        adaptor.addChild(root_0, char_literal298_tree);
                    }

                    string_literal299 = (Token) match(input, 106, FOLLOW_106_in_durationConstraint2589);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal299_tree = (Object) adaptor.create(string_literal299);
                        adaptor.addChild(root_0, string_literal299_tree);
                    }

                    // Pddl.g:411:14: ( simpleDurationConstraint )+
                    int cnt56 = 0;
                    loop56:
                    while (true) {
                        int alt56 = 2;
                        int LA56_0 = input.LA(1);
                        if ((LA56_0 == 67)) {
                            alt56 = 1;
                        }

                        switch (alt56) {
                            case 1: // Pddl.g:411:14: simpleDurationConstraint
                            {
                                pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2591);
                                simpleDurationConstraint300 = simpleDurationConstraint();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, simpleDurationConstraint300.getTree());
                                }

                            }
                            break;

                            default:
                                if (cnt56 >= 1) {
                                    break loop56;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(56, input);
                                throw eee;
                        }
                        cnt56++;
                    }

                    char_literal301 = (Token) match(input, 68, FOLLOW_68_in_durationConstraint2594);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal301_tree = (Object) adaptor.create(char_literal301);
                        adaptor.addChild(root_0, char_literal301_tree);
                    }

                }
                break;
                case 2: // Pddl.g:412:4: '(' ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal302 = (Token) match(input, 67, FOLLOW_67_in_durationConstraint2599);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal302_tree = (Object) adaptor.create(char_literal302);
                        adaptor.addChild(root_0, char_literal302_tree);
                    }

                    char_literal303 = (Token) match(input, 68, FOLLOW_68_in_durationConstraint2601);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal303_tree = (Object) adaptor.create(char_literal303);
                        adaptor.addChild(root_0, char_literal303_tree);
                    }

                }
                break;
                case 3: // Pddl.g:413:4: simpleDurationConstraint
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2606);
                    simpleDurationConstraint304 = simpleDurationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, simpleDurationConstraint304.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "simpleDurationConstraint"
    // Pddl.g:416:1: simpleDurationConstraint : ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' );
    public final ModifiedPddlParser.simpleDurationConstraint_return simpleDurationConstraint ( ) throws RecognitionException {
        ModifiedPddlParser.simpleDurationConstraint_return retval = new ModifiedPddlParser.simpleDurationConstraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal305 = null;
        Token string_literal307 = null;
        Token char_literal309 = null;
        Token char_literal310 = null;
        Token string_literal311 = null;
        Token char_literal314 = null;
        ParserRuleReturnScope durOp306 = null;
        ParserRuleReturnScope durValue308 = null;
        ParserRuleReturnScope timeSpecifier312 = null;
        ParserRuleReturnScope simpleDurationConstraint313 = null;

        Object char_literal305_tree = null;
        Object string_literal307_tree = null;
        Object char_literal309_tree = null;
        Object char_literal310_tree = null;
        Object string_literal311_tree = null;
        Object char_literal314_tree = null;

        try {
            // Pddl.g:417:2: ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' )
            int alt58 = 2;
            int LA58_0 = input.LA(1);
            if ((LA58_0 == 67)) {
                int LA58_1 = input.LA(2);
                if ((LA58_1 == 108)) {
                    alt58 = 2;
                } else if (((LA58_1 >= 97 && LA58_1 <= 98) || LA58_1 == 100)) {
                    alt58 = 1;
                } else {
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
                case 1: // Pddl.g:417:4: '(' durOp '?duration' durValue ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal305 = (Token) match(input, 67, FOLLOW_67_in_simpleDurationConstraint2617);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal305_tree = (Object) adaptor.create(char_literal305);
                        adaptor.addChild(root_0, char_literal305_tree);
                    }

                    pushFollow(FOLLOW_durOp_in_simpleDurationConstraint2619);
                    durOp306 = durOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durOp306.getTree());
                    }

                    string_literal307 = (Token) match(input, 101, FOLLOW_101_in_simpleDurationConstraint2621);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal307_tree = (Object) adaptor.create(string_literal307);
                        adaptor.addChild(root_0, string_literal307_tree);
                    }

                    pushFollow(FOLLOW_durValue_in_simpleDurationConstraint2623);
                    durValue308 = durValue();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, durValue308.getTree());
                    }

                    char_literal309 = (Token) match(input, 68, FOLLOW_68_in_simpleDurationConstraint2625);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal309_tree = (Object) adaptor.create(char_literal309);
                        adaptor.addChild(root_0, char_literal309_tree);
                    }

                }
                break;
                case 2: // Pddl.g:418:4: '(' 'at' timeSpecifier simpleDurationConstraint ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal310 = (Token) match(input, 67, FOLLOW_67_in_simpleDurationConstraint2630);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal310_tree = (Object) adaptor.create(char_literal310);
                        adaptor.addChild(root_0, char_literal310_tree);
                    }

                    string_literal311 = (Token) match(input, 108, FOLLOW_108_in_simpleDurationConstraint2632);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal311_tree = (Object) adaptor.create(string_literal311);
                        adaptor.addChild(root_0, string_literal311_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_simpleDurationConstraint2634);
                    timeSpecifier312 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier312.getTree());
                    }

                    pushFollow(FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2636);
                    simpleDurationConstraint313 = simpleDurationConstraint();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, simpleDurationConstraint313.getTree());
                    }

                    char_literal314 = (Token) match(input, 68, FOLLOW_68_in_simpleDurationConstraint2638);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal314_tree = (Object) adaptor.create(char_literal314);
                        adaptor.addChild(root_0, char_literal314_tree);
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
        }
        return retval;
    }

    // $ANTLR start "durOp"
    // Pddl.g:421:1: durOp : ( '<=' | '>=' | '=' );
    public final ModifiedPddlParser.durOp_return durOp ( ) throws RecognitionException {
        ModifiedPddlParser.durOp_return retval = new ModifiedPddlParser.durOp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set315 = null;

        Object set315_tree = null;

        try {
            // Pddl.g:421:7: ( '<=' | '>=' | '=' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set315 = input.LT(1);
                if ((input.LA(1) >= 97 && input.LA(1) <= 98) || input.LA(1) == 100) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set315));
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
        }
        return retval;
    }

    // $ANTLR start "durValue"
    // Pddl.g:423:1: durValue : ( NUMBER | fExp );
    public final ModifiedPddlParser.durValue_return durValue ( ) throws RecognitionException {
        ModifiedPddlParser.durValue_return retval = new ModifiedPddlParser.durValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER316 = null;
        ParserRuleReturnScope fExp317 = null;

        Object NUMBER316_tree = null;

        try {
            // Pddl.g:423:10: ( NUMBER | fExp )
            int alt59 = 2;
            int LA59_0 = input.LA(1);
            if ((LA59_0 == NUMBER)) {
                int LA59_1 = input.LA(2);
                if ((synpred97_Pddl())) {
                    alt59 = 1;
                } else if ((true)) {
                    alt59 = 2;
                }

            } else if ((LA59_0 == NAME || (LA59_0 >= 66 && LA59_0 <= 67))) {
                alt59 = 2;
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
                case 1: // Pddl.g:423:12: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER316 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_durValue2665);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER316_tree = (Object) adaptor.create(NUMBER316);
                        adaptor.addChild(root_0, NUMBER316_tree);
                    }

                }
                break;
                case 2: // Pddl.g:423:21: fExp
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fExp_in_durValue2669);
                    fExp317 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp317.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "daEffect"
    // Pddl.g:425:1: daEffect : ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' );
    public final ModifiedPddlParser.daEffect_return daEffect ( ) throws RecognitionException {
        ModifiedPddlParser.daEffect_return retval = new ModifiedPddlParser.daEffect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal318 = null;
        Token string_literal319 = null;
        Token char_literal321 = null;
        Token char_literal323 = null;
        Token string_literal324 = null;
        Token char_literal325 = null;
        Token char_literal327 = null;
        Token char_literal329 = null;
        Token char_literal330 = null;
        Token string_literal331 = null;
        Token char_literal334 = null;
        Token char_literal335 = null;
        Token char_literal339 = null;
        ParserRuleReturnScope daEffect320 = null;
        ParserRuleReturnScope timedEffect322 = null;
        ParserRuleReturnScope typedVariableList326 = null;
        ParserRuleReturnScope daEffect328 = null;
        ParserRuleReturnScope daGD332 = null;
        ParserRuleReturnScope timedEffect333 = null;
        ParserRuleReturnScope assignOp336 = null;
        ParserRuleReturnScope fHead337 = null;
        ParserRuleReturnScope fExpDA338 = null;

        Object char_literal318_tree = null;
        Object string_literal319_tree = null;
        Object char_literal321_tree = null;
        Object char_literal323_tree = null;
        Object string_literal324_tree = null;
        Object char_literal325_tree = null;
        Object char_literal327_tree = null;
        Object char_literal329_tree = null;
        Object char_literal330_tree = null;
        Object string_literal331_tree = null;
        Object char_literal334_tree = null;
        Object char_literal335_tree = null;
        Object char_literal339_tree = null;

        try {
            // Pddl.g:426:2: ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' )
            int alt61 = 5;
            int LA61_0 = input.LA(1);
            if ((LA61_0 == 67)) {
                int LA61_1 = input.LA(2);
                if ((synpred99_Pddl())) {
                    alt61 = 1;
                } else if ((synpred100_Pddl())) {
                    alt61 = 2;
                } else if ((synpred101_Pddl())) {
                    alt61 = 3;
                } else if ((synpred102_Pddl())) {
                    alt61 = 4;
                } else if ((true)) {
                    alt61 = 5;
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 61, 0, input);
                throw nvae;
            }

            switch (alt61) {
                case 1: // Pddl.g:426:4: '(' 'and' ( daEffect )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal318 = (Token) match(input, 67, FOLLOW_67_in_daEffect2679);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal318_tree = (Object) adaptor.create(char_literal318);
                        adaptor.addChild(root_0, char_literal318_tree);
                    }

                    string_literal319 = (Token) match(input, 106, FOLLOW_106_in_daEffect2681);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal319_tree = (Object) adaptor.create(string_literal319);
                        adaptor.addChild(root_0, string_literal319_tree);
                    }

                    // Pddl.g:426:14: ( daEffect )*
                    loop60:
                    while (true) {
                        int alt60 = 2;
                        int LA60_0 = input.LA(1);
                        if ((LA60_0 == 67)) {
                            alt60 = 1;
                        }

                        switch (alt60) {
                            case 1: // Pddl.g:426:14: daEffect
                            {
                                pushFollow(FOLLOW_daEffect_in_daEffect2683);
                                daEffect320 = daEffect();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, daEffect320.getTree());
                                }

                            }
                            break;

                            default:
                                break loop60;
                        }
                    }

                    char_literal321 = (Token) match(input, 68, FOLLOW_68_in_daEffect2686);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal321_tree = (Object) adaptor.create(char_literal321);
                        adaptor.addChild(root_0, char_literal321_tree);
                    }

                }
                break;
                case 2: // Pddl.g:427:4: timedEffect
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_timedEffect_in_daEffect2691);
                    timedEffect322 = timedEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedEffect322.getTree());
                    }

                }
                break;
                case 3: // Pddl.g:428:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal323 = (Token) match(input, 67, FOLLOW_67_in_daEffect2696);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal323_tree = (Object) adaptor.create(char_literal323);
                        adaptor.addChild(root_0, char_literal323_tree);
                    }

                    string_literal324 = (Token) match(input, 117, FOLLOW_117_in_daEffect2698);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal324_tree = (Object) adaptor.create(string_literal324);
                        adaptor.addChild(root_0, string_literal324_tree);
                    }

                    char_literal325 = (Token) match(input, 67, FOLLOW_67_in_daEffect2700);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal325_tree = (Object) adaptor.create(char_literal325);
                        adaptor.addChild(root_0, char_literal325_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daEffect2702);
                    typedVariableList326 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList326.getTree());
                    }

                    char_literal327 = (Token) match(input, 68, FOLLOW_68_in_daEffect2704);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal327_tree = (Object) adaptor.create(char_literal327);
                        adaptor.addChild(root_0, char_literal327_tree);
                    }

                    pushFollow(FOLLOW_daEffect_in_daEffect2706);
                    daEffect328 = daEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daEffect328.getTree());
                    }

                    char_literal329 = (Token) match(input, 68, FOLLOW_68_in_daEffect2708);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal329_tree = (Object) adaptor.create(char_literal329);
                        adaptor.addChild(root_0, char_literal329_tree);
                    }

                }
                break;
                case 4: // Pddl.g:429:4: '(' 'when' daGD timedEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal330 = (Token) match(input, 67, FOLLOW_67_in_daEffect2713);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal330_tree = (Object) adaptor.create(char_literal330);
                        adaptor.addChild(root_0, char_literal330_tree);
                    }

                    string_literal331 = (Token) match(input, 140, FOLLOW_140_in_daEffect2715);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal331_tree = (Object) adaptor.create(string_literal331);
                        adaptor.addChild(root_0, string_literal331_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daEffect2717);
                    daGD332 = daGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daGD332.getTree());
                    }

                    pushFollow(FOLLOW_timedEffect_in_daEffect2719);
                    timedEffect333 = timedEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timedEffect333.getTree());
                    }

                    char_literal334 = (Token) match(input, 68, FOLLOW_68_in_daEffect2721);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal334_tree = (Object) adaptor.create(char_literal334);
                        adaptor.addChild(root_0, char_literal334_tree);
                    }

                }
                break;
                case 5: // Pddl.g:430:4: '(' assignOp fHead fExpDA ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal335 = (Token) match(input, 67, FOLLOW_67_in_daEffect2726);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal335_tree = (Object) adaptor.create(char_literal335);
                        adaptor.addChild(root_0, char_literal335_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_daEffect2728);
                    assignOp336 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, assignOp336.getTree());
                    }

                    pushFollow(FOLLOW_fHead_in_daEffect2730);
                    fHead337 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead337.getTree());
                    }

                    pushFollow(FOLLOW_fExpDA_in_daEffect2732);
                    fExpDA338 = fExpDA();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExpDA338.getTree());
                    }

                    char_literal339 = (Token) match(input, 68, FOLLOW_68_in_daEffect2734);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal339_tree = (Object) adaptor.create(char_literal339);
                        adaptor.addChild(root_0, char_literal339_tree);
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
        }
        return retval;
    }

    // $ANTLR start "timedEffect"
    // Pddl.g:433:1: timedEffect : ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' );
    public final ModifiedPddlParser.timedEffect_return timedEffect ( ) throws RecognitionException {
        ModifiedPddlParser.timedEffect_return retval = new ModifiedPddlParser.timedEffect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal340 = null;
        Token string_literal341 = null;
        Token char_literal344 = null;
        Token char_literal345 = null;
        Token string_literal346 = null;
        Token char_literal349 = null;
        Token char_literal350 = null;
        Token char_literal354 = null;
        ParserRuleReturnScope timeSpecifier342 = null;
        ParserRuleReturnScope daEffect343 = null;
        ParserRuleReturnScope timeSpecifier347 = null;
        ParserRuleReturnScope fAssignDA348 = null;
        ParserRuleReturnScope assignOp351 = null;
        ParserRuleReturnScope fHead352 = null;
        ParserRuleReturnScope fExp353 = null;

        Object char_literal340_tree = null;
        Object string_literal341_tree = null;
        Object char_literal344_tree = null;
        Object char_literal345_tree = null;
        Object string_literal346_tree = null;
        Object char_literal349_tree = null;
        Object char_literal350_tree = null;
        Object char_literal354_tree = null;

        try {
            // Pddl.g:434:2: ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' )
            int alt62 = 3;
            int LA62_0 = input.LA(1);
            if ((LA62_0 == 67)) {
                int LA62_1 = input.LA(2);
                if ((synpred103_Pddl())) {
                    alt62 = 1;
                } else if ((synpred104_Pddl())) {
                    alt62 = 2;
                } else if ((true)) {
                    alt62 = 3;
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
                case 1: // Pddl.g:434:4: '(' 'at' timeSpecifier daEffect ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal340 = (Token) match(input, 67, FOLLOW_67_in_timedEffect2745);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal340_tree = (Object) adaptor.create(char_literal340);
                        adaptor.addChild(root_0, char_literal340_tree);
                    }

                    string_literal341 = (Token) match(input, 108, FOLLOW_108_in_timedEffect2747);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal341_tree = (Object) adaptor.create(string_literal341);
                        adaptor.addChild(root_0, string_literal341_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2749);
                    timeSpecifier342 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier342.getTree());
                    }

                    pushFollow(FOLLOW_daEffect_in_timedEffect2751);
                    daEffect343 = daEffect();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, daEffect343.getTree());
                    }

                    char_literal344 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2753);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal344_tree = (Object) adaptor.create(char_literal344);
                        adaptor.addChild(root_0, char_literal344_tree);
                    }

                }
                break;
                case 2: // Pddl.g:435:4: '(' 'at' timeSpecifier fAssignDA ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal345 = (Token) match(input, 67, FOLLOW_67_in_timedEffect2763);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal345_tree = (Object) adaptor.create(char_literal345);
                        adaptor.addChild(root_0, char_literal345_tree);
                    }

                    string_literal346 = (Token) match(input, 108, FOLLOW_108_in_timedEffect2765);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal346_tree = (Object) adaptor.create(string_literal346);
                        adaptor.addChild(root_0, string_literal346_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2767);
                    timeSpecifier347 = timeSpecifier();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, timeSpecifier347.getTree());
                    }

                    pushFollow(FOLLOW_fAssignDA_in_timedEffect2769);
                    fAssignDA348 = fAssignDA();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fAssignDA348.getTree());
                    }

                    char_literal349 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2771);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal349_tree = (Object) adaptor.create(char_literal349);
                        adaptor.addChild(root_0, char_literal349_tree);
                    }

                }
                break;
                case 3: // Pddl.g:436:4: '(' assignOp fHead fExp ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal350 = (Token) match(input, 67, FOLLOW_67_in_timedEffect2776);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal350_tree = (Object) adaptor.create(char_literal350);
                        adaptor.addChild(root_0, char_literal350_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_timedEffect2778);
                    assignOp351 = assignOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, assignOp351.getTree());
                    }

                    pushFollow(FOLLOW_fHead_in_timedEffect2780);
                    fHead352 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead352.getTree());
                    }

                    pushFollow(FOLLOW_fExp_in_timedEffect2782);
                    fExp353 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp353.getTree());
                    }

                    char_literal354 = (Token) match(input, 68, FOLLOW_68_in_timedEffect2784);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal354_tree = (Object) adaptor.create(char_literal354);
                        adaptor.addChild(root_0, char_literal354_tree);
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
        }
        return retval;
    }

    // $ANTLR start "fAssignDA"
    // Pddl.g:439:1: fAssignDA : '(' assignOp fHead fExpDA ')' ;
    public final ModifiedPddlParser.fAssignDA_return fAssignDA ( ) throws RecognitionException {
        ModifiedPddlParser.fAssignDA_return retval = new ModifiedPddlParser.fAssignDA_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal355 = null;
        Token char_literal359 = null;
        ParserRuleReturnScope assignOp356 = null;
        ParserRuleReturnScope fHead357 = null;
        ParserRuleReturnScope fExpDA358 = null;

        Object char_literal355_tree = null;
        Object char_literal359_tree = null;

        try {
            // Pddl.g:440:2: ( '(' assignOp fHead fExpDA ')' )
            // Pddl.g:440:4: '(' assignOp fHead fExpDA ')'
            {
                root_0 = (Object) adaptor.nil();

                char_literal355 = (Token) match(input, 67, FOLLOW_67_in_fAssignDA2804);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal355_tree = (Object) adaptor.create(char_literal355);
                    adaptor.addChild(root_0, char_literal355_tree);
                }

                pushFollow(FOLLOW_assignOp_in_fAssignDA2806);
                assignOp356 = assignOp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, assignOp356.getTree());
                }

                pushFollow(FOLLOW_fHead_in_fAssignDA2808);
                fHead357 = fHead();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fHead357.getTree());
                }

                pushFollow(FOLLOW_fExpDA_in_fAssignDA2810);
                fExpDA358 = fExpDA();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    adaptor.addChild(root_0, fExpDA358.getTree());
                }

                char_literal359 = (Token) match(input, 68, FOLLOW_68_in_fAssignDA2812);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    char_literal359_tree = (Object) adaptor.create(char_literal359);
                    adaptor.addChild(root_0, char_literal359_tree);
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
        }
        return retval;
    }

    // $ANTLR start "fExpDA"
    // Pddl.g:443:1: fExpDA : ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp );
    public final ModifiedPddlParser.fExpDA_return fExpDA ( ) throws RecognitionException {
        ModifiedPddlParser.fExpDA_return retval = new ModifiedPddlParser.fExpDA_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal360 = null;
        Token char_literal364 = null;
        Token char_literal366 = null;
        Token string_literal367 = null;
        ParserRuleReturnScope binaryOp361 = null;
        ParserRuleReturnScope fExpDA362 = null;
        ParserRuleReturnScope fExpDA363 = null;
        ParserRuleReturnScope fExpDA365 = null;
        ParserRuleReturnScope fExp368 = null;

        Object char_literal360_tree = null;
        Object char_literal364_tree = null;
        Object char_literal366_tree = null;
        Object string_literal367_tree = null;

        try {
            // Pddl.g:444:2: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp )
            int alt64 = 3;
            switch (input.LA(1)) {
                case 67: {
                    int LA64_1 = input.LA(2);
                    if ((synpred106_Pddl())) {
                        alt64 = 1;
                    } else if ((true)) {
                        alt64 = 3;
                    }

                }
                break;
                case 101: {
                    alt64 = 2;
                }
                break;
                case NAME:
                case NUMBER:
                case 66: {
                    alt64 = 3;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 64, 0, input);
                    throw nvae;
            }
            switch (alt64) {
                case 1: // Pddl.g:444:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal360 = (Token) match(input, 67, FOLLOW_67_in_fExpDA2823);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal360_tree = (Object) adaptor.create(char_literal360);
                        adaptor.addChild(root_0, char_literal360_tree);
                    }

                    // Pddl.g:444:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
                    int alt63 = 2;
                    int LA63_0 = input.LA(1);
                    if ((LA63_0 == 71)) {
                        int LA63_1 = input.LA(2);
                        if ((synpred105_Pddl())) {
                            alt63 = 1;
                        } else if ((true)) {
                            alt63 = 2;
                        }

                    } else if (((LA63_0 >= 69 && LA63_0 <= 70) || LA63_0 == 72 || LA63_0 == 102)) {
                        alt63 = 1;
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
                        case 1: // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
                        {
                            // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
                            // Pddl.g:444:10: binaryOp fExpDA fExpDA
                            {
                                pushFollow(FOLLOW_binaryOp_in_fExpDA2827);
                                binaryOp361 = binaryOp();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, binaryOp361.getTree());
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2829);
                                fExpDA362 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA362.getTree());
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2831);
                                fExpDA363 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA363.getTree());
                                }

                            }

                        }
                        break;
                        case 2: // Pddl.g:444:36: ( '-' fExpDA )
                        {
                            // Pddl.g:444:36: ( '-' fExpDA )
                            // Pddl.g:444:37: '-' fExpDA
                            {
                                char_literal364 = (Token) match(input, 71, FOLLOW_71_in_fExpDA2837);
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    char_literal364_tree = (Object) adaptor.create(char_literal364);
                                    adaptor.addChild(root_0, char_literal364_tree);
                                }

                                pushFollow(FOLLOW_fExpDA_in_fExpDA2839);
                                fExpDA365 = fExpDA();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, fExpDA365.getTree());
                                }

                            }

                        }
                        break;

                    }

                    char_literal366 = (Token) match(input, 68, FOLLOW_68_in_fExpDA2843);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal366_tree = (Object) adaptor.create(char_literal366);
                        adaptor.addChild(root_0, char_literal366_tree);
                    }

                }
                break;
                case 2: // Pddl.g:445:4: '?duration'
                {
                    root_0 = (Object) adaptor.nil();

                    string_literal367 = (Token) match(input, 101, FOLLOW_101_in_fExpDA2848);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal367_tree = (Object) adaptor.create(string_literal367);
                        adaptor.addChild(root_0, string_literal367_tree);
                    }

                }
                break;
                case 3: // Pddl.g:446:4: fExp
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fExp_in_fExpDA2853);
                    fExp368 = fExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fExp368.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "problem"
    // Pddl.g:451:1: problem : '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) ;
    public final ModifiedPddlParser.problem_return problem ( ) throws RecognitionException {
        ModifiedPddlParser.problem_return retval = new ModifiedPddlParser.problem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal369 = null;
        Token string_literal370 = null;
        Token char_literal379 = null;
        ParserRuleReturnScope problemDecl371 = null;
        ParserRuleReturnScope problemDomain372 = null;
        ParserRuleReturnScope requireDef373 = null;
        ParserRuleReturnScope objectDecl374 = null;
        ParserRuleReturnScope init375 = null;
        ParserRuleReturnScope goal376 = null;
        ParserRuleReturnScope probConstraints377 = null;
        ParserRuleReturnScope metricSpec378 = null;

        Object char_literal369_tree = null;
        Object string_literal370_tree = null;
        Object char_literal379_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_112 = new RewriteRuleTokenStream(adaptor, "token 112");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_metricSpec = new RewriteRuleSubtreeStream(adaptor, "rule metricSpec");
        RewriteRuleSubtreeStream stream_init = new RewriteRuleSubtreeStream(adaptor, "rule init");
        RewriteRuleSubtreeStream stream_goal = new RewriteRuleSubtreeStream(adaptor, "rule goal");
        RewriteRuleSubtreeStream stream_requireDef = new RewriteRuleSubtreeStream(adaptor, "rule requireDef");
        RewriteRuleSubtreeStream stream_problemDecl = new RewriteRuleSubtreeStream(adaptor, "rule problemDecl");
        RewriteRuleSubtreeStream stream_objectDecl = new RewriteRuleSubtreeStream(adaptor, "rule objectDecl");
        RewriteRuleSubtreeStream stream_problemDomain = new RewriteRuleSubtreeStream(adaptor, "rule problemDomain");
        RewriteRuleSubtreeStream stream_probConstraints = new RewriteRuleSubtreeStream(adaptor, "rule probConstraints");

        try {
            // Pddl.g:452:2: ( '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) )
            // Pddl.g:452:4: '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')'
            {
                char_literal369 = (Token) match(input, 67, FOLLOW_67_in_problem2867);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal369);
                }

                string_literal370 = (Token) match(input, 112, FOLLOW_112_in_problem2869);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_112.add(string_literal370);
                }

                pushFollow(FOLLOW_problemDecl_in_problem2871);
                problemDecl371 = problemDecl();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_problemDecl.add(problemDecl371.getTree());
                }
                pushFollow(FOLLOW_problemDomain_in_problem2876);
                problemDomain372 = problemDomain();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_problemDomain.add(problemDomain372.getTree());
                }
                // Pddl.g:454:7: ( requireDef )?
                int alt65 = 2;
                int LA65_0 = input.LA(1);
                if ((LA65_0 == 67)) {
                    int LA65_1 = input.LA(2);
                    if ((LA65_1 == 94)) {
                        alt65 = 1;
                    }
                }
                switch (alt65) {
                    case 1: // Pddl.g:454:7: requireDef
                    {
                        pushFollow(FOLLOW_requireDef_in_problem2884);
                        requireDef373 = requireDef();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_requireDef.add(requireDef373.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:455:7: ( objectDecl )?
                int alt66 = 2;
                int LA66_0 = input.LA(1);
                if ((LA66_0 == 67)) {
                    int LA66_1 = input.LA(2);
                    if ((LA66_1 == 89)) {
                        alt66 = 1;
                    }
                }
                switch (alt66) {
                    case 1: // Pddl.g:455:7: objectDecl
                    {
                        pushFollow(FOLLOW_objectDecl_in_problem2893);
                        objectDecl374 = objectDecl();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_objectDecl.add(objectDecl374.getTree());
                        }
                    }
                    break;

                }

                pushFollow(FOLLOW_init_in_problem2902);
                init375 = init();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_init.add(init375.getTree());
                }
                pushFollow(FOLLOW_goal_in_problem2910);
                goal376 = goal();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_goal.add(goal376.getTree());
                }
                // Pddl.g:458:7: ( probConstraints )?
                int alt67 = 2;
                int LA67_0 = input.LA(1);
                if ((LA67_0 == 67)) {
                    int LA67_1 = input.LA(2);
                    if ((LA67_1 == 77)) {
                        alt67 = 1;
                    }
                }
                switch (alt67) {
                    case 1: // Pddl.g:458:7: probConstraints
                    {
                        pushFollow(FOLLOW_probConstraints_in_problem2918);
                        probConstraints377 = probConstraints();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_probConstraints.add(probConstraints377.getTree());
                        }
                    }
                    break;

                }

                // Pddl.g:459:7: ( metricSpec )?
                int alt68 = 2;
                int LA68_0 = input.LA(1);
                if ((LA68_0 == 67)) {
                    alt68 = 1;
                }
                switch (alt68) {
                    case 1: // Pddl.g:459:7: metricSpec
                    {
                        pushFollow(FOLLOW_metricSpec_in_problem2927);
                        metricSpec378 = metricSpec();
                        state._fsp--;
                        if (state.failed) {
                            return retval;
                        }
                        if (state.backtracking == 0) {
                            stream_metricSpec.add(metricSpec378.getTree());
                        }
                    }
                    break;

                }

                char_literal379 = (Token) match(input, 68, FOLLOW_68_in_problem2943);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal379);
                }

                // AST REWRITE
                // elements: probConstraints, requireDef, problemDomain, objectDecl, goal, init, metricSpec, problemDecl
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 462:7: -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
                    {
                        // Pddl.g:462:10: ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PROBLEM, "PROBLEM"), root_1);
                            adaptor.addChild(root_1, stream_problemDecl.nextTree());
                            adaptor.addChild(root_1, stream_problemDomain.nextTree());
                            // Pddl.g:462:46: ( requireDef )?
                            if (stream_requireDef.hasNext()) {
                                adaptor.addChild(root_1, stream_requireDef.nextTree());
                            }
                            stream_requireDef.reset();

                            // Pddl.g:462:58: ( objectDecl )?
                            if (stream_objectDecl.hasNext()) {
                                adaptor.addChild(root_1, stream_objectDecl.nextTree());
                            }
                            stream_objectDecl.reset();

                            adaptor.addChild(root_1, stream_init.nextTree());
                            adaptor.addChild(root_1, stream_goal.nextTree());
                            // Pddl.g:463:19: ( probConstraints )?
                            if (stream_probConstraints.hasNext()) {
                                adaptor.addChild(root_1, stream_probConstraints.nextTree());
                            }
                            stream_probConstraints.reset();

                            // Pddl.g:463:36: ( metricSpec )?
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
        }
        return retval;
    }

    // $ANTLR start "problemDecl"
    // Pddl.g:466:1: problemDecl : '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) ;
    public final ModifiedPddlParser.problemDecl_return problemDecl ( ) throws RecognitionException {
        ModifiedPddlParser.problemDecl_return retval = new ModifiedPddlParser.problemDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal380 = null;
        Token string_literal381 = null;
        Token NAME382 = null;
        Token char_literal383 = null;

        Object char_literal380_tree = null;
        Object string_literal381_tree = null;
        Object NAME382_tree = null;
        Object char_literal383_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_131 = new RewriteRuleTokenStream(adaptor, "token 131");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            // Pddl.g:467:5: ( '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) )
            // Pddl.g:467:7: '(' 'problem' NAME ')'
            {
                char_literal380 = (Token) match(input, 67, FOLLOW_67_in_problemDecl3000);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal380);
                }

                string_literal381 = (Token) match(input, 131, FOLLOW_131_in_problemDecl3002);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_131.add(string_literal381);
                }

                NAME382 = (Token) match(input, NAME, FOLLOW_NAME_in_problemDecl3004);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME382);
                }

                char_literal383 = (Token) match(input, 68, FOLLOW_68_in_problemDecl3006);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal383);
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
                    // 468:5: -> ^( PROBLEM_NAME NAME )
                    {
                        // Pddl.g:468:8: ^( PROBLEM_NAME NAME )
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
        }
        return retval;
    }

    // $ANTLR start "problemDomain"
    // Pddl.g:471:1: problemDomain : '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) ;
    public final ModifiedPddlParser.problemDomain_return problemDomain ( ) throws RecognitionException {
        ModifiedPddlParser.problemDomain_return retval = new ModifiedPddlParser.problemDomain_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal384 = null;
        Token string_literal385 = null;
        Token NAME386 = null;
        Token char_literal387 = null;

        Object char_literal384_tree = null;
        Object string_literal385_tree = null;
        Object NAME386_tree = null;
        Object char_literal387_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_79 = new RewriteRuleTokenStream(adaptor, "token 79");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");

        try {
            // Pddl.g:472:2: ( '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) )
            // Pddl.g:472:4: '(' ':domain' NAME ')'
            {
                char_literal384 = (Token) match(input, 67, FOLLOW_67_in_problemDomain3032);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal384);
                }

                string_literal385 = (Token) match(input, 79, FOLLOW_79_in_problemDomain3034);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_79.add(string_literal385);
                }

                NAME386 = (Token) match(input, NAME, FOLLOW_NAME_in_problemDomain3036);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_NAME.add(NAME386);
                }

                char_literal387 = (Token) match(input, 68, FOLLOW_68_in_problemDomain3038);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal387);
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
                    // 473:2: -> ^( PROBLEM_DOMAIN NAME )
                    {
                        // Pddl.g:473:5: ^( PROBLEM_DOMAIN NAME )
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
        }
        return retval;
    }

    // $ANTLR start "objectDecl"
    // Pddl.g:476:1: objectDecl : '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) ;
    public final ModifiedPddlParser.objectDecl_return objectDecl ( ) throws RecognitionException {
        ModifiedPddlParser.objectDecl_return retval = new ModifiedPddlParser.objectDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal388 = null;
        Token string_literal389 = null;
        Token char_literal391 = null;
        ParserRuleReturnScope typedNameList390 = null;

        Object char_literal388_tree = null;
        Object string_literal389_tree = null;
        Object char_literal391_tree = null;
        RewriteRuleTokenStream stream_89 = new RewriteRuleTokenStream(adaptor, "token 89");
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_typedNameList = new RewriteRuleSubtreeStream(adaptor, "rule typedNameList");

        try {
            // Pddl.g:477:2: ( '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) )
            // Pddl.g:477:4: '(' ':objects' typedNameList ')'
            {
                char_literal388 = (Token) match(input, 67, FOLLOW_67_in_objectDecl3058);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal388);
                }

                string_literal389 = (Token) match(input, 89, FOLLOW_89_in_objectDecl3060);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_89.add(string_literal389);
                }

                pushFollow(FOLLOW_typedNameList_in_objectDecl3062);
                typedNameList390 = typedNameList();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_typedNameList.add(typedNameList390.getTree());
                }
                char_literal391 = (Token) match(input, 68, FOLLOW_68_in_objectDecl3064);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal391);
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
                    // 478:2: -> ^( OBJECTS typedNameList )
                    {
                        // Pddl.g:478:5: ^( OBJECTS typedNameList )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OBJECTS, "OBJECTS"), root_1);
                            if (stream_typedNameList.hasNext()) {//This is a hack to handle empty lists. Something could be probably done in the parser
                                adaptor.addChild(root_1, stream_typedNameList.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }
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
        }
        return retval;
    }

    // $ANTLR start "init"
    // Pddl.g:481:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) );
    public final ModifiedPddlParser.init_return init ( ) throws RecognitionException {
        ModifiedPddlParser.init_return retval = new ModifiedPddlParser.init_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal392 = null;
        Token string_literal393 = null;
        Token char_literal395 = null;
        Token char_literal396 = null;
        Token string_literal397 = null;
        Token char_literal399 = null;
        ParserRuleReturnScope initEl394 = null;
        ParserRuleReturnScope goalDesc398 = null;

        Object char_literal392_tree = null;
        Object string_literal393_tree = null;
        Object char_literal395_tree = null;
        Object char_literal396_tree = null;
        Object string_literal397_tree = null;
        Object char_literal399_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_87 = new RewriteRuleTokenStream(adaptor, "token 87");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");
        RewriteRuleSubtreeStream stream_initEl = new RewriteRuleSubtreeStream(adaptor, "rule initEl");

        try {
            // Pddl.g:482:2: ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) )
            int alt70 = 2;
            alt70 = dfa70.predict(input);
            switch (alt70) {
                case 1: // Pddl.g:482:4: '(' ':init' ( initEl )* ')'
                {
                    char_literal392 = (Token) match(input, 67, FOLLOW_67_in_init3084);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal392);
                    }

                    string_literal393 = (Token) match(input, 87, FOLLOW_87_in_init3086);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_87.add(string_literal393);
                    }

                    // Pddl.g:482:16: ( initEl )*
                    loop69:
                    while (true) {
                        int alt69 = 2;
                        int LA69_0 = input.LA(1);
                        if ((LA69_0 == 67)) {
                            alt69 = 1;
                        }

                        switch (alt69) {
                            case 1: // Pddl.g:482:16: initEl
                            {
                                pushFollow(FOLLOW_initEl_in_init3088);
                                initEl394 = initEl();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_initEl.add(initEl394.getTree());
                                }
                            }
                            break;

                            default:
                                break loop69;
                        }
                    }

                    char_literal395 = (Token) match(input, 68, FOLLOW_68_in_init3091);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal395);
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
                        // 483:2: -> ^( INIT ( initEl )* )
                        {
                            // Pddl.g:483:5: ^( INIT ( initEl )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INIT, "INIT"), root_1);
                                // Pddl.g:483:12: ( initEl )*
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
                case 2: // Pddl.g:484:4: '(' ':init' goalDesc ')'
                {
                    char_literal396 = (Token) match(input, 67, FOLLOW_67_in_init3106);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal396);
                    }

                    string_literal397 = (Token) match(input, 87, FOLLOW_87_in_init3108);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_87.add(string_literal397);
                    }

                    pushFollow(FOLLOW_goalDesc_in_init3110);
                    goalDesc398 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_goalDesc.add(goalDesc398.getTree());
                    }
                    char_literal399 = (Token) match(input, 68, FOLLOW_68_in_init3112);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal399);
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
                        // 484:29: -> ^( FORMULAINIT goalDesc )
                        {
                            // Pddl.g:484:32: ^( FORMULAINIT goalDesc )
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
        }
        return retval;
    }

    // $ANTLR start "initEl"
    // Pddl.g:487:1: initEl : ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) );
    public final ModifiedPddlParser.initEl_return initEl ( ) throws RecognitionException {
        ModifiedPddlParser.initEl_return retval = new ModifiedPddlParser.initEl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal401 = null;
        Token char_literal402 = null;
        Token NUMBER404 = null;
        Token char_literal405 = null;
        Token char_literal406 = null;
        Token string_literal407 = null;
        Token NUMBER408 = null;
        Token char_literal410 = null;
        Token char_literal411 = null;
        Token string_literal412 = null;
        Token char_literal414 = null;
        Token char_literal415 = null;
        Token string_literal416 = null;
        Token char_literal418 = null;
        Token char_literal419 = null;
        Token string_literal420 = null;
        Token char_literal422 = null;
        ParserRuleReturnScope nameLiteral400 = null;
        ParserRuleReturnScope fHead403 = null;
        ParserRuleReturnScope nameLiteral409 = null;
        ParserRuleReturnScope atomicNameFormula413 = null;
        ParserRuleReturnScope atomicNameFormula417 = null;
        ParserRuleReturnScope nameLiteral421 = null;

        Object char_literal401_tree = null;
        Object char_literal402_tree = null;
        Object NUMBER404_tree = null;
        Object char_literal405_tree = null;
        Object char_literal406_tree = null;
        Object string_literal407_tree = null;
        Object NUMBER408_tree = null;
        Object char_literal410_tree = null;
        Object char_literal411_tree = null;
        Object string_literal412_tree = null;
        Object char_literal414_tree = null;
        Object char_literal415_tree = null;
        Object string_literal416_tree = null;
        Object char_literal418_tree = null;
        Object char_literal419_tree = null;
        Object string_literal420_tree = null;
        Object char_literal422_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_NUMBER = new RewriteRuleTokenStream(adaptor, "token NUMBER");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_127 = new RewriteRuleTokenStream(adaptor, "token 127");
        RewriteRuleTokenStream stream_139 = new RewriteRuleTokenStream(adaptor, "token 139");
        RewriteRuleTokenStream stream_128 = new RewriteRuleTokenStream(adaptor, "token 128");
        RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(adaptor, "token 108");
        RewriteRuleTokenStream stream_98 = new RewriteRuleTokenStream(adaptor, "token 98");
        RewriteRuleSubtreeStream stream_fHead = new RewriteRuleSubtreeStream(adaptor, "rule fHead");
        RewriteRuleSubtreeStream stream_atomicNameFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicNameFormula");
        RewriteRuleSubtreeStream stream_nameLiteral = new RewriteRuleSubtreeStream(adaptor, "rule nameLiteral");

        try {
            // Pddl.g:488:2: ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) )
            int alt73 = 6;
            int LA73_0 = input.LA(1);
            if ((LA73_0 == 67)) {
                switch (input.LA(2)) {
                    case NAME:
                    case 125: {
                        alt73 = 1;
                    }
                    break;
                    case 98: {
                        alt73 = 2;
                    }
                    break;
                    case 108: {
                        alt73 = 3;
                    }
                    break;
                    case 139: {
                        alt73 = 4;
                    }
                    break;
                    case 127: {
                        alt73 = 5;
                    }
                    break;
                    case 128: {
                        alt73 = 6;
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
                                    = new NoViableAltException("", 73, 1, input);
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
                        = new NoViableAltException("", 73, 0, input);
                throw nvae;
            }

            switch (alt73) {
                case 1: // Pddl.g:488:4: nameLiteral
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_nameLiteral_in_initEl3131);
                    nameLiteral400 = nameLiteral();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, nameLiteral400.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:489:4: '(' '=' fHead NUMBER ')'
                {
                    char_literal401 = (Token) match(input, 67, FOLLOW_67_in_initEl3136);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal401);
                    }

                    char_literal402 = (Token) match(input, 98, FOLLOW_98_in_initEl3138);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_98.add(char_literal402);
                    }

                    pushFollow(FOLLOW_fHead_in_initEl3140);
                    fHead403 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_fHead.add(fHead403.getTree());
                    }
                    NUMBER404 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_initEl3142);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_NUMBER.add(NUMBER404);
                    }

                    char_literal405 = (Token) match(input, 68, FOLLOW_68_in_initEl3144);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal405);
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
                        // 489:37: -> ^( INIT_EQ fHead NUMBER )
                        {
                            // Pddl.g:489:40: ^( INIT_EQ fHead NUMBER )
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
                case 3: // Pddl.g:490:4: '(' 'at' NUMBER nameLiteral ')'
                {
                    char_literal406 = (Token) match(input, 67, FOLLOW_67_in_initEl3167);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal406);
                    }

                    string_literal407 = (Token) match(input, 108, FOLLOW_108_in_initEl3169);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_108.add(string_literal407);
                    }

                    NUMBER408 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_initEl3171);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_NUMBER.add(NUMBER408);
                    }

                    pushFollow(FOLLOW_nameLiteral_in_initEl3173);
                    nameLiteral409 = nameLiteral();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_nameLiteral.add(nameLiteral409.getTree());
                    }
                    char_literal410 = (Token) match(input, 68, FOLLOW_68_in_initEl3175);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal410);
                    }

                    // AST REWRITE
                    // elements: NUMBER, nameLiteral
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 490:37: -> ^( INIT_AT NUMBER nameLiteral )
                        {
                            // Pddl.g:490:40: ^( INIT_AT NUMBER nameLiteral )
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
                case 4: // Pddl.g:491:4: '(' 'unknown' atomicNameFormula ')'
                {
                    char_literal411 = (Token) match(input, 67, FOLLOW_67_in_initEl3191);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal411);
                    }

                    string_literal412 = (Token) match(input, 139, FOLLOW_139_in_initEl3193);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_139.add(string_literal412);
                    }

                    pushFollow(FOLLOW_atomicNameFormula_in_initEl3196);
                    atomicNameFormula413 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicNameFormula.add(atomicNameFormula413.getTree());
                    }
                    char_literal414 = (Token) match(input, 68, FOLLOW_68_in_initEl3198);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal414);
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
                        // 491:42: -> ^( UNKNOWN atomicNameFormula )
                        {
                            // Pddl.g:491:45: ^( UNKNOWN atomicNameFormula )
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
                case 5: // Pddl.g:492:4: '(' 'oneof' ( atomicNameFormula )* ')'
                {
                    char_literal415 = (Token) match(input, 67, FOLLOW_67_in_initEl3212);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal415);
                    }

                    string_literal416 = (Token) match(input, 127, FOLLOW_127_in_initEl3214);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_127.add(string_literal416);
                    }

                    // Pddl.g:492:17: ( atomicNameFormula )*
                    loop71:
                    while (true) {
                        int alt71 = 2;
                        int LA71_0 = input.LA(1);
                        if ((LA71_0 == 67)) {
                            alt71 = 1;
                        }

                        switch (alt71) {
                            case 1: // Pddl.g:492:17: atomicNameFormula
                            {
                                pushFollow(FOLLOW_atomicNameFormula_in_initEl3217);
                                atomicNameFormula417 = atomicNameFormula();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_atomicNameFormula.add(atomicNameFormula417.getTree());
                                }
                            }
                            break;

                            default:
                                break loop71;
                        }
                    }

                    char_literal418 = (Token) match(input, 68, FOLLOW_68_in_initEl3220);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal418);
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
                        // 492:41: -> ^( ONEOF ( atomicNameFormula )* )
                        {
                            // Pddl.g:492:44: ^( ONEOF ( atomicNameFormula )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ONEOF, "ONEOF"), root_1);
                                // Pddl.g:492:52: ( atomicNameFormula )*
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
                case 6: // Pddl.g:493:4: '(' 'or' ( nameLiteral )* ')'
                {
                    char_literal419 = (Token) match(input, 67, FOLLOW_67_in_initEl3235);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal419);
                    }

                    string_literal420 = (Token) match(input, 128, FOLLOW_128_in_initEl3237);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_128.add(string_literal420);
                    }

                    // Pddl.g:493:14: ( nameLiteral )*
                    loop72:
                    while (true) {
                        int alt72 = 2;
                        int LA72_0 = input.LA(1);
                        if ((LA72_0 == 67)) {
                            alt72 = 1;
                        }

                        switch (alt72) {
                            case 1: // Pddl.g:493:14: nameLiteral
                            {
                                pushFollow(FOLLOW_nameLiteral_in_initEl3240);
                                nameLiteral421 = nameLiteral();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_nameLiteral.add(nameLiteral421.getTree());
                                }
                            }
                            break;

                            default:
                                break loop72;
                        }
                    }

                    char_literal422 = (Token) match(input, 68, FOLLOW_68_in_initEl3243);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal422);
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
                        // 493:32: -> ^( OR_GD ( nameLiteral )* )
                        {
                            // Pddl.g:493:35: ^( OR_GD ( nameLiteral )* )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OR_GD, "OR_GD"), root_1);
                                // Pddl.g:493:43: ( nameLiteral )*
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
        }
        return retval;
    }

    // $ANTLR start "nameLiteral"
    // Pddl.g:496:1: nameLiteral : ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) );
    public final ModifiedPddlParser.nameLiteral_return nameLiteral ( ) throws RecognitionException {
        ModifiedPddlParser.nameLiteral_return retval = new ModifiedPddlParser.nameLiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal424 = null;
        Token string_literal425 = null;
        Token char_literal427 = null;
        ParserRuleReturnScope atomicNameFormula423 = null;
        ParserRuleReturnScope atomicNameFormula426 = null;

        Object char_literal424_tree = null;
        Object string_literal425_tree = null;
        Object char_literal427_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_125 = new RewriteRuleTokenStream(adaptor, "token 125");
        RewriteRuleSubtreeStream stream_atomicNameFormula = new RewriteRuleSubtreeStream(adaptor, "rule atomicNameFormula");

        try {
            // Pddl.g:497:2: ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) )
            int alt74 = 2;
            int LA74_0 = input.LA(1);
            if ((LA74_0 == 67)) {
                int LA74_1 = input.LA(2);
                if ((LA74_1 == 125)) {
                    alt74 = 2;
                } else if ((LA74_1 == NAME)) {
                    alt74 = 1;
                } else {
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
                case 1: // Pddl.g:497:4: atomicNameFormula
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3264);
                    atomicNameFormula423 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, atomicNameFormula423.getTree());
                    }

                }
                break;
                case 2: // Pddl.g:498:4: '(' 'not' atomicNameFormula ')'
                {
                    char_literal424 = (Token) match(input, 67, FOLLOW_67_in_nameLiteral3269);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal424);
                    }

                    string_literal425 = (Token) match(input, 125, FOLLOW_125_in_nameLiteral3271);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_125.add(string_literal425);
                    }

                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3273);
                    atomicNameFormula426 = atomicNameFormula();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_atomicNameFormula.add(atomicNameFormula426.getTree());
                    }
                    char_literal427 = (Token) match(input, 68, FOLLOW_68_in_nameLiteral3275);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal427);
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
                        // 498:36: -> ^( NOT_PRED_INIT atomicNameFormula )
                        {
                            // Pddl.g:498:39: ^( NOT_PRED_INIT atomicNameFormula )
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
        }
        return retval;
    }

    // $ANTLR start "atomicNameFormula"
    // Pddl.g:501:1: atomicNameFormula : '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) ;
    public final ModifiedPddlParser.atomicNameFormula_return atomicNameFormula ( ) throws RecognitionException {
        ModifiedPddlParser.atomicNameFormula_return retval = new ModifiedPddlParser.atomicNameFormula_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal428 = null;
        Token NAME430 = null;
        Token char_literal431 = null;
        ParserRuleReturnScope predicate429 = null;

        Object char_literal428_tree = null;
        Object NAME430_tree = null;
        Object char_literal431_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_NAME = new RewriteRuleTokenStream(adaptor, "token NAME");
        RewriteRuleSubtreeStream stream_predicate = new RewriteRuleSubtreeStream(adaptor, "rule predicate");

        try {
            // Pddl.g:502:2: ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) )
            // Pddl.g:502:4: '(' predicate ( NAME )* ')'
            {
                char_literal428 = (Token) match(input, 67, FOLLOW_67_in_atomicNameFormula3294);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal428);
                }

                pushFollow(FOLLOW_predicate_in_atomicNameFormula3296);
                predicate429 = predicate();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_predicate.add(predicate429.getTree());
                }
                // Pddl.g:502:18: ( NAME )*
                loop75:
                while (true) {
                    int alt75 = 2;
                    int LA75_0 = input.LA(1);
                    if ((LA75_0 == NAME)) {
                        alt75 = 1;
                    }

                    switch (alt75) {
                        case 1: // Pddl.g:502:18: NAME
                        {
                            NAME430 = (Token) match(input, NAME, FOLLOW_NAME_in_atomicNameFormula3298);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                stream_NAME.add(NAME430);
                            }

                        }
                        break;

                        default:
                            break loop75;
                    }
                }

                char_literal431 = (Token) match(input, 68, FOLLOW_68_in_atomicNameFormula3301);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal431);
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
                    // 502:28: -> ^( PRED_INST predicate ( NAME )* )
                    {
                        // Pddl.g:502:31: ^( PRED_INST predicate ( NAME )* )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(PRED_INST, "PRED_INST"), root_1);
                            adaptor.addChild(root_1, stream_predicate.nextTree());
                            // Pddl.g:502:53: ( NAME )*
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
        }
        return retval;
    }

    // $ANTLR start "goal"
    // Pddl.g:509:1: goal : '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) ;
    public final ModifiedPddlParser.goal_return goal ( ) throws RecognitionException {
        ModifiedPddlParser.goal_return retval = new ModifiedPddlParser.goal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal432 = null;
        Token string_literal433 = null;
        Token char_literal435 = null;
        ParserRuleReturnScope goalDesc434 = null;

        Object char_literal432_tree = null;
        Object string_literal433_tree = null;
        Object char_literal435_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_86 = new RewriteRuleTokenStream(adaptor, "token 86");
        RewriteRuleSubtreeStream stream_goalDesc = new RewriteRuleSubtreeStream(adaptor, "rule goalDesc");

        try {
            // Pddl.g:509:6: ( '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) )
            // Pddl.g:509:8: '(' ':goal' goalDesc ')'
            {
                char_literal432 = (Token) match(input, 67, FOLLOW_67_in_goal3326);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal432);
                }

                string_literal433 = (Token) match(input, 86, FOLLOW_86_in_goal3328);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_86.add(string_literal433);
                }

                pushFollow(FOLLOW_goalDesc_in_goal3330);
                goalDesc434 = goalDesc();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_goalDesc.add(goalDesc434.getTree());
                }
                char_literal435 = (Token) match(input, 68, FOLLOW_68_in_goal3333);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal435);
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
                    // 509:34: -> ^( GOAL goalDesc )
                    {
                        // Pddl.g:509:37: ^( GOAL goalDesc )
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
        }
        return retval;
    }

    // $ANTLR start "probConstraints"
    // Pddl.g:511:1: probConstraints : '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) ;
    public final ModifiedPddlParser.probConstraints_return probConstraints ( ) throws RecognitionException {
        ModifiedPddlParser.probConstraints_return retval = new ModifiedPddlParser.probConstraints_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal436 = null;
        Token string_literal437 = null;
        Token char_literal439 = null;
        ParserRuleReturnScope prefConGD438 = null;

        Object char_literal436_tree = null;
        Object string_literal437_tree = null;
        Object char_literal439_tree = null;
        RewriteRuleTokenStream stream_77 = new RewriteRuleTokenStream(adaptor, "token 77");
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_prefConGD = new RewriteRuleSubtreeStream(adaptor, "rule prefConGD");

        try {
            // Pddl.g:512:2: ( '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) )
            // Pddl.g:512:4: '(' ':constraints' prefConGD ')'
            {
                char_literal436 = (Token) match(input, 67, FOLLOW_67_in_probConstraints3351);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal436);
                }

                string_literal437 = (Token) match(input, 77, FOLLOW_77_in_probConstraints3353);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_77.add(string_literal437);
                }

                pushFollow(FOLLOW_prefConGD_in_probConstraints3356);
                prefConGD438 = prefConGD();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_prefConGD.add(prefConGD438.getTree());
                }
                char_literal439 = (Token) match(input, 68, FOLLOW_68_in_probConstraints3358);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal439);
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
                    // 513:4: -> ^( PROBLEM_CONSTRAINT prefConGD )
                    {
                        // Pddl.g:513:7: ^( PROBLEM_CONSTRAINT prefConGD )
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
        }
        return retval;
    }

    // $ANTLR start "prefConGD"
    // Pddl.g:516:1: prefConGD : ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD );
    public final ModifiedPddlParser.prefConGD_return prefConGD ( ) throws RecognitionException {
        ModifiedPddlParser.prefConGD_return retval = new ModifiedPddlParser.prefConGD_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal440 = null;
        Token string_literal441 = null;
        Token char_literal443 = null;
        Token char_literal444 = null;
        Token string_literal445 = null;
        Token char_literal446 = null;
        Token char_literal448 = null;
        Token char_literal450 = null;
        Token char_literal451 = null;
        Token string_literal452 = null;
        Token NAME453 = null;
        Token char_literal455 = null;
        ParserRuleReturnScope prefConGD442 = null;
        ParserRuleReturnScope typedVariableList447 = null;
        ParserRuleReturnScope prefConGD449 = null;
        ParserRuleReturnScope conGD454 = null;
        ParserRuleReturnScope conGD456 = null;

        Object char_literal440_tree = null;
        Object string_literal441_tree = null;
        Object char_literal443_tree = null;
        Object char_literal444_tree = null;
        Object string_literal445_tree = null;
        Object char_literal446_tree = null;
        Object char_literal448_tree = null;
        Object char_literal450_tree = null;
        Object char_literal451_tree = null;
        Object string_literal452_tree = null;
        Object NAME453_tree = null;
        Object char_literal455_tree = null;

        try {
            // Pddl.g:517:2: ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD )
            int alt78 = 4;
            int LA78_0 = input.LA(1);
            if ((LA78_0 == 67)) {
                int LA78_1 = input.LA(2);
                if ((synpred124_Pddl())) {
                    alt78 = 1;
                } else if ((synpred125_Pddl())) {
                    alt78 = 2;
                } else if ((synpred127_Pddl())) {
                    alt78 = 3;
                } else if ((true)) {
                    alt78 = 4;
                }

            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 78, 0, input);
                throw nvae;
            }

            switch (alt78) {
                case 1: // Pddl.g:517:4: '(' 'and' ( prefConGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal440 = (Token) match(input, 67, FOLLOW_67_in_prefConGD3380);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal440_tree = (Object) adaptor.create(char_literal440);
                        adaptor.addChild(root_0, char_literal440_tree);
                    }

                    string_literal441 = (Token) match(input, 106, FOLLOW_106_in_prefConGD3382);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal441_tree = (Object) adaptor.create(string_literal441);
                        adaptor.addChild(root_0, string_literal441_tree);
                    }

                    // Pddl.g:517:14: ( prefConGD )*
                    loop76:
                    while (true) {
                        int alt76 = 2;
                        int LA76_0 = input.LA(1);
                        if ((LA76_0 == 67)) {
                            alt76 = 1;
                        }

                        switch (alt76) {
                            case 1: // Pddl.g:517:14: prefConGD
                            {
                                pushFollow(FOLLOW_prefConGD_in_prefConGD3384);
                                prefConGD442 = prefConGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, prefConGD442.getTree());
                                }

                            }
                            break;

                            default:
                                break loop76;
                        }
                    }

                    char_literal443 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3387);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal443_tree = (Object) adaptor.create(char_literal443);
                        adaptor.addChild(root_0, char_literal443_tree);
                    }

                }
                break;
                case 2: // Pddl.g:518:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal444 = (Token) match(input, 67, FOLLOW_67_in_prefConGD3392);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal444_tree = (Object) adaptor.create(char_literal444);
                        adaptor.addChild(root_0, char_literal444_tree);
                    }

                    string_literal445 = (Token) match(input, 117, FOLLOW_117_in_prefConGD3394);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal445_tree = (Object) adaptor.create(string_literal445);
                        adaptor.addChild(root_0, string_literal445_tree);
                    }

                    char_literal446 = (Token) match(input, 67, FOLLOW_67_in_prefConGD3396);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal446_tree = (Object) adaptor.create(char_literal446);
                        adaptor.addChild(root_0, char_literal446_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_prefConGD3398);
                    typedVariableList447 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList447.getTree());
                    }

                    char_literal448 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3400);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal448_tree = (Object) adaptor.create(char_literal448);
                        adaptor.addChild(root_0, char_literal448_tree);
                    }

                    pushFollow(FOLLOW_prefConGD_in_prefConGD3402);
                    prefConGD449 = prefConGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, prefConGD449.getTree());
                    }

                    char_literal450 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3404);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal450_tree = (Object) adaptor.create(char_literal450);
                        adaptor.addChild(root_0, char_literal450_tree);
                    }

                }
                break;
                case 3: // Pddl.g:519:4: '(' 'preference' ( NAME )? conGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal451 = (Token) match(input, 67, FOLLOW_67_in_prefConGD3409);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal451_tree = (Object) adaptor.create(char_literal451);
                        adaptor.addChild(root_0, char_literal451_tree);
                    }

                    string_literal452 = (Token) match(input, 130, FOLLOW_130_in_prefConGD3411);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal452_tree = (Object) adaptor.create(string_literal452);
                        adaptor.addChild(root_0, string_literal452_tree);
                    }

                    // Pddl.g:519:21: ( NAME )?
                    int alt77 = 2;
                    int LA77_0 = input.LA(1);
                    if ((LA77_0 == NAME)) {
                        alt77 = 1;
                    }
                    switch (alt77) {
                        case 1: // Pddl.g:519:21: NAME
                        {
                            NAME453 = (Token) match(input, NAME, FOLLOW_NAME_in_prefConGD3413);
                            if (state.failed) {
                                return retval;
                            }
                            if (state.backtracking == 0) {
                                NAME453_tree = (Object) adaptor.create(NAME453);
                                adaptor.addChild(root_0, NAME453_tree);
                            }

                        }
                        break;

                    }

                    pushFollow(FOLLOW_conGD_in_prefConGD3416);
                    conGD454 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD454.getTree());
                    }

                    char_literal455 = (Token) match(input, 68, FOLLOW_68_in_prefConGD3418);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal455_tree = (Object) adaptor.create(char_literal455);
                        adaptor.addChild(root_0, char_literal455_tree);
                    }

                }
                break;
                case 4: // Pddl.g:520:4: conGD
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_conGD_in_prefConGD3423);
                    conGD456 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD456.getTree());
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
        }
        return retval;
    }

    // $ANTLR start "metricSpec"
    // Pddl.g:523:1: metricSpec : '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) ;
    public final ModifiedPddlParser.metricSpec_return metricSpec ( ) throws RecognitionException {
        ModifiedPddlParser.metricSpec_return retval = new ModifiedPddlParser.metricSpec_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal457 = null;
        Token string_literal458 = null;
        Token char_literal461 = null;
        ParserRuleReturnScope optimization459 = null;
        ParserRuleReturnScope metricFExp460 = null;

        Object char_literal457_tree = null;
        Object string_literal458_tree = null;
        Object char_literal461_tree = null;
        RewriteRuleTokenStream stream_88 = new RewriteRuleTokenStream(adaptor, "token 88");
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleSubtreeStream stream_metricFExp = new RewriteRuleSubtreeStream(adaptor, "rule metricFExp");
        RewriteRuleSubtreeStream stream_optimization = new RewriteRuleSubtreeStream(adaptor, "rule optimization");

        try {
            // Pddl.g:524:2: ( '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) )
            // Pddl.g:524:4: '(' ':metric' optimization metricFExp ')'
            {
                char_literal457 = (Token) match(input, 67, FOLLOW_67_in_metricSpec3434);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_67.add(char_literal457);
                }

                string_literal458 = (Token) match(input, 88, FOLLOW_88_in_metricSpec3436);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_88.add(string_literal458);
                }

                pushFollow(FOLLOW_optimization_in_metricSpec3438);
                optimization459 = optimization();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_optimization.add(optimization459.getTree());
                }
                pushFollow(FOLLOW_metricFExp_in_metricSpec3440);
                metricFExp460 = metricFExp();
                state._fsp--;
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_metricFExp.add(metricFExp460.getTree());
                }
                char_literal461 = (Token) match(input, 68, FOLLOW_68_in_metricSpec3442);
                if (state.failed) {
                    return retval;
                }
                if (state.backtracking == 0) {
                    stream_68.add(char_literal461);
                }

                // AST REWRITE
                // elements: metricFExp, optimization
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 525:4: -> ^( PROBLEM_METRIC optimization metricFExp )
                    {
                        // Pddl.g:525:7: ^( PROBLEM_METRIC optimization metricFExp )
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
        }
        return retval;
    }

    // $ANTLR start "optimization"
    // Pddl.g:528:1: optimization : ( 'minimize' | 'maximize' );
    public final ModifiedPddlParser.optimization_return optimization ( ) throws RecognitionException {
        ModifiedPddlParser.optimization_return retval = new ModifiedPddlParser.optimization_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set462 = null;

        Object set462_tree = null;

        try {
            // Pddl.g:528:14: ( 'minimize' | 'maximize' )
            // Pddl.g:
            {
                root_0 = (Object) adaptor.nil();

                set462 = input.LT(1);
                if ((input.LA(1) >= 123 && input.LA(1) <= 124)) {
                    input.consume();
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, (Object) adaptor.create(set462));
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
        }
        return retval;
    }

    // $ANTLR start "metricFExp"
    // Pddl.g:530:1: metricFExp : ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' );
    public final ModifiedPddlParser.metricFExp_return metricFExp ( ) throws RecognitionException {
        ModifiedPddlParser.metricFExp_return retval = new ModifiedPddlParser.metricFExp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal463 = null;
        Token char_literal467 = null;
        Token char_literal468 = null;
        Token char_literal472 = null;
        Token char_literal473 = null;
        Token char_literal474 = null;
        Token char_literal476 = null;
        Token NUMBER477 = null;
        Token char_literal479 = null;
        Token string_literal480 = null;
        Token NAME481 = null;
        Token char_literal482 = null;
        ParserRuleReturnScope binaryOp464 = null;
        ParserRuleReturnScope metricFExp465 = null;
        ParserRuleReturnScope metricFExp466 = null;
        ParserRuleReturnScope multiOp469 = null;
        ParserRuleReturnScope metricFExp470 = null;
        ParserRuleReturnScope metricFExp471 = null;
        ParserRuleReturnScope metricFExp475 = null;
        ParserRuleReturnScope fHead478 = null;

        Object char_literal463_tree = null;
        Object char_literal467_tree = null;
        Object char_literal468_tree = null;
        Object char_literal472_tree = null;
        Object char_literal473_tree = null;
        Object char_literal474_tree = null;
        Object char_literal476_tree = null;
        Object NUMBER477_tree = null;
        Object char_literal479_tree = null;
        Object string_literal480_tree = null;
        Object NAME481_tree = null;
        Object char_literal482_tree = null;
        RewriteRuleTokenStream stream_67 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream stream_68 = new RewriteRuleTokenStream(adaptor, "token 68");
        RewriteRuleTokenStream stream_71 = new RewriteRuleTokenStream(adaptor, "token 71");
        RewriteRuleSubtreeStream stream_binaryOp = new RewriteRuleSubtreeStream(adaptor, "rule binaryOp");
        RewriteRuleSubtreeStream stream_metricFExp = new RewriteRuleSubtreeStream(adaptor, "rule metricFExp");
        RewriteRuleSubtreeStream stream_multiOp = new RewriteRuleSubtreeStream(adaptor, "rule multiOp");

        try {
            // Pddl.g:531:2: ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' )
            int alt80 = 6;
            switch (input.LA(1)) {
                case 67: {
                    int LA80_1 = input.LA(2);
                    if ((synpred129_Pddl())) {
                        alt80 = 1;
                    } else if ((synpred131_Pddl())) {
                        alt80 = 2;
                    } else if ((synpred132_Pddl())) {
                        alt80 = 3;
                    } else if ((synpred134_Pddl())) {
                        alt80 = 5;
                    } else if ((true)) {
                        alt80 = 6;
                    }

                }
                break;
                case NUMBER: {
                    alt80 = 4;
                }
                break;
                case NAME:
                case 66: {
                    alt80 = 5;
                }
                break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae
                            = new NoViableAltException("", 80, 0, input);
                    throw nvae;
            }
            switch (alt80) {
                case 1: // Pddl.g:531:4: '(' binaryOp metricFExp metricFExp ')'
                {
                    char_literal463 = (Token) match(input, 67, FOLLOW_67_in_metricFExp3479);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal463);
                    }

                    pushFollow(FOLLOW_binaryOp_in_metricFExp3481);
                    binaryOp464 = binaryOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_binaryOp.add(binaryOp464.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3483);
                    metricFExp465 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp465.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3485);
                    metricFExp466 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp466.getTree());
                    }
                    char_literal467 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3487);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal467);
                    }

                    // AST REWRITE
                    // elements: metricFExp, metricFExp, binaryOp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 532:6: -> ^( BINARY_OP binaryOp metricFExp metricFExp )
                        {
                            // Pddl.g:532:9: ^( BINARY_OP binaryOp metricFExp metricFExp )
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
                case 2: // Pddl.g:533:4: '(' multiOp metricFExp ( metricFExp )+ ')'
                {
                    char_literal468 = (Token) match(input, 67, FOLLOW_67_in_metricFExp3509);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal468);
                    }

                    pushFollow(FOLLOW_multiOp_in_metricFExp3511);
                    multiOp469 = multiOp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_multiOp.add(multiOp469.getTree());
                    }
                    pushFollow(FOLLOW_metricFExp_in_metricFExp3513);
                    metricFExp470 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp470.getTree());
                    }
                    // Pddl.g:533:27: ( metricFExp )+
                    int cnt79 = 0;
                    loop79:
                    while (true) {
                        int alt79 = 2;
                        int LA79_0 = input.LA(1);
                        if ((LA79_0 == NAME || LA79_0 == NUMBER || (LA79_0 >= 66 && LA79_0 <= 67))) {
                            alt79 = 1;
                        }

                        switch (alt79) {
                            case 1: // Pddl.g:533:27: metricFExp
                            {
                                pushFollow(FOLLOW_metricFExp_in_metricFExp3515);
                                metricFExp471 = metricFExp();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    stream_metricFExp.add(metricFExp471.getTree());
                                }
                            }
                            break;

                            default:
                                if (cnt79 >= 1) {
                                    break loop79;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                EarlyExitException eee = new EarlyExitException(79, input);
                                throw eee;
                        }
                        cnt79++;
                    }

                    char_literal472 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3518);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal472);
                    }

                    // AST REWRITE
                    // elements: multiOp, metricFExp, metricFExp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    if (state.backtracking == 0) {
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 534:7: -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
                        {
                            // Pddl.g:534:10: ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
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
                case 3: // Pddl.g:535:4: '(' '-' metricFExp ')'
                {
                    char_literal473 = (Token) match(input, 67, FOLLOW_67_in_metricFExp3542);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_67.add(char_literal473);
                    }

                    char_literal474 = (Token) match(input, 71, FOLLOW_71_in_metricFExp3544);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_71.add(char_literal474);
                    }

                    pushFollow(FOLLOW_metricFExp_in_metricFExp3546);
                    metricFExp475 = metricFExp();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_metricFExp.add(metricFExp475.getTree());
                    }
                    char_literal476 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3548);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        stream_68.add(char_literal476);
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
                        // 536:4: -> ^( MINUS_OP metricFExp )
                        {
                            // Pddl.g:536:7: ^( MINUS_OP metricFExp )
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
                case 4: // Pddl.g:537:4: NUMBER
                {
                    root_0 = (Object) adaptor.nil();

                    NUMBER477 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_metricFExp3565);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER477_tree = (Object) adaptor.create(NUMBER477);
                        adaptor.addChild(root_0, NUMBER477_tree);
                    }

                }
                break;
                case 5: // Pddl.g:538:4: fHead
                {
                    root_0 = (Object) adaptor.nil();

                    pushFollow(FOLLOW_fHead_in_metricFExp3570);
                    fHead478 = fHead();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, fHead478.getTree());
                    }

                }
                break;
                case 6: // Pddl.g:540:4: '(' 'is-violated' NAME ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal479 = (Token) match(input, 67, FOLLOW_67_in_metricFExp3585);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal479_tree = (Object) adaptor.create(char_literal479);
                        adaptor.addChild(root_0, char_literal479_tree);
                    }

                    string_literal480 = (Token) match(input, 122, FOLLOW_122_in_metricFExp3587);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal480_tree = (Object) adaptor.create(string_literal480);
                        adaptor.addChild(root_0, string_literal480_tree);
                    }

                    NAME481 = (Token) match(input, NAME, FOLLOW_NAME_in_metricFExp3589);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NAME481_tree = (Object) adaptor.create(NAME481);
                        adaptor.addChild(root_0, NAME481_tree);
                    }

                    char_literal482 = (Token) match(input, 68, FOLLOW_68_in_metricFExp3591);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal482_tree = (Object) adaptor.create(char_literal482);
                        adaptor.addChild(root_0, char_literal482_tree);
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
        }
        return retval;
    }

    // $ANTLR start "conGD"
    // Pddl.g:545:1: conGD : ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' );
    public final ModifiedPddlParser.conGD_return conGD ( ) throws RecognitionException {
        ModifiedPddlParser.conGD_return retval = new ModifiedPddlParser.conGD_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal483 = null;
        Token string_literal484 = null;
        Token char_literal486 = null;
        Token char_literal487 = null;
        Token string_literal488 = null;
        Token char_literal489 = null;
        Token char_literal491 = null;
        Token char_literal493 = null;
        Token char_literal494 = null;
        Token string_literal495 = null;
        Token string_literal496 = null;
        Token char_literal498 = null;
        Token char_literal499 = null;
        Token string_literal500 = null;
        Token char_literal502 = null;
        Token char_literal503 = null;
        Token string_literal504 = null;
        Token char_literal506 = null;
        Token char_literal507 = null;
        Token string_literal508 = null;
        Token NUMBER509 = null;
        Token char_literal511 = null;
        Token char_literal512 = null;
        Token string_literal513 = null;
        Token char_literal515 = null;
        Token char_literal516 = null;
        Token string_literal517 = null;
        Token char_literal520 = null;
        Token char_literal521 = null;
        Token string_literal522 = null;
        Token char_literal525 = null;
        Token char_literal526 = null;
        Token string_literal527 = null;
        Token NUMBER528 = null;
        Token char_literal531 = null;
        Token char_literal532 = null;
        Token string_literal533 = null;
        Token NUMBER534 = null;
        Token NUMBER535 = null;
        Token char_literal537 = null;
        Token char_literal538 = null;
        Token string_literal539 = null;
        Token NUMBER540 = null;
        Token char_literal542 = null;
        ParserRuleReturnScope conGD485 = null;
        ParserRuleReturnScope typedVariableList490 = null;
        ParserRuleReturnScope conGD492 = null;
        ParserRuleReturnScope goalDesc497 = null;
        ParserRuleReturnScope goalDesc501 = null;
        ParserRuleReturnScope goalDesc505 = null;
        ParserRuleReturnScope goalDesc510 = null;
        ParserRuleReturnScope goalDesc514 = null;
        ParserRuleReturnScope goalDesc518 = null;
        ParserRuleReturnScope goalDesc519 = null;
        ParserRuleReturnScope goalDesc523 = null;
        ParserRuleReturnScope goalDesc524 = null;
        ParserRuleReturnScope goalDesc529 = null;
        ParserRuleReturnScope goalDesc530 = null;
        ParserRuleReturnScope goalDesc536 = null;
        ParserRuleReturnScope goalDesc541 = null;

        Object char_literal483_tree = null;
        Object string_literal484_tree = null;
        Object char_literal486_tree = null;
        Object char_literal487_tree = null;
        Object string_literal488_tree = null;
        Object char_literal489_tree = null;
        Object char_literal491_tree = null;
        Object char_literal493_tree = null;
        Object char_literal494_tree = null;
        Object string_literal495_tree = null;
        Object string_literal496_tree = null;
        Object char_literal498_tree = null;
        Object char_literal499_tree = null;
        Object string_literal500_tree = null;
        Object char_literal502_tree = null;
        Object char_literal503_tree = null;
        Object string_literal504_tree = null;
        Object char_literal506_tree = null;
        Object char_literal507_tree = null;
        Object string_literal508_tree = null;
        Object NUMBER509_tree = null;
        Object char_literal511_tree = null;
        Object char_literal512_tree = null;
        Object string_literal513_tree = null;
        Object char_literal515_tree = null;
        Object char_literal516_tree = null;
        Object string_literal517_tree = null;
        Object char_literal520_tree = null;
        Object char_literal521_tree = null;
        Object string_literal522_tree = null;
        Object char_literal525_tree = null;
        Object char_literal526_tree = null;
        Object string_literal527_tree = null;
        Object NUMBER528_tree = null;
        Object char_literal531_tree = null;
        Object char_literal532_tree = null;
        Object string_literal533_tree = null;
        Object NUMBER534_tree = null;
        Object NUMBER535_tree = null;
        Object char_literal537_tree = null;
        Object char_literal538_tree = null;
        Object string_literal539_tree = null;
        Object NUMBER540_tree = null;
        Object char_literal542_tree = null;

        try {
            // Pddl.g:546:2: ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' )
            int alt82 = 12;
            int LA82_0 = input.LA(1);
            if ((LA82_0 == 67)) {
                switch (input.LA(2)) {
                    case 106: {
                        alt82 = 1;
                    }
                    break;
                    case 117: {
                        alt82 = 2;
                    }
                    break;
                    case 108: {
                        alt82 = 3;
                    }
                    break;
                    case 104: {
                        alt82 = 4;
                    }
                    break;
                    case 135: {
                        alt82 = 5;
                    }
                    break;
                    case 141: {
                        alt82 = 6;
                    }
                    break;
                    case 109: {
                        alt82 = 7;
                    }
                    break;
                    case 136: {
                        alt82 = 8;
                    }
                    break;
                    case 137: {
                        alt82 = 9;
                    }
                    break;
                    case 105: {
                        alt82 = 10;
                    }
                    break;
                    case 119: {
                        alt82 = 11;
                    }
                    break;
                    case 118: {
                        alt82 = 12;
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
                                    = new NoViableAltException("", 82, 1, input);
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
                        = new NoViableAltException("", 82, 0, input);
                throw nvae;
            }

            switch (alt82) {
                case 1: // Pddl.g:546:4: '(' 'and' ( conGD )* ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal483 = (Token) match(input, 67, FOLLOW_67_in_conGD3605);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal483_tree = (Object) adaptor.create(char_literal483);
                        adaptor.addChild(root_0, char_literal483_tree);
                    }

                    string_literal484 = (Token) match(input, 106, FOLLOW_106_in_conGD3607);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal484_tree = (Object) adaptor.create(string_literal484);
                        adaptor.addChild(root_0, string_literal484_tree);
                    }

                    // Pddl.g:546:14: ( conGD )*
                    loop81:
                    while (true) {
                        int alt81 = 2;
                        int LA81_0 = input.LA(1);
                        if ((LA81_0 == 67)) {
                            alt81 = 1;
                        }

                        switch (alt81) {
                            case 1: // Pddl.g:546:14: conGD
                            {
                                pushFollow(FOLLOW_conGD_in_conGD3609);
                                conGD485 = conGD();
                                state._fsp--;
                                if (state.failed) {
                                    return retval;
                                }
                                if (state.backtracking == 0) {
                                    adaptor.addChild(root_0, conGD485.getTree());
                                }

                            }
                            break;

                            default:
                                break loop81;
                        }
                    }

                    char_literal486 = (Token) match(input, 68, FOLLOW_68_in_conGD3612);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal486_tree = (Object) adaptor.create(char_literal486);
                        adaptor.addChild(root_0, char_literal486_tree);
                    }

                }
                break;
                case 2: // Pddl.g:547:4: '(' 'forall' '(' typedVariableList ')' conGD ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal487 = (Token) match(input, 67, FOLLOW_67_in_conGD3617);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal487_tree = (Object) adaptor.create(char_literal487);
                        adaptor.addChild(root_0, char_literal487_tree);
                    }

                    string_literal488 = (Token) match(input, 117, FOLLOW_117_in_conGD3619);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal488_tree = (Object) adaptor.create(string_literal488);
                        adaptor.addChild(root_0, string_literal488_tree);
                    }

                    char_literal489 = (Token) match(input, 67, FOLLOW_67_in_conGD3621);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal489_tree = (Object) adaptor.create(char_literal489);
                        adaptor.addChild(root_0, char_literal489_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_conGD3623);
                    typedVariableList490 = typedVariableList();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, typedVariableList490.getTree());
                    }

                    char_literal491 = (Token) match(input, 68, FOLLOW_68_in_conGD3625);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal491_tree = (Object) adaptor.create(char_literal491);
                        adaptor.addChild(root_0, char_literal491_tree);
                    }

                    pushFollow(FOLLOW_conGD_in_conGD3627);
                    conGD492 = conGD();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, conGD492.getTree());
                    }

                    char_literal493 = (Token) match(input, 68, FOLLOW_68_in_conGD3629);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal493_tree = (Object) adaptor.create(char_literal493);
                        adaptor.addChild(root_0, char_literal493_tree);
                    }

                }
                break;
                case 3: // Pddl.g:548:4: '(' 'at' 'end' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal494 = (Token) match(input, 67, FOLLOW_67_in_conGD3634);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal494_tree = (Object) adaptor.create(char_literal494);
                        adaptor.addChild(root_0, char_literal494_tree);
                    }

                    string_literal495 = (Token) match(input, 108, FOLLOW_108_in_conGD3636);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal495_tree = (Object) adaptor.create(string_literal495);
                        adaptor.addChild(root_0, string_literal495_tree);
                    }

                    string_literal496 = (Token) match(input, 115, FOLLOW_115_in_conGD3638);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal496_tree = (Object) adaptor.create(string_literal496);
                        adaptor.addChild(root_0, string_literal496_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3640);
                    goalDesc497 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc497.getTree());
                    }

                    char_literal498 = (Token) match(input, 68, FOLLOW_68_in_conGD3642);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal498_tree = (Object) adaptor.create(char_literal498);
                        adaptor.addChild(root_0, char_literal498_tree);
                    }

                }
                break;
                case 4: // Pddl.g:549:25: '(' 'always' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal499 = (Token) match(input, 67, FOLLOW_67_in_conGD3668);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal499_tree = (Object) adaptor.create(char_literal499);
                        adaptor.addChild(root_0, char_literal499_tree);
                    }

                    string_literal500 = (Token) match(input, 104, FOLLOW_104_in_conGD3670);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal500_tree = (Object) adaptor.create(string_literal500);
                        adaptor.addChild(root_0, string_literal500_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3672);
                    goalDesc501 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc501.getTree());
                    }

                    char_literal502 = (Token) match(input, 68, FOLLOW_68_in_conGD3674);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal502_tree = (Object) adaptor.create(char_literal502);
                        adaptor.addChild(root_0, char_literal502_tree);
                    }

                }
                break;
                case 5: // Pddl.g:550:4: '(' 'sometime' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal503 = (Token) match(input, 67, FOLLOW_67_in_conGD3679);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal503_tree = (Object) adaptor.create(char_literal503);
                        adaptor.addChild(root_0, char_literal503_tree);
                    }

                    string_literal504 = (Token) match(input, 135, FOLLOW_135_in_conGD3681);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal504_tree = (Object) adaptor.create(string_literal504);
                        adaptor.addChild(root_0, string_literal504_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3683);
                    goalDesc505 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc505.getTree());
                    }

                    char_literal506 = (Token) match(input, 68, FOLLOW_68_in_conGD3685);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal506_tree = (Object) adaptor.create(char_literal506);
                        adaptor.addChild(root_0, char_literal506_tree);
                    }

                }
                break;
                case 6: // Pddl.g:551:5: '(' 'within' NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal507 = (Token) match(input, 67, FOLLOW_67_in_conGD3691);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal507_tree = (Object) adaptor.create(char_literal507);
                        adaptor.addChild(root_0, char_literal507_tree);
                    }

                    string_literal508 = (Token) match(input, 141, FOLLOW_141_in_conGD3693);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal508_tree = (Object) adaptor.create(string_literal508);
                        adaptor.addChild(root_0, string_literal508_tree);
                    }

                    NUMBER509 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3695);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER509_tree = (Object) adaptor.create(NUMBER509);
                        adaptor.addChild(root_0, NUMBER509_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3697);
                    goalDesc510 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc510.getTree());
                    }

                    char_literal511 = (Token) match(input, 68, FOLLOW_68_in_conGD3699);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal511_tree = (Object) adaptor.create(char_literal511);
                        adaptor.addChild(root_0, char_literal511_tree);
                    }

                }
                break;
                case 7: // Pddl.g:552:4: '(' 'at-most-once' goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal512 = (Token) match(input, 67, FOLLOW_67_in_conGD3704);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal512_tree = (Object) adaptor.create(char_literal512);
                        adaptor.addChild(root_0, char_literal512_tree);
                    }

                    string_literal513 = (Token) match(input, 109, FOLLOW_109_in_conGD3706);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal513_tree = (Object) adaptor.create(string_literal513);
                        adaptor.addChild(root_0, string_literal513_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3708);
                    goalDesc514 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc514.getTree());
                    }

                    char_literal515 = (Token) match(input, 68, FOLLOW_68_in_conGD3710);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal515_tree = (Object) adaptor.create(char_literal515);
                        adaptor.addChild(root_0, char_literal515_tree);
                    }

                }
                break;
                case 8: // Pddl.g:553:4: '(' 'sometime-after' goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal516 = (Token) match(input, 67, FOLLOW_67_in_conGD3715);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal516_tree = (Object) adaptor.create(char_literal516);
                        adaptor.addChild(root_0, char_literal516_tree);
                    }

                    string_literal517 = (Token) match(input, 136, FOLLOW_136_in_conGD3717);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal517_tree = (Object) adaptor.create(string_literal517);
                        adaptor.addChild(root_0, string_literal517_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3719);
                    goalDesc518 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc518.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3721);
                    goalDesc519 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc519.getTree());
                    }

                    char_literal520 = (Token) match(input, 68, FOLLOW_68_in_conGD3723);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal520_tree = (Object) adaptor.create(char_literal520);
                        adaptor.addChild(root_0, char_literal520_tree);
                    }

                }
                break;
                case 9: // Pddl.g:554:4: '(' 'sometime-before' goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal521 = (Token) match(input, 67, FOLLOW_67_in_conGD3728);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal521_tree = (Object) adaptor.create(char_literal521);
                        adaptor.addChild(root_0, char_literal521_tree);
                    }

                    string_literal522 = (Token) match(input, 137, FOLLOW_137_in_conGD3730);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal522_tree = (Object) adaptor.create(string_literal522);
                        adaptor.addChild(root_0, string_literal522_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3732);
                    goalDesc523 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc523.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3734);
                    goalDesc524 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc524.getTree());
                    }

                    char_literal525 = (Token) match(input, 68, FOLLOW_68_in_conGD3736);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal525_tree = (Object) adaptor.create(char_literal525);
                        adaptor.addChild(root_0, char_literal525_tree);
                    }

                }
                break;
                case 10: // Pddl.g:555:4: '(' 'always-within' NUMBER goalDesc goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal526 = (Token) match(input, 67, FOLLOW_67_in_conGD3741);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal526_tree = (Object) adaptor.create(char_literal526);
                        adaptor.addChild(root_0, char_literal526_tree);
                    }

                    string_literal527 = (Token) match(input, 105, FOLLOW_105_in_conGD3743);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal527_tree = (Object) adaptor.create(string_literal527);
                        adaptor.addChild(root_0, string_literal527_tree);
                    }

                    NUMBER528 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3745);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER528_tree = (Object) adaptor.create(NUMBER528);
                        adaptor.addChild(root_0, NUMBER528_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3747);
                    goalDesc529 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc529.getTree());
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3749);
                    goalDesc530 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc530.getTree());
                    }

                    char_literal531 = (Token) match(input, 68, FOLLOW_68_in_conGD3751);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal531_tree = (Object) adaptor.create(char_literal531);
                        adaptor.addChild(root_0, char_literal531_tree);
                    }

                }
                break;
                case 11: // Pddl.g:556:4: '(' 'hold-during' NUMBER NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal532 = (Token) match(input, 67, FOLLOW_67_in_conGD3756);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal532_tree = (Object) adaptor.create(char_literal532);
                        adaptor.addChild(root_0, char_literal532_tree);
                    }

                    string_literal533 = (Token) match(input, 119, FOLLOW_119_in_conGD3758);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal533_tree = (Object) adaptor.create(string_literal533);
                        adaptor.addChild(root_0, string_literal533_tree);
                    }

                    NUMBER534 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3760);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER534_tree = (Object) adaptor.create(NUMBER534);
                        adaptor.addChild(root_0, NUMBER534_tree);
                    }

                    NUMBER535 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3762);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER535_tree = (Object) adaptor.create(NUMBER535);
                        adaptor.addChild(root_0, NUMBER535_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3764);
                    goalDesc536 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc536.getTree());
                    }

                    char_literal537 = (Token) match(input, 68, FOLLOW_68_in_conGD3766);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal537_tree = (Object) adaptor.create(char_literal537);
                        adaptor.addChild(root_0, char_literal537_tree);
                    }

                }
                break;
                case 12: // Pddl.g:557:4: '(' 'hold-after' NUMBER goalDesc ')'
                {
                    root_0 = (Object) adaptor.nil();

                    char_literal538 = (Token) match(input, 67, FOLLOW_67_in_conGD3771);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal538_tree = (Object) adaptor.create(char_literal538);
                        adaptor.addChild(root_0, char_literal538_tree);
                    }

                    string_literal539 = (Token) match(input, 118, FOLLOW_118_in_conGD3773);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        string_literal539_tree = (Object) adaptor.create(string_literal539);
                        adaptor.addChild(root_0, string_literal539_tree);
                    }

                    NUMBER540 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_conGD3775);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        NUMBER540_tree = (Object) adaptor.create(NUMBER540);
                        adaptor.addChild(root_0, NUMBER540_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3777);
                    goalDesc541 = goalDesc();
                    state._fsp--;
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        adaptor.addChild(root_0, goalDesc541.getTree());
                    }

                    char_literal542 = (Token) match(input, 68, FOLLOW_68_in_conGD3779);
                    if (state.failed) {
                        return retval;
                    }
                    if (state.backtracking == 0) {
                        char_literal542_tree = (Object) adaptor.create(char_literal542);
                        adaptor.addChild(root_0, char_literal542_tree);
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
        }
        return retval;
    }

    // $ANTLR start synpred18_Pddl
    public final void synpred18_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:166:5: ( atomicFunctionSkeleton )
        // Pddl.g:166:5: atomicFunctionSkeleton
        {
            pushFollow(FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl828);
            atomicFunctionSkeleton();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred48_Pddl
    public final void synpred48_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:290:7: ( fComp )
        // Pddl.g:290:7: fComp
        {
            pushFollow(FOLLOW_fComp_in_synpred48_Pddl1732);
            fComp();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred51_Pddl
    public final void synpred51_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:313:28: ( typedVariableList )
        // Pddl.g:313:28: typedVariableList
        {
            pushFollow(FOLLOW_typedVariableList_in_synpred51_Pddl1891);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred64_Pddl
    public final void synpred64_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:353:4: ( '(' binaryOp fExp fExp2 ')' )
        // Pddl.g:353:4: '(' binaryOp fExp fExp2 ')'
        {
            match(input, 67, FOLLOW_67_in_synpred64_Pddl2147);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_binaryOp_in_synpred64_Pddl2149);
            binaryOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred64_Pddl2151);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp2_in_synpred64_Pddl2153);
            fExp2();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred64_Pddl2155);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred65_Pddl
    public final void synpred65_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:354:4: ( '(' '-' fExp ')' )
        // Pddl.g:354:4: '(' '-' fExp ')'
        {
            match(input, 67, FOLLOW_67_in_synpred65_Pddl2172);
            if (state.failed) {
                return;
            }

            match(input, 71, FOLLOW_71_in_synpred65_Pddl2174);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred65_Pddl2176);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred65_Pddl2178);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred66_Pddl
    public final void synpred66_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:355:7: ( '(' 'sin' fExp ')' )
        // Pddl.g:355:7: '(' 'sin' fExp ')'
        {
            match(input, 67, FOLLOW_67_in_synpred66_Pddl2194);
            if (state.failed) {
                return;
            }

            match(input, 134, FOLLOW_134_in_synpred66_Pddl2196);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred66_Pddl2198);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred66_Pddl2200);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred67_Pddl
    public final void synpred67_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:356:7: ( '(' 'cos' fExp ')' )
        // Pddl.g:356:7: '(' 'cos' fExp ')'
        {
            match(input, 67, FOLLOW_67_in_synpred67_Pddl2216);
            if (state.failed) {
                return;
            }

            match(input, 110, FOLLOW_110_in_synpred67_Pddl2218);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fExp_in_synpred67_Pddl2220);
            fExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred67_Pddl2222);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred97_Pddl
    public final void synpred97_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:423:12: ( NUMBER )
        // Pddl.g:423:12: NUMBER
        {
            match(input, NUMBER, FOLLOW_NUMBER_in_synpred97_Pddl2665);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred99_Pddl
    public final void synpred99_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:426:4: ( '(' 'and' ( daEffect )* ')' )
        // Pddl.g:426:4: '(' 'and' ( daEffect )* ')'
        {
            match(input, 67, FOLLOW_67_in_synpred99_Pddl2679);
            if (state.failed) {
                return;
            }

            match(input, 106, FOLLOW_106_in_synpred99_Pddl2681);
            if (state.failed) {
                return;
            }

            // Pddl.g:426:14: ( daEffect )*
            loop99:
            while (true) {
                int alt99 = 2;
                int LA99_0 = input.LA(1);
                if ((LA99_0 == 67)) {
                    alt99 = 1;
                }

                switch (alt99) {
                    case 1: // Pddl.g:426:14: daEffect
                    {
                        pushFollow(FOLLOW_daEffect_in_synpred99_Pddl2683);
                        daEffect();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        break loop99;
                }
            }

            match(input, 68, FOLLOW_68_in_synpred99_Pddl2686);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred100_Pddl
    public final void synpred100_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:427:4: ( timedEffect )
        // Pddl.g:427:4: timedEffect
        {
            pushFollow(FOLLOW_timedEffect_in_synpred100_Pddl2691);
            timedEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred101_Pddl
    public final void synpred101_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:428:4: ( '(' 'forall' '(' typedVariableList ')' daEffect ')' )
        // Pddl.g:428:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
        {
            match(input, 67, FOLLOW_67_in_synpred101_Pddl2696);
            if (state.failed) {
                return;
            }

            match(input, 117, FOLLOW_117_in_synpred101_Pddl2698);
            if (state.failed) {
                return;
            }

            match(input, 67, FOLLOW_67_in_synpred101_Pddl2700);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_typedVariableList_in_synpred101_Pddl2702);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred101_Pddl2704);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daEffect_in_synpred101_Pddl2706);
            daEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred101_Pddl2708);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred102_Pddl
    public final void synpred102_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:429:4: ( '(' 'when' daGD timedEffect ')' )
        // Pddl.g:429:4: '(' 'when' daGD timedEffect ')'
        {
            match(input, 67, FOLLOW_67_in_synpred102_Pddl2713);
            if (state.failed) {
                return;
            }

            match(input, 140, FOLLOW_140_in_synpred102_Pddl2715);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daGD_in_synpred102_Pddl2717);
            daGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timedEffect_in_synpred102_Pddl2719);
            timedEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred102_Pddl2721);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred103_Pddl
    public final void synpred103_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:434:4: ( '(' 'at' timeSpecifier daEffect ')' )
        // Pddl.g:434:4: '(' 'at' timeSpecifier daEffect ')'
        {
            match(input, 67, FOLLOW_67_in_synpred103_Pddl2745);
            if (state.failed) {
                return;
            }

            match(input, 108, FOLLOW_108_in_synpred103_Pddl2747);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timeSpecifier_in_synpred103_Pddl2749);
            timeSpecifier();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_daEffect_in_synpred103_Pddl2751);
            daEffect();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred103_Pddl2753);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred104_Pddl
    public final void synpred104_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:435:4: ( '(' 'at' timeSpecifier fAssignDA ')' )
        // Pddl.g:435:4: '(' 'at' timeSpecifier fAssignDA ')'
        {
            match(input, 67, FOLLOW_67_in_synpred104_Pddl2763);
            if (state.failed) {
                return;
            }

            match(input, 108, FOLLOW_108_in_synpred104_Pddl2765);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_timeSpecifier_in_synpred104_Pddl2767);
            timeSpecifier();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_fAssignDA_in_synpred104_Pddl2769);
            fAssignDA();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred104_Pddl2771);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred105_Pddl
    public final void synpred105_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:444:9: ( ( binaryOp fExpDA fExpDA ) )
        // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
        {
            // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
            // Pddl.g:444:10: binaryOp fExpDA fExpDA
            {
                pushFollow(FOLLOW_binaryOp_in_synpred105_Pddl2827);
                binaryOp();
                state._fsp--;
                if (state.failed) {
                    return;
                }

                pushFollow(FOLLOW_fExpDA_in_synpred105_Pddl2829);
                fExpDA();
                state._fsp--;
                if (state.failed) {
                    return;
                }

                pushFollow(FOLLOW_fExpDA_in_synpred105_Pddl2831);
                fExpDA();
                state._fsp--;
                if (state.failed) {
                    return;
                }

            }

        }

    }

    // $ANTLR start synpred106_Pddl
    public final void synpred106_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:444:4: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' )
        // Pddl.g:444:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
        {
            match(input, 67, FOLLOW_67_in_synpred106_Pddl2823);
            if (state.failed) {
                return;
            }

            // Pddl.g:444:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
            int alt100 = 2;
            int LA100_0 = input.LA(1);
            if ((LA100_0 == 71)) {
                int LA100_1 = input.LA(2);
                if ((synpred105_Pddl())) {
                    alt100 = 1;
                } else if ((true)) {
                    alt100 = 2;
                }

            } else if (((LA100_0 >= 69 && LA100_0 <= 70) || LA100_0 == 72 || LA100_0 == 102)) {
                alt100 = 1;
            } else {
                if (state.backtracking > 0) {
                    state.failed = true;
                    return;
                }
                NoViableAltException nvae
                        = new NoViableAltException("", 100, 0, input);
                throw nvae;
            }

            switch (alt100) {
                case 1: // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
                {
                    // Pddl.g:444:9: ( binaryOp fExpDA fExpDA )
                    // Pddl.g:444:10: binaryOp fExpDA fExpDA
                    {
                        pushFollow(FOLLOW_binaryOp_in_synpred106_Pddl2827);
                        binaryOp();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred106_Pddl2829);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred106_Pddl2831);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }

                }
                break;
                case 2: // Pddl.g:444:36: ( '-' fExpDA )
                {
                    // Pddl.g:444:36: ( '-' fExpDA )
                    // Pddl.g:444:37: '-' fExpDA
                    {
                        match(input, 71, FOLLOW_71_in_synpred106_Pddl2837);
                        if (state.failed) {
                            return;
                        }

                        pushFollow(FOLLOW_fExpDA_in_synpred106_Pddl2839);
                        fExpDA();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }

                }
                break;

            }

            match(input, 68, FOLLOW_68_in_synpred106_Pddl2843);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred113_Pddl
    public final void synpred113_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:482:4: ( '(' ':init' ( initEl )* ')' )
        // Pddl.g:482:4: '(' ':init' ( initEl )* ')'
        {
            match(input, 67, FOLLOW_67_in_synpred113_Pddl3084);
            if (state.failed) {
                return;
            }

            match(input, 87, FOLLOW_87_in_synpred113_Pddl3086);
            if (state.failed) {
                return;
            }

            // Pddl.g:482:16: ( initEl )*
            loop101:
            while (true) {
                int alt101 = 2;
                int LA101_0 = input.LA(1);
                if ((LA101_0 == 67)) {
                    alt101 = 1;
                }

                switch (alt101) {
                    case 1: // Pddl.g:482:16: initEl
                    {
                        pushFollow(FOLLOW_initEl_in_synpred113_Pddl3088);
                        initEl();
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

            match(input, 68, FOLLOW_68_in_synpred113_Pddl3091);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred124_Pddl
    public final void synpred124_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:517:4: ( '(' 'and' ( prefConGD )* ')' )
        // Pddl.g:517:4: '(' 'and' ( prefConGD )* ')'
        {
            match(input, 67, FOLLOW_67_in_synpred124_Pddl3380);
            if (state.failed) {
                return;
            }

            match(input, 106, FOLLOW_106_in_synpred124_Pddl3382);
            if (state.failed) {
                return;
            }

            // Pddl.g:517:14: ( prefConGD )*
            loop103:
            while (true) {
                int alt103 = 2;
                int LA103_0 = input.LA(1);
                if ((LA103_0 == 67)) {
                    alt103 = 1;
                }

                switch (alt103) {
                    case 1: // Pddl.g:517:14: prefConGD
                    {
                        pushFollow(FOLLOW_prefConGD_in_synpred124_Pddl3384);
                        prefConGD();
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

            match(input, 68, FOLLOW_68_in_synpred124_Pddl3387);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred125_Pddl
    public final void synpred125_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:518:4: ( '(' 'forall' '(' typedVariableList ')' prefConGD ')' )
        // Pddl.g:518:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
        {
            match(input, 67, FOLLOW_67_in_synpred125_Pddl3392);
            if (state.failed) {
                return;
            }

            match(input, 117, FOLLOW_117_in_synpred125_Pddl3394);
            if (state.failed) {
                return;
            }

            match(input, 67, FOLLOW_67_in_synpred125_Pddl3396);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_typedVariableList_in_synpred125_Pddl3398);
            typedVariableList();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred125_Pddl3400);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_prefConGD_in_synpred125_Pddl3402);
            prefConGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred125_Pddl3404);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred127_Pddl
    public final void synpred127_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:519:4: ( '(' 'preference' ( NAME )? conGD ')' )
        // Pddl.g:519:4: '(' 'preference' ( NAME )? conGD ')'
        {
            match(input, 67, FOLLOW_67_in_synpred127_Pddl3409);
            if (state.failed) {
                return;
            }

            match(input, 130, FOLLOW_130_in_synpred127_Pddl3411);
            if (state.failed) {
                return;
            }

            // Pddl.g:519:21: ( NAME )?
            int alt104 = 2;
            int LA104_0 = input.LA(1);
            if ((LA104_0 == NAME)) {
                alt104 = 1;
            }
            switch (alt104) {
                case 1: // Pddl.g:519:21: NAME
                {
                    match(input, NAME, FOLLOW_NAME_in_synpred127_Pddl3413);
                    if (state.failed) {
                        return;
                    }

                }
                break;

            }

            pushFollow(FOLLOW_conGD_in_synpred127_Pddl3416);
            conGD();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred127_Pddl3418);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred129_Pddl
    public final void synpred129_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:531:4: ( '(' binaryOp metricFExp metricFExp ')' )
        // Pddl.g:531:4: '(' binaryOp metricFExp metricFExp ')'
        {
            match(input, 67, FOLLOW_67_in_synpred129_Pddl3479);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_binaryOp_in_synpred129_Pddl3481);
            binaryOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred129_Pddl3483);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred129_Pddl3485);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred129_Pddl3487);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred131_Pddl
    public final void synpred131_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:533:4: ( '(' multiOp metricFExp ( metricFExp )+ ')' )
        // Pddl.g:533:4: '(' multiOp metricFExp ( metricFExp )+ ')'
        {
            match(input, 67, FOLLOW_67_in_synpred131_Pddl3509);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_multiOp_in_synpred131_Pddl3511);
            multiOp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred131_Pddl3513);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            // Pddl.g:533:27: ( metricFExp )+
            int cnt105 = 0;
            loop105:
            while (true) {
                int alt105 = 2;
                int LA105_0 = input.LA(1);
                if ((LA105_0 == NAME || LA105_0 == NUMBER || (LA105_0 >= 66 && LA105_0 <= 67))) {
                    alt105 = 1;
                }

                switch (alt105) {
                    case 1: // Pddl.g:533:27: metricFExp
                    {
                        pushFollow(FOLLOW_metricFExp_in_synpred131_Pddl3515);
                        metricFExp();
                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }
                    break;

                    default:
                        if (cnt105 >= 1) {
                            break loop105;
                        }
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return;
                        }
                        EarlyExitException eee = new EarlyExitException(105, input);
                        throw eee;
                }
                cnt105++;
            }

            match(input, 68, FOLLOW_68_in_synpred131_Pddl3518);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred132_Pddl
    public final void synpred132_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:535:4: ( '(' '-' metricFExp ')' )
        // Pddl.g:535:4: '(' '-' metricFExp ')'
        {
            match(input, 67, FOLLOW_67_in_synpred132_Pddl3542);
            if (state.failed) {
                return;
            }

            match(input, 71, FOLLOW_71_in_synpred132_Pddl3544);
            if (state.failed) {
                return;
            }

            pushFollow(FOLLOW_metricFExp_in_synpred132_Pddl3546);
            metricFExp();
            state._fsp--;
            if (state.failed) {
                return;
            }

            match(input, 68, FOLLOW_68_in_synpred132_Pddl3548);
            if (state.failed) {
                return;
            }

        }

    }

    // $ANTLR start synpred134_Pddl
    public final void synpred134_Pddl_fragment ( ) throws RecognitionException {
        // Pddl.g:538:4: ( fHead )
        // Pddl.g:538:4: fHead
        {
            pushFollow(FOLLOW_fHead_in_synpred134_Pddl3570);
            fHead();
            state._fsp--;
            if (state.failed) {
                return;
            }

        }

    }

    // Delegated rules
    public final boolean synpred129_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred129_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

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

    public final boolean synpred125_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred125_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred101_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred101_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred65_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred65_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred97_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred97_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred51_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred51_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred124_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred124_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred99_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred113_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred113_Pddl_fragment(); // can never throw exception
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

    public final boolean synpred131_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred131_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred64_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred64_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred48_Pddl ( ) {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred48_Pddl_fragment(); // can never throw exception
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
            return "144:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )";
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
            return "()+ loopback of 144:16: ( singleTypeNameList )+";
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
            return "195:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )";
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
            return "()+ loopback of 195:20: ( singleTypeVarList )+";
        }
    }

    protected class DFA70 extends DFA {

        public DFA70 (BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 70;
            this.eot = DFA70_eot;
            this.eof = DFA70_eof;
            this.min = DFA70_min;
            this.max = DFA70_max;
            this.accept = DFA70_accept;
            this.special = DFA70_special;
            this.transition = DFA70_transition;
        }

        @Override
        public String getDescription ( ) {
            return "481:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' goalDesc ')' -> ^( FORMULAINIT goalDesc ) );";
        }

        @Override
        public int specialStateTransition (int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s) {
                case 0:
                    int LA70_23 = input.LA(1);

                    int index70_23 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred113_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 8;
                    }

                    input.seek(index70_23);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 1:
                    int LA70_24 = input.LA(1);

                    int index70_24 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred113_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 8;
                    }

                    input.seek(index70_24);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 2:
                    int LA70_34 = input.LA(1);

                    int index70_34 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred113_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 8;
                    }

                    input.seek(index70_34);
                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 3:
                    int LA70_36 = input.LA(1);

                    int index70_36 = input.index();
                    input.rewind();
                    s = -1;
                    if ((synpred113_Pddl())) {
                        s = 4;
                    } else if ((true)) {
                        s = 8;
                    }

                    input.seek(index70_36);
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
                    = new NoViableAltException(getDescription(), 70, _s, input);
            error(nvae);
            throw nvae;
        }
    }
}
