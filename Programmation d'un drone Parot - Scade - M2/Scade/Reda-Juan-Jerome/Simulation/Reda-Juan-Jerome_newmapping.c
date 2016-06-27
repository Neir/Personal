/* Reda-Juan-Jerome_newmapping.c */

#include <stddef.h>

#include "NewSmuTypes.h"
#include "NewSmuMapping.h"
#include "Reda-Juan-Jerome_newtype.h"
#include "Reda-Juan-Jerome_newmapping.h"

/* iterators */
static const MappingIterator iter_array_3;
static const MappingIterator iter_map_3;
static const MappingIterator iter_array_3 = { "array", 3, 3, NULL};
static const MappingIterator iter_map_3 = { "map", 3, 3, NULL};

/* clock active helper functions */
static int isActive_SSM_TR_SM1_SSM_TR_hovering_2_SM1 (void* pHandle) { return (*(SSM_TR_SM1*)pHandle == SSM_TR_hovering_2_SM1); }
static int isActive_SSM_TR_SM1_SSM_TR_hovering_1_SM1 (void* pHandle) { return (*(SSM_TR_SM1*)pHandle == SSM_TR_hovering_1_SM1); }
static int isActive_SSM_ST_SM1_SSM_st_hovering_SM1 (void* pHandle) { return (*(SSM_ST_SM1*)pHandle == SSM_st_hovering_SM1); }
static int isActive_SSM_ST_SM1_SSM_st_Landing_SM1 (void* pHandle) { return (*(SSM_ST_SM1*)pHandle == SSM_st_Landing_SM1); }
static int isActive_SSM_ST_SM1_SSM_st_takingOff_SM1 (void* pHandle) { return (*(SSM_ST_SM1*)pHandle == SSM_st_takingOff_SM1); }
static int isActive_SSM_ST_SM1_SSM_st_right_SM1 (void* pHandle) { return (*(SSM_ST_SM1*)pHandle == SSM_st_right_SM1); }
static int isActive_SSM_TR_SM1_SSM_TR_left_1_SM1 (void* pHandle) { return (*(SSM_TR_SM1*)pHandle == SSM_TR_left_1_SM1); }
static int isActive_SSM_ST_SM1_SSM_st_left_SM1 (void* pHandle) { return (*(SSM_ST_SM1*)pHandle == SSM_st_left_SM1); }

/* forward declarations */
#define MAP_DECL(ident, nb) static const MappingEntry ident##_entries[nb]; static const MappingScope ident
MAP_DECL(scope_26, 1);
MAP_DECL(scope_12, 1);
MAP_DECL(scope_11, 1);
MAP_DECL(scope_10, 3);
MAP_DECL(scope_9, 3);
MAP_DECL(scope_8, 3);
MAP_DECL(scope_7, 3);
MAP_DECL(scope_6, 1);
MAP_DECL(scope_5, 4);
MAP_DECL(scope_2, 12);
MAP_DECL(scope_1, 4);
MAP_DECL(scope_0, 1);

/* array_int32_3 */
static const MappingEntry scope_26_entries[1] = {
  /* 0 */ { MAP_ARRAY, "", &iter_array_3, sizeof(kcg_int32), 0, &_Type_kcg_int32_Utils, NULL, NULL, NULL, 1, 0}
};
static const MappingScope scope_26 = {
  "array_int32_3",
  scope_26_entries, 1,
};

/* Deplacement/ DeplacementSM1:hovering:<2 */
static const MappingEntry scope_12_entries[1] = {
  /* 0 */ { MAP_WEAK_TRANSITION, ">:", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_hovering_2_SM1, NULL, 1, 0}
};
static const MappingScope scope_12 = {
  "Deplacement/ DeplacementSM1:hovering:<2",
  scope_12_entries, 1,
};

/* Deplacement/ DeplacementSM1:hovering:<1 */
static const MappingEntry scope_11_entries[1] = {
  /* 0 */ { MAP_WEAK_TRANSITION, ">:", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_hovering_1_SM1, NULL, 1, 0}
};
static const MappingScope scope_11 = {
  "Deplacement/ DeplacementSM1:hovering:<1",
  scope_11_entries, 1,
};

