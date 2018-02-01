package com.itmaoo.scenic.dao;

import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.query.ImageQuery;

public interface IImageDao extends IBaseDao<ImagePo>{

	ImagePo selectSingleByUserAndImageName(ImageQuery imageQuery);

	int updateByImageName(ImagePo image);

	

}
