#include <jni.h>
#include <lcm.h>
#include <lua.h>
#include <string.h>

jstring Java_se_ltu_emapal_androidcompute_ActivityMain_stringFromJNI(JNIEnv *env, jobject self) {
    return (*env)->NewStringUTF(env, "Hello from JNI!");
}