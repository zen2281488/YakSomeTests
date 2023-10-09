package utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class FailedTestExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            String className = context.getTestClass()
                    .map(Class::getName)
                    .orElse("UnknownClass");

            String methodName = context.getTestMethod()
                    .map(method -> method.getName() + "()")
                    .orElse("unknownMethod()");

            FailedTestRecorder.recordFailedTest(className, methodName);
        }

    }
}