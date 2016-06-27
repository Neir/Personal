/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config E:/Scade/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-14T16:12:48
*************************************************************$ */

#include "kcg_types.h"

#ifdef kcg_use_array_int32_3
kcg_bool kcg_comp_array_int32_3(array_int32_3 *kcg_c1, array_int32_3 *kcg_c2)
{
  kcg_bool kcg_equ;
  kcg_size kcg_ci;

  kcg_equ = kcg_true;
  for (kcg_ci = 0; kcg_ci < 3; kcg_ci++) {
    kcg_equ = kcg_equ & ((*kcg_c1)[kcg_ci] == (*kcg_c2)[kcg_ci]);
  }
  return kcg_equ;
}
#endif /* kcg_use_array_int32_3 */

/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** kcg_types.c
** Generation date: 2016-04-14T16:12:48
*************************************************************$ */

