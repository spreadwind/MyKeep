package akiyama.mykeep.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import akiyama.mykeep.controller.IBaseController;
import akiyama.mykeep.db.model.BaseModel;
import akiyama.mykeep.db.model.IModel;
import akiyama.mykeep.db.model.RecordModel;

/**
 * 插入保存单条数据的Task
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-08-07  10:43
 */
public abstract class UpdateSingleDbTask extends AsyncTask<BaseModel,Void,Boolean> {

    private IBaseController mBaseController;
    private volatile Context mContext;
    private ProgressDialog mProgressBar;
    private boolean mIsShowPregressBar;
    public UpdateSingleDbTask(Context context, IBaseController baseController,boolean isShowPregressBar){
        this.mContext=context;
        this.mBaseController=baseController;
        this.mIsShowPregressBar = isShowPregressBar;
    }
    @Override
    protected void onPreExecute() {
        updatePreExecute();
    }

    @Override
    protected Boolean doInBackground(BaseModel... params) {
        if(mBaseController.updateById(mContext,params[0])){
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        updatePostExecute(aBoolean);
        if(mProgressBar!=null){
            mProgressBar.dismiss();
        }
    }

    /**
     * 保存数据前执行的操作
     */
    protected void updatePreExecute(){
        if(mIsShowPregressBar){
            mProgressBar=new ProgressDialog(mContext);
            mProgressBar.setMessage("正在更新，请稍后......");
            mProgressBar.show();
        }
    }


    /**
     * 保存数据成功后执行的操作
     */
    public abstract void updatePostExecute(Boolean aBoolean);
}
