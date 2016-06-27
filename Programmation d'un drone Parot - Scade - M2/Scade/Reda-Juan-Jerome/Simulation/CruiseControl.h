/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config C:/Users/stl/Desktop/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */
#ifndef _CruiseControl_H_
#define _CruiseControl_H_

#include "kcg_types.h"
#include "Control.h"

/* ========================  input structure  ====================== */
typedef struct {
  kcg_float64 /* CruiseControl::cruise */ cruise;
  kcg_float64 /* CruiseControl::maxCruise */ maxCruise;
  kcg_bool /* CruiseControl::cruiseControlOn */ cruiseControlOn;
} inC_CruiseControl;

/* =====================  no output structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_float64 /* CruiseControl::cruiseControled */ cruiseControled;
  /* -----------------------  no local probes  ----------------------- */
  /* -------------------- initialization variables  ------------------ */
  kcg_bool init1;
  kcg_bool init;
  /* ----------------------- local memories  ------------------------- */
  kcg_size pre_times_2;
  kcg_size pre_times_1;
  SSM_ST_SM1 /* CruiseControl::SM1 */ SM1_state_nxt;
  kcg_bool /* CruiseControl::SM1 */ SM1_reset_act;
  kcg_bool /* CruiseControl::SM1 */ SM1_reset_nxt;
  /* ---------------------  sub nodes' contexts  --------------------- */
  outC_Control /* 1 */ Context_1;
  /* ------------------ clocks of observable data -------------------- */
  SSM_ST_SM1 /* CruiseControl::SM1 */ SM1_state_act;
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_float64 /* CruiseControl::SM1::State1::_L1 */ _L1_SM1_State1;
  kcg_float64 /* CruiseControl::SM1::State2::_L4 */ _L4_SM1_State2;
  kcg_bool /* CruiseControl::SM1::State2::_L3 */ _L3_SM1_State2;
  kcg_float64 /* CruiseControl::SM1::State2::_L2 */ _L2_SM1_State2;
  kcg_float64 /* CruiseControl::SM1::State2::_L1 */ _L1_SM1_State2;
  SSM_ST_SM1 /* CruiseControl::SM1 */ SM1_state_sel;
  SSM_TR_SM1 /* CruiseControl::SM1 */ SM1_fired_strong;
  SSM_TR_SM1 /* CruiseControl::SM1 */ SM1_fired;
} outC_CruiseControl;

/* ===========  node initialization and cycle functions  =========== */
/* CruiseControl */
extern void CruiseControl(inC_CruiseControl *inC, outC_CruiseControl *outC);

#ifndef KCG_NO_EXTERN_CALL_TO_RESET
extern void CruiseControl_reset(outC_CruiseControl *outC);
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

#ifndef KCG_USER_DEFINED_INIT
extern void CruiseControl_init(outC_CruiseControl *outC);
#endif /* KCG_USER_DEFINED_INIT */

#endif /* _CruiseControl_H_ */
/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** CruiseControl.h
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

