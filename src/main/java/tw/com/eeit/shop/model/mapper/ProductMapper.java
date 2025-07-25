package tw.com.eeit.shop.model.mapper;

import org.springframework.beans.BeanUtils;

import tw.com.eeit.shop.model.dto.ProductDto;
import tw.com.eeit.shop.model.entity.Product;
import tw.com.eeit.shop.utils.CommonUtil;

public class ProductMapper {

	public static ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(entity, dto);

		dto.setId(entity.getProductId());
		dto.setName(entity.getProductName());

		// 將 photo 的 byte[] 轉換成 Base64 格式
		byte[] photo = entity.getProductPhoto();
		String base64Image = CommonUtil.getBase64Image(photo);
		dto.setPhoto(base64Image);

		return dto;
	}

}
