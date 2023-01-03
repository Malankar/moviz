import React from "react";
import { AiFillEdit, AiOutlineLeft } from "react-icons/ai";
import { RxCross1 } from "react-icons/rx";
import { useNavigate, useParams } from "react-router-dom";
import Seats from "../components/Seats";
const BookMovie = () => {
  const params = useParams();
  const navigate = useNavigate();
  return (
    <div>
      {/* Navbar */}
      <div className="flex justify-between items-center bg-black text-white">
        <div className="p-5 flex space-x-3 items-center ml-10">
          <AiOutlineLeft
            className="text-2xl cursor-pointer"
            onClick={() => navigate(`/${params.name}`)}
          />
          <p className="text-2xl font-bold mb-1">{params.name}</p>
        </div>
        <div className="flex space-x-6 mr-10 items-center p-5">
          <div className="flex border-[1px] border-gray-600 shadow-lg px-2 rounded-lg items-center space-x-2">
            <p>{params.seats} Tickets</p>
            {/* <AiFillEdit /> */}
          </div>
          {/* <div>
            <RxCross1 />
          </div> */}
        </div>
      </div>
      <Seats />
    </div>
  );
};

export default BookMovie;
