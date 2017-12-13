package cn.zmy.common.base.task;

/**
 * Created by zmy on 2017/12/13.
 */

public class ExecutorManager
{
    public static final ExecutorManager Instance = new ExecutorManager();

    private RunableExecutor mRunableExecutor;
    private CallableExecutor mCallableExecutor;

    public RunableExecutor getRunableExecutor()
    {
        if (mRunableExecutor == null)
        {
            synchronized (ExecutorManager.class)
            {
                if (mRunableExecutor == null)
                {
                    mRunableExecutor = new RunableExecutor();
                }
            }
        }

        return mRunableExecutor;
    }

    public CallableExecutor getCallableExecutor()
    {
        if (mCallableExecutor == null)
        {
            synchronized (ExecutorManager.class)
            {
                if (mCallableExecutor == null)
                {
                    mCallableExecutor = new CallableExecutor();
                }
            }
        }

        return mCallableExecutor;
    }
}