/* Deplacement/ DeplacementSM1:hovering: */
static const MappingEntry scope_10_entries[3] = {
  /* 0 */ { MAP_FORK, "<1", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_hovering_1_SM1, &scope_11, 1, 1},
  /* 1 */ { MAP_FORK, "<2", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_hovering_2_SM1, &scope_12, 1, 2},
  /* 2 */ { MAP_LOCAL, "_L2", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L2_SM1_hovering, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_hovering_SM1, &scope_26, 1, 0}
};
static const MappingScope scope_10 = {
  "Deplacement/ DeplacementSM1:hovering:",
  scope_10_entries, 3,
};

/* Deplacement/ DeplacementSM1:Landing: */
static const MappingEntry scope_9_entries[3] = {
  /* 0 */ { MAP_LOCAL, "_L1", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L1_SM1_Landing, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_Landing_SM1, &scope_26, 1, 2},
  /* 1 */ { MAP_LOCAL, "_L2", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L2_SM1_Landing, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_Landing_SM1, &scope_26, 1, 1},
  /* 2 */ { MAP_LOCAL, "_L3", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L3_SM1_Landing, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_Landing_SM1, &scope_26, 1, 0}
};
static const MappingScope scope_9 = {
  "Deplacement/ DeplacementSM1:Landing:",
  scope_9_entries, 3,
};

/* Deplacement/ DeplacementSM1:takingOff: */
static const MappingEntry scope_8_entries[3] = {
  /* 0 */ { MAP_LOCAL, "_L3", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L3_SM1_takingOff, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_takingOff_SM1, &scope_26, 1, 2},
  /* 1 */ { MAP_LOCAL, "_L4", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L4_SM1_takingOff, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_takingOff_SM1, &scope_26, 1, 1},
  /* 2 */ { MAP_LOCAL, "_L5", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L5_SM1_takingOff, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_takingOff_SM1, &scope_26, 1, 0}
};
static const MappingScope scope_8 = {
  "Deplacement/ DeplacementSM1:takingOff:",
  scope_8_entries, 3,
};

/* Deplacement/ DeplacementSM1:right: */
static const MappingEntry scope_7_entries[3] = {
  /* 0 */ { MAP_LOCAL, "_L10", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L10_SM1_right, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_right_SM1, &scope_26, 1, 2},
  /* 1 */ { MAP_LOCAL, "_L11", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L11_SM1_right, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_right_SM1, &scope_26, 1, 1},
  /* 2 */ { MAP_LOCAL, "_L12", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L12_SM1_right, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_right_SM1, &scope_26, 1, 0}
};
static const MappingScope scope_7 = {
  "Deplacement/ DeplacementSM1:right:",
  scope_7_entries, 3,
};

/* Deplacement/ DeplacementSM1:left:<1 */
static const MappingEntry scope_6_entries[1] = {
  /* 0 */ { MAP_WEAK_TRANSITION, ">:", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_left_1_SM1, NULL, 1, 0}
};
static const MappingScope scope_6 = {
  "Deplacement/ DeplacementSM1:left:<1",
  scope_6_entries, 1,
};

/* Deplacement/ DeplacementSM1:left: */
static const MappingEntry scope_5_entries[4] = {
  /* 0 */ { MAP_FORK, "<1", NULL, 0, 0, NULL, &scope_2_entries[2], isActive_SSM_TR_SM1_SSM_TR_left_1_SM1, &scope_6, 1, 3},
  /* 1 */ { MAP_LOCAL, "_L10", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L10_SM1_left, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_left_SM1, &scope_26, 1, 2},
  /* 2 */ { MAP_LOCAL, "_L11", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L11_SM1_left, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_left_SM1, &scope_26, 1, 1},
  /* 3 */ { MAP_LOCAL, "_L12", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx._L12_SM1_left, &_Type_array_int32_3_Utils, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_left_SM1, &scope_26, 1, 0}
};
static const MappingScope scope_5 = {
  "Deplacement/ DeplacementSM1:left:",
  scope_5_entries, 4,
};

