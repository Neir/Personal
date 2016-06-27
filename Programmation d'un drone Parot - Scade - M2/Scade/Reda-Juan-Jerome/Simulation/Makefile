#############################################################
######    Begin of Makefile
#############################################################

SCADE=C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\.

SHELL=cmd.exe

C_FLAGS = -pedantic

all: Deplacement.exe Deplacement.dll 

win32/Deplacement.o: Deplacement.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "Deplacement.c" -o "win32\Deplacement.o"

win32/kcg_types.o: kcg_types.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "kcg_types.c" -o "win32\kcg_types.o"

win32/kcg_consts.o: kcg_consts.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "kcg_consts.c" -o "win32\kcg_consts.o"

win32/Reda-Juan-Jerome_newtype.o: Reda-Juan-Jerome_newtype.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "Reda-Juan-Jerome_newtype.c" -o "win32\Reda-Juan-Jerome_newtype.o"

win32/Reda-Juan-Jerome_newmapping.o: Reda-Juan-Jerome_newmapping.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "Reda-Juan-Jerome_newmapping.c" -o "win32\Reda-Juan-Jerome_newmapping.o"

win32/Reda-Juan-Jerome_newinterface.o: Reda-Juan-Jerome_newinterface.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "Reda-Juan-Jerome_newinterface.c" -o "win32\Reda-Juan-Jerome_newinterface.o"

win32/SmuProxy.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/SCADE\ R16.2.1/SCADE/lib/SmuProxy.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\lib\SmuProxy.c" -o "win32\SmuProxy.o"

win32/NewSmuTypes.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/SCADE\ R16.2.1/SCADE/lib/NewSmuTypes.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\lib\NewSmuTypes.c" -o "win32\NewSmuTypes.o"

win32/SmuConverter.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/SCADE\ R16.2.1/SCADE/lib/SmuConverter.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\lib\SmuConverter.c" -o "win32\SmuConverter.o"

Deplacement.dll: win32/Deplacement.o win32/kcg_types.o win32/kcg_consts.o win32/Reda-Juan-Jerome_newtype.o win32/Reda-Juan-Jerome_newmapping.o win32/Reda-Juan-Jerome_newinterface.o win32/SmuProxy.o win32/NewSmuTypes.o win32/SmuConverter.o SmuExport.def 
	gcc -mwindows -mwin32 -shared -static-libgcc  "win32\Deplacement.o" "win32\kcg_types.o" "win32\kcg_consts.o" "win32\Reda-Juan-Jerome_newtype.o" "win32\Reda-Juan-Jerome_newmapping.o" "win32\Reda-Juan-Jerome_newinterface.o" "win32\SmuProxy.o" "win32\NewSmuTypes.o" "win32\SmuConverter.o" -Wl,--output-def,"SmuExport.def" -lm -lwsock32 -m32 -o "Deplacement.dll" 

SmuExport.def: C:/Program\ Files\ (x86)/Esterel\ Technologies/SCADE\ R16.2.1/SCADE/lib/SmuExport.def
	copy "C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\lib\SmuExport.def" "SmuExport.def"

win32/Deplacement_dll.o: Deplacement_dll.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "Deplacement_dll.c" -o "win32\Deplacement_dll.o"

win32/SmuMainWindows.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/SCADE\ R16.2.1/SCADE/lib/SmuMainWindows.c
	gcc -I"." -I"$(SCADE)" -I"$(SCADE)\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\include" -I"C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\libraries\SC65\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c -ansi -std=c99 -m32 "C:\Program Files (x86)\Esterel Technologies\SCADE R16.2.1\SCADE\lib\SmuMainWindows.c" -o "win32\SmuMainWindows.o"

Deplacement.exe: win32/Deplacement_dll.o win32/SmuMainWindows.o 
	gcc "win32\Deplacement_dll.o" "win32\SmuMainWindows.o" -lm -lwsock32 -m32 -o "Deplacement.exe" 

#############################################################
######    End of Makefile
#############################################################
