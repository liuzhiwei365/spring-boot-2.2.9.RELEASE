package org.lzw;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class ForJavaAgent {

    public static void premain(String agentArgs, Instrumentation inst){
        //通过参数中的Instrumentation inst，添加自己定义的ClassFileTransformer，来改变class文件

        System.out.println("premain1");
        System.out.println("agentArgs:  "+agentArgs);
        inst.addTransformer(new ClassFileTransformer() {

            String clazz = "org.lzw.TestTrans";
            String method = "sayHello";

            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                                    byte[] classfileBuffer) throws IllegalClassFormatException {

                String replace = className.replace("/", ".");
                System.out.println("calssName->"+className+"->"+replace);

                if(clazz.equals(replace)){

                    CtClass ct ;

                    try{
                        ct = ClassPool.getDefault().getCtClass(clazz);
                        CtMethod declaredMethod = ct.getDeclaredMethod(method);

                        String tmp = "System.out.println( \"java  字节码改造 \");";
                        declaredMethod.insertAfter(tmp);
                        return ct.toBytecode();

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
                return null;
            }
        });




    }


    public static void premain(String agentArgs){
        System.out.println("premain2");
        System.out.println("agentArgs:  "+agentArgs);
    }

}
