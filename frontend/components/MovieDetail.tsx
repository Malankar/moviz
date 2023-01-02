import Movie from "../types/Movie";
import { AiFillStar } from "react-icons/ai";
import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useState } from "react";
interface MovieDetail {
  movie: Movie | null;
}
const MovieDetail: React.FC<MovieDetail> = ({ movie }) => {
  let [isOpen, setIsOpen] = useState(false);
  const [selectedSeat, setSelectedSeat] = useState(1);
  function closeModal() {
    setIsOpen(false);
  }

  function openModal() {
    setIsOpen(true);
  }

  const seats = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const handleSeatSelection = (seat: number) => {
    setSelectedSeat(seat);
  };
  console.log(selectedSeat);

  return (
    <>
      <div className="bg-black text-white">
        <div className="w-full md:max-w-7xl m-auto lg:flex block">
          <div className="flex justify-center lg:w-1/4">
            <img src={movie?.imageUrl} className="p-10" alt="" />
          </div>
          <div className="p-10 space-y-3">
            <h1 className="text-4xl font-bold">{movie?.title}</h1>
            <div className="flex space-x-2 items-center">
              <AiFillStar className="text-[#F07583] text-3xl" />
              <p className="text-xl font-semibold">{movie?.rating}/ 10</p>
            </div>
            <p className=" lg:max-w-md text-lg">{movie?.description}</p>
            <p>
              {movie?.genre.join(", ")} • {movie?.releaseDate}
            </p>
            <button
              className="bg-[#F84464] px-8 py-3 text-md font-semibold rounded-lg font-sans"
              onClick={openModal}
            >
              Book tickets
            </button>
          </div>
        </div>
      </div>
      <Transition appear show={isOpen} as={Fragment}>
        <Dialog as="div" className="relative z-10" onClose={closeModal}>
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black bg-opacity-80" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto">
            <div className="flex min-h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="w-full md:max-w-lg transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <Dialog.Title className="text-md font-medium leading-6 text-gray-900 flex justify-center">
                    How Many Seats?
                  </Dialog.Title>
                  <div className="w-24 m-auto p-2 my-3">
                    <img
                      src="https://cdn-icons-png.flaticon.com/512/1052/1052742.png"
                      alt=""
                    />
                  </div>
                  <div className="flex">
                    {seats.map((seat) => (
                      <div
                        className={`px-4 py-2.5 text-xs cursor-pointer rounded-full mr-2 hover:bg-[#F84464] hover:text-white ${
                          seat === selectedSeat ? "bg-[#F84464] text-white" : ""
                        }`}
                        onClick={() => handleSeatSelection(seat)}
                      >
                        {seat}
                      </div>
                    ))}
                  </div>
                  <div className="flex space-x-10 justify-center text-xs mt-5">
                    <div className="text-center">
                      <h1>NORMAL</h1>{" "}
                      <h1 className="font-semibold">Rs. 450.00</h1>{" "}
                      <h1 className="text-green-500">Available</h1>
                    </div>
                    <div className="text-center">
                      <h1>EXECUTIVE</h1>{" "}
                      <h1 className="font-semibold">Rs. 500.00</h1>{" "}
                      <h1 className="text-green-500">Available</h1>
                    </div>
                    <div className="text-center">
                      <h1>PREMIUM</h1>{" "}
                      <h1 className="font-semibold">Rs. 550.00</h1>{" "}
                      <h1 className="text-green-500">Available</h1>
                    </div>
                  </div>
                  <button
                    className="w-full bg-[#F84464] text-white py-2 rounded-lg mt-5"
                    onClick={() => {
                      if (selectedSeat !== 0) {
                        closeModal();
                      } else {
                        return;
                      }
                    }}
                  >
                    Select Seats
                  </button>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </>
  );
};

export default MovieDetail;
