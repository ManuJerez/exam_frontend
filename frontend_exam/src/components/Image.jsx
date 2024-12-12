import React from 'react'
import { Cloudinary } from '@cloudinary/url-gen';
import { auto } from '@cloudinary/url-gen/actions/resize';
import { autoGravity } from '@cloudinary/url-gen/qualifiers/gravity';
import { AdvancedImage } from '@cloudinary/react';

const Image = ({imageName}) => {
  const cld = new Cloudinary({ cloud: { cloudName: 'dfxfoz13q' } });
  
  const img = cld
        .image(imageName)
        .format('auto')
        .quality('auto')
        .resize(auto().gravity(autoGravity()).width(300).height(300));

  return (<AdvancedImage cldImg={img}/>);
};

export default Image