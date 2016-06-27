/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config E:/Scade/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-14T16:12:49
*************************************************************$ */
#ifndef _Deplacement_H_
#define _Deplacement_H_

#include "kcg_types.h"

/* ========================  input structure  ====================== */
typedef struct {
  DIRECTION /* Deplacement::inGOTO */ inGOTO;
  array_int32_3 /* Deplacement::Triplet */ Triplet;
} inC_Deplacement;

/* =====================  no output structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  array_int32_3 /* Deplacement::OUTPUT */ OUTPUT;
  /* -----------------------  no local probes  ----------------------- */
  /* -----------------  no initialization variables  ----------------- */
  /* ----------------------- local memories  ------------------------- */
  SSM_ST_SM1 /* Deplacement::SM1 */ SM1_state_nxt;
  kcg_bool /* Deplacement::SM1 */ SM1_reset_act;
  kcg_bool /* Deplacement::SM1 */ SM1_reset_nxt;
  /* -------------------- no sub nodes' contexts  -------------------- */
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  array_int32_3 /* Deplacement::SM1::left::_L10 */ _L10_SM1_left;
  array_int32_3 /* Deplacement::SM1::left::_L11 */ _L11_SM1_left;
  array_int32_3 /* Deplacement::SM1::left::_L12 */ _L12_SM1_left;
  array_int32_3 /* Deplacement::SM1::right::_L10 */ _L10_SM1_right;
  array_int32_3 /* Deplacement::SM1::right::_L11 */ _L11_SM1_right;
  array_int32_3 /* Deplacement::SM1::right::_L12 */ _L12_SM1_right;
  array_int32_3 /* Deplacement::SM1::takingOff::_L3 */ _L3_SM1_takingOff;
  array_int32_3 /* Deplacement::SM1::takingOff::_L4 */ _L4_SM1_takingOff;
  array_int32_3 /* Deplacement::SM1::takingOff::_L5 */ _L5_SM1_takingOff;
  array_int32_3 /* Deplacement::SM1::Landing::_L1 */ _L1_SM1_Landing;
  array_int32_3 /* Deplacement::SM1::Landing::_L2 */ _L2_SM1_Landing;
  array_int32_3 /* Deplacement::SM1::Landing::_L3 */ _L3_SM1_Landing;
  array_int32_3 /* Deplacement::SM1::hovering::_L2 */ _L2_SM1_hovering;
  SSM_ST_SM1 /* Deplacement::SM1 */ SM1_state_sel;
  SSM_ST_SM1 /* Deplacement::SM1 */ SM1_state_act;
  SSM_TR_SM1 /* Deplacement::SM1 */ SM1_fired_strong;
  SSM_TR_SM1 /* Deplacement::SM1 */ SM1_fired;
} outC_Deplacement;

/* ===========  node initialization and cycle functions  =========== */
/* Deplacement */
extern void Deplacement(inC_Deplacement *inC, outC_Deplacement *outC);

#ifndef KCG_NO_EXTERN_CALL_TO_RESET
extern void Deplacement_reset(outC_Deplacement *outC);
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

#ifndef KCG_USER_DEFINED_INIT
extern void Deplacement_init(outC_Deplacement *outC);
#endif /* KCG_USER_DEFINED_INIT */

#endif /* _Deplacement_H_ */
/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Deplacement.h
** Generation date: 2016-04-14T16:12:49
*************************************************************$ */