/* Deplacement/ DeplacementSM1: */
static const MappingEntry scope_2_entries[12] = {
  /* 0 */ { MAP_LOCAL, "@active_state", NULL, sizeof(SSM_ST_SM1), (size_t)&outputs_ctx.SM1_state_act, &_Type_SSM_ST_SM1_Utils, NULL, NULL, NULL, 0, 0},
  /* 1 */ { MAP_LOCAL, "@active_strong_transition", NULL, sizeof(SSM_TR_SM1), (size_t)&outputs_ctx.SM1_fired_strong, &_Type_SSM_TR_SM1_Utils, NULL, NULL, NULL, 0, 5},
  /* 2 */ { MAP_LOCAL, "@active_weak_transition", NULL, sizeof(SSM_TR_SM1), (size_t)&outputs_ctx.SM1_fired, &_Type_SSM_TR_SM1_Utils, NULL, NULL, NULL, 0, 6},
  /* 3 */ { MAP_LOCAL, "@next_state", NULL, sizeof(SSM_ST_SM1), (size_t)&outputs_ctx.SM1_state_nxt, &_Type_SSM_ST_SM1_Utils, NULL, NULL, NULL, 0, 2},
  /* 4 */ { MAP_LOCAL, "@reset_active_state", NULL, sizeof(kcg_bool), (size_t)&outputs_ctx.SM1_reset_act, &_Type_kcg_bool_Utils, NULL, NULL, NULL, 0, 1},
  /* 5 */ { MAP_LOCAL, "@reset_next_state", NULL, sizeof(kcg_bool), (size_t)&outputs_ctx.SM1_reset_nxt, &_Type_kcg_bool_Utils, NULL, NULL, NULL, 0, 3},
  /* 6 */ { MAP_LOCAL, "@selected_state", NULL, sizeof(SSM_ST_SM1), (size_t)&outputs_ctx.SM1_state_sel, &_Type_SSM_ST_SM1_Utils, NULL, NULL, NULL, 0, 4},
  /* 7 */ { MAP_STATE, "Landing:", NULL, 0, 0, NULL, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_Landing_SM1, &scope_9, 1, 10},
  /* 8 */ { MAP_STATE, "hovering:", NULL, 0, 0, NULL, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_hovering_SM1, &scope_10, 1, 11},
  /* 9 */ { MAP_STATE, "left:", NULL, 0, 0, NULL, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_left_SM1, &scope_5, 1, 7},
  /* 10 */ { MAP_STATE, "right:", NULL, 0, 0, NULL, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_right_SM1, &scope_7, 1, 8},
  /* 11 */ { MAP_STATE, "takingOff:", NULL, 0, 0, NULL, &scope_2_entries[0], isActive_SSM_ST_SM1_SSM_st_takingOff_SM1, &scope_8, 1, 9}
};
static const MappingScope scope_2 = {
  "Deplacement/ DeplacementSM1:",
  scope_2_entries, 12,
};

/* Deplacement/ Deplacement */
static const MappingEntry scope_1_entries[4] = {
  /* 0 */ { MAP_OUTPUT, "OUTPUT", NULL, sizeof(array_int32_3), (size_t)&outputs_ctx.OUTPUT, &_Type_array_int32_3_Utils, NULL, NULL, &scope_26, 1, 1},
  /* 1 */ { MAP_AUTOMATON, "SM1:", NULL, 0, 0, NULL, NULL, NULL, &scope_2, 1, 0},
  /* 2 */ { MAP_INPUT, "Triplet", NULL, sizeof(array_int32_3), (size_t)&inputs_ctx.Triplet, &_Type_array_int32_3_Utils, NULL, NULL, &scope_26, 1, 3},
  /* 3 */ { MAP_INPUT, "inGOTO", NULL, sizeof(DIRECTION), (size_t)&inputs_ctx.inGOTO, &_Type_DIRECTION_Utils, NULL, NULL, NULL, 1, 2}
};
static const MappingScope scope_1 = {
  "Deplacement/ Deplacement",
  scope_1_entries, 4,
};

/*  */
static const MappingEntry scope_0_entries[1] = {
  /* 0 */ { MAP_ROOT, "Deplacement", NULL, 0, 0, NULL, NULL, NULL, &scope_1, 1, 0}
};
static const MappingScope scope_0 = {
  "",
  scope_0_entries, 1,
};

const MappingScope* pTop = &scope_0;

