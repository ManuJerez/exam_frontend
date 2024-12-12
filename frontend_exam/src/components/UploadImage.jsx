import { useEffect, useRef } from "react"

const UploadImage = () => {
  const cloudinaryRef = useRef();
  const widgetRef = useRef();

  useEffect(() => {
    cloudinaryRef.current = window.cloudinary;
    widgetRef.current = cloudinaryRef.current.createUploadWidget({
      cloudName: 'dfxfoz13q',
      uploadPreset: 'ml_default',
    }, 
    /*
    function (error, result) {
      console.log(error);
    }
    */
  );
  }, []);

  return (
    <button onClick={() => widgetRef.current.open()}>
      Upload
    </button>
  );
};

export default UploadImage;