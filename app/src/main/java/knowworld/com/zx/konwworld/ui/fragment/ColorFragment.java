/**
 * Copyright 2015 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package knowworld.com.zx.konwworld.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

import knowworld.com.zx.konwworld.R;

/**
 * Created by Bartosz Lipinski
 * 28.01.15
 */
public class ColorFragment extends Fragment {
    //private List<Getposts.PostsBean> postsBeanList;

    private static final String EXTRA_COLOR = "com.bartoszlipinski.flippablestackview.fragment.ColorFragment.EXTRA_COLOR";

    LRecyclerView mRecyclerView;

    public static ColorFragment newInstance(int backgroundColor) {
        ColorFragment fragment = new ColorFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(EXTRA_COLOR, backgroundColor);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyelerview2, container, false);
        Bundle bdl = getArguments();

        mRecyclerView = (LRecyclerView) v.findViewById(R.id.zhihu_recyc);

//        LayerDrawable bgDrawable = (LayerDrawable) mMainLayout.getBackground();
//        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.background_shape);
//        shape.setColor(bdl.getInt(EXTRA_COLOR));

        return v;
    }
}
